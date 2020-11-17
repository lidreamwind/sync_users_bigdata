package com.thriteen.bgd.usersync.utils.unix;

import com.thriteen.bgd.usersync.entity.dto.utils.UnixUserOperationDto;
import com.thriteen.bgd.usersync.utils.remote.CommandRemoteTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * @Author: Lph
 * @Date: 2020/11/17 16:21
 * @Function:
 * @Version 1.0
 */
public class UnixUserOperationUtils {
    /**
     * Unix 用户操作的条件
     */
    private UnixUserOperationDto uuod = null;
    /**
     * 远程工具类
     */
    private CommandRemoteTools commandRemoteTools = null;
    /**
     * 远程工具类是否验证成功，默认验证失败
     */
    private boolean isAuthen  = false;
    /**
     * 日志
     */
    private final  Logger log = LoggerFactory.getLogger(getClass());

    public UnixUserOperationUtils(UnixUserOperationDto unixUserOperationDto) {
        this.uuod = unixUserOperationDto;
        // 创建远程连接工具
        if(this.uuod.getConnectTimeout() != null && this.uuod.getKeyOut() != null){
            this.commandRemoteTools = new CommandRemoteTools(this.uuod.getRemoteIp(),this.uuod.getRemotePort(),
                    this.uuod.getConnectTimeout(),this.uuod.getKeyOut());
        }else {
            this.commandRemoteTools = new CommandRemoteTools(this.uuod.getRemoteIp(),this.uuod.getRemotePort());
        }
        //初始化
        if(uuod.getUserPasswd() != null) {
            boolean authenticated = this.commandRemoteTools.isAuthenticated(this.uuod.getUserName(), this.uuod.getUserPasswd());
            this.isAuthen = authenticated;
        }
        if(!this.isAuthen && this.uuod.getUserSshFile() != null){
            boolean auth = this.commandRemoteTools.isAuthenticated(this.uuod.getUserName(),new File(this.uuod.getUserSshFile()));
            this.isAuthen = auth;
        }
        if(!this.isAuthen){
            this.log.error("please confirm the Remoete Host variable info in class: UnixUserOperationDto");
        }
    }

    /**
     * 创建用户
     * @param userName 用户名
     * @param group 用户所属组
     * @param userPassword  用户的密码
     */
    public void addUser(String userName, String userPassword, String group){
        String createUser = String.format("useradd -G %s %s", group,userName);
        BufferedReader bufferedReader = this.commandRemoteTools.executeCommandOnRemote(createUser);
        this.modifyUserPasswd(userName,userPassword);
    }
    /**
     * 创建用户
     * @param userName 用户名
     * @param userPassword  用户的密码
     */
    public void addUser(String userName, String userPassword){
        String createUser = String.format("useradd %s", userName);
        BufferedReader bufferedReader = this.commandRemoteTools.executeCommandOnRemote(createUser);
        boolean b = this.modifyUserPasswd(userName, userPassword);
    }

    /**
     * 修改用户密码， 当密码为空的时候，则密码和用户名保持一致
     * @param user
     * @param passwd
     * @return
     */
    public boolean modifyUserPasswd(String user, String passwd){
        String command = null;
        if(passwd == null || passwd.length()<=0) {
            command = String.format("echo \"%s\"| passwd --stdin %s", user, user);
        }else {
            command = String.format("echo \"%s\"| passwd --stdin %s", passwd,user);
        }
        BufferedReader bufferedReader = this.commandRemoteTools.executeCommandOnRemote(command);
        String line = null;
        try {
            while((line = bufferedReader.readLine()) != null){
                if(line.contains("passwd: all authentication tokens updated successfully")){
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  删除系统用户
     * @param userName
     */
    public void deleteUser(String userName){
        String command = String.format("userdel -r %s", userName);
        BufferedReader bufferedReader = this.commandRemoteTools.executeCommandOnRemote(command);
    }

    /**
     * 关闭远程工具连接
     */
    public void close(){
        this.commandRemoteTools.close();
    }

}
