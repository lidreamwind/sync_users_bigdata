package com.thriteen.bgd.usersync.services.impl;

import com.thriteen.bgd.usersync.dao.dbmeta.ClusterIpsMapper;
import com.thriteen.bgd.usersync.dao.dbmeta.UserLinuxMapper;
import com.thriteen.bgd.usersync.entity.po.ClusterIpsEntity;
import com.thriteen.bgd.usersync.entity.po.UserLinuxEntity;
import com.thriteen.bgd.usersync.entity.dto.dbmeta.ClusterIpUserPasswdFileDto;
import com.thriteen.bgd.usersync.entity.paraConf.UnixParamEntity;
import com.thriteen.bgd.usersync.services.UnixUserAndIpService;
import com.thriteen.bgd.usersync.utils.MyTimeUtils;
import com.thriteen.bgd.usersync.utils.remote.CommandRemoteTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Lph
 * @Date: 2020/11/12 22:42
 * @Function:
 * @Version 1.0
 */
@Service
public class UnixUserAndIpServiceImpl implements UnixUserAndIpService {
    @Autowired
    private UnixParamEntity upe;
    @Autowired
    private ClusterIpsMapper clusterIpsMapper;
    @Autowired
    private UserLinuxMapper userLinuxMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private MyTimeUtils myTimeUtils = new MyTimeUtils();


    /**
     * 目前从系统同步用户
     * @return
     */
    @Override
    public HashMap<String,String> initUnixUserForDbmeta() {
        List<ClusterIpUserPasswdFileDto> clusterIpUserPasswdFileEntities = clusterIpsMapper.selectAllIpsUserPasswdKeyFile();
        if(clusterIpUserPasswdFileEntities.size()<=0){
            return null;
        }
        HashMap<String,String> rsMap = new HashMap<>();
        String command = "cat /etc/passwd";
        for (ClusterIpUserPasswdFileDto clusterIpUserPasswdFileDto : clusterIpUserPasswdFileEntities) {
            CommandRemoteTools commandRemoteTools = new CommandRemoteTools(clusterIpUserPasswdFileDto.getIpAddr(),upe.getPort(), upe.getConnectTimeout(), upe.getKexTimeOut());
            // user password
            if(commandRemoteTools.isAuthenticated(clusterIpUserPasswdFileDto.getUserName(), clusterIpUserPasswdFileDto.getUserPasswd())){
                BufferedReader bufferedReader = commandRemoteTools.executeCommandOnRemote(command);
                boolean b = this.initUnixUser(bufferedReader, clusterIpUserPasswdFileDto.getIpAddr());
                if(b){
                    rsMap.put(clusterIpUserPasswdFileDto.getIpAddr(),"success");
                }else {
                    rsMap.put(clusterIpUserPasswdFileDto.getIpAddr(),"failed.");
                }
                // 免密方式
            }else if(commandRemoteTools.isAuthenticated(clusterIpUserPasswdFileDto.getUserName(),new File(clusterIpUserPasswdFileDto.getUserKeyFile()))){
                BufferedReader bufferedReader = commandRemoteTools.executeCommandOnRemote(command);
                boolean b = this.initUnixUser(bufferedReader, clusterIpUserPasswdFileDto.getIpAddr());
                if(b){
                    rsMap.put(clusterIpUserPasswdFileDto.getIpAddr(),"success");
                }else {
                    rsMap.put(clusterIpUserPasswdFileDto.getIpAddr(),"failed.");
                }
            }else {
                rsMap.put(clusterIpUserPasswdFileDto.getIpAddr(),"failed.");
                this.logger.error(String.format("-----please check host:%s the user name and ssh key file.", clusterIpUserPasswdFileDto.getIpAddr()));
            }
        }
        return rsMap;
    }

    /**
     * 集群ip地址初始化，通过服务所在的机器/etc/hosts获取集群内机器的IP地址
     * 后续版本，可指定服务器进行初始化，或者自己手动维护,对于删除，则是删除，不需要逻辑删除
     * @return
     */
    @Override
//    @TODO
    public boolean initUnixIpForDbmeta() {
        // 功能测试
        File hostFile = new File("testFile/hosts");
//        File hostFile = new File(upe.getHostsFilePath());
        BufferedReader bufferedReader = null;
        String line = "";
        // 获取密码
        HashMap<String, ArrayList<String>> ipUserPasswd = this.getClusterIpsIndetifeidInfoHost(upe.getClusterSyncPasswd());
        String userPasswd = upe.getClusterSyncPasswd();
        try {
            bufferedReader = new BufferedReader(new FileReader(hostFile));
            while ((line = bufferedReader.readLine()) != null){
                ClusterIpsEntity clusterIpsEntity = new ClusterIpsEntity();
                // 去除本机ip
                if(!line.replace(" ","").isEmpty() && !(line.contains("localhost") || line.contains("127.0.0.1"))) {
                    String[] lines = line.split(" ");
                    clusterIpsEntity.setComment("init operation");
                    clusterIpsEntity.setCreateUser(this.upe.getUserFlag());
                    clusterIpsEntity.setCreateDate(myTimeUtils.getNowTimeYMD());
                    clusterIpsEntity.setIpAddr(lines[0]);
                    clusterIpsEntity.setUserPort(this.upe.getPort());
                    // 若每行有两个，或者顺序错了
                    if(lines.length==3){
                        if(lines[1].contains(".")){
                            clusterIpsEntity.setFullName(lines[1]);
                            clusterIpsEntity.setShortName(lines[2]);
                        }else{
                            clusterIpsEntity.setFullName(lines[2]);
                            clusterIpsEntity.setShortName(lines[1]);
                        }
                        // 密码
                        if(ipUserPasswd.containsKey(lines[1].replace(" ",""))){
                            clusterIpsEntity.setUserName(ipUserPasswd.get(lines[1]).get(0));
                            clusterIpsEntity.setUserPasswd(ipUserPasswd.get(lines[1]).get(1));
                        }else if(ipUserPasswd.containsKey(lines[2].replace(" ",""))) {
                            clusterIpsEntity.setUserName(ipUserPasswd.get(lines[2]).get(0));
                            clusterIpsEntity.setUserPasswd(ipUserPasswd.get(lines[2]).get(1));
                        }else {
                            if(userPasswd != null && userPasswd.replace(" ","").length()>0){
                                if(!userPasswd.contains(":")){
                                    this.logger.error("---check the configuration:%s, like this user:password","myconfig.remote.cluster-sync-passwd");
                                }else {
                                    String[] up = userPasswd.split(":");
                                    if(up.length != 2) {
                                        this.logger.error("---check the configuration:%s, like this user:password","myconfig.remote.cluster-sync-passwd");
                                    }else {
                                        clusterIpsEntity.setUserPasswd(up[1]);
                                        clusterIpsEntity.setUserName(up[0]);
                                    }
                                }
                            }
                        }
                    }else {
                        this.logger.error("---check the configuration:%s, like this computer:user:password","myconfig.remote.cluster-passwd");
                    }
                    if(upe.getSshKeyPath()!=null && upe.getSshKeyPath().length()>0){
                        clusterIpsEntity.setUserKeyfile(upe.getSshKeyPath());
                    }
                    if(this.clusterIpsMapper.selectCountByIpAddr(lines[0].replace(" ",""))<=0) {
                        clusterIpsMapper.insert(clusterIpsEntity);
                        this.logger.info(String.format("---hosts:%s inits succesully.", lines[0]));
                    }else {
                        this.logger.info(String.format("---hosts:%s already exists.", lines[0]));
                    }
                }

            }
            return  true;
        } catch (FileNotFoundException e) {
            if(bufferedReader == null){
                this.logger.error("hosts file is not in right path.");
            }
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            if(line == null){
                this.logger.error(String.format("hosts file:%s is is empty!", upe.getHostsFilePath()));
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取集群的认证方式，通过参数cluster-passwd: cluster1.bigdata.com:root:1,cluster2.bigdata.com:root:1
     */
    private HashMap<String, ArrayList<String>> getClusterIpsIndetifeidInfoHost(String connectStr){
        if(connectStr != null && connectStr.replace(" ","").length()>0){
            String[] ips = connectStr.split(",");
            HashMap<String, ArrayList<String>> rs = new HashMap();
            for (String ip : ips) {
                ArrayList userPasswd = new ArrayList();
                String[] maps = ip.split(":");
                if(maps.length != 3){
                    this.logger.error("-----please check parameter:myconfig.remote.cluster-passwd,the format is :ip(computer name):user:passwd !");
                }else {
                    userPasswd.add(maps[1]);
                    userPasswd.add(maps[2]);
                    rs.put(maps[0],userPasswd);
                }
            }
            return rs;
        }else {
            return null;
        }
    }

    /**
     * 处理/etc/passwd中的用户数据
     */
    private boolean initUnixUser(BufferedReader bufferedReader,String ip) {
        String line = "no";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                if(parts[0].equalsIgnoreCase("root")){
                    UserLinuxEntity userLinuxEntity = new UserLinuxEntity();
                    userLinuxEntity.setCreateUser("system");
                    userLinuxEntity.setCreateDate(this.myTimeUtils.getNowTimeYMD());
                    userLinuxEntity.setUserFlag(upe.getUserFlag());
                    userLinuxEntity.setUserIp(ip);
                    userLinuxEntity.setUserName(parts[0]);
                    if(this.userLinuxMapper.selectCountByIpAndUserName(ip,parts[0])<=0) {
                        this.userLinuxMapper.insert(userLinuxEntity);
                    }else {
                        this.logger.info(String.format("-----user:%s on host:%s already exists.", parts[0],ip));
                    }
                }else if(Integer.parseInt(parts[2])>=500){
                    UserLinuxEntity userLinuxEntity = new UserLinuxEntity();
                    userLinuxEntity.setCreateUser("system");
                    userLinuxEntity.setCreateDate(this.myTimeUtils.getNowTimeYMD());
                    userLinuxEntity.setUserFlag(upe.getUserFlag());
                    userLinuxEntity.setUserIp(ip);
                    userLinuxEntity.setUserName(parts[0]);
                    if(this.userLinuxMapper.selectCountByIpAndUserName(ip,parts[0])<=0) {
                        this.userLinuxMapper.insert(userLinuxEntity);
                    }else {
                        this.logger.info(String.format("-----user:%s on host:%s already exists.", parts[0],ip));
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
