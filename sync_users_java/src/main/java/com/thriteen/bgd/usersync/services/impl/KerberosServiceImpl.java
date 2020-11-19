package com.thriteen.bgd.usersync.services.impl;

import com.thriteen.bgd.usersync.dao.dbmeta.ClusterIpsMapper;
import com.thriteen.bgd.usersync.dao.dbmeta.KerberosConnectionsInfoMapper;
import com.thriteen.bgd.usersync.dao.dbmeta.UserKerberosMapper;
import com.thriteen.bgd.usersync.entity.dto.dbmeta.ClusterIpUserPasswdFileDto;
import com.thriteen.bgd.usersync.entity.dto.kerberos.KerberosInitDto;
import com.thriteen.bgd.usersync.entity.paraConf.KerberosParamEntity;
import com.thriteen.bgd.usersync.entity.paraConf.UnixParamEntity;
import com.thriteen.bgd.usersync.entity.po.KerberosConnectionsInfoEntity;
import com.thriteen.bgd.usersync.entity.vo.kerberos.KerberosConnectionsInfoVo;
import com.thriteen.bgd.usersync.services.KerberosService;
import com.thriteen.bgd.usersync.utils.ExceptionEnum;
import com.thriteen.bgd.usersync.utils.MyTimeUtils;
import com.thriteen.bgd.usersync.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Lph
 * @Date: 2020/11/16 17:56
 * @Function:
 * @Version 1.0
 */
@Service
public class KerberosServiceImpl implements KerberosService {
    @Autowired
    private UserKerberosMapper userKerberosMapper;
    @Autowired
    private KerberosParamEntity kerberosParamEntity;
    @Autowired
    private KerberosConnectionsInfoMapper kerberosConnectionsInfoMapper;
    @Autowired
    private UnixParamEntity unixParamEntity;
    @Autowired
    private ClusterIpsMapper clusterIpsMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private MyTimeUtils myTimeUtils = new MyTimeUtils();

    @Override
    public HashMap<String, Integer> initUserFromKerberosToMysql() {
        HashMap<String,Integer> rs = new HashMap<>();

        List<ClusterIpUserPasswdFileDto> clusterIpUserPasswdFileDtos = clusterIpsMapper.selectAllIpsUserPasswdKeyFile();
        for (ClusterIpUserPasswdFileDto clusterIpUserPasswdFileDto : clusterIpUserPasswdFileDtos) {
            KerberosInitDto kerberosInitDto = new KerberosInitDto();
            kerberosInitDto.setIp(clusterIpUserPasswdFileDto.getIpAddr());
            kerberosInitDto.setSshKeyFile(clusterIpUserPasswdFileDto.getUserKeyFile());
            kerberosInitDto.setRemoteUser(clusterIpUserPasswdFileDto.getUserName());
            kerberosInitDto.setRemotePasswd(clusterIpUserPasswdFileDto.getUserPasswd());

            // Kerberos 域名信息
        }


        return null;
    }

    /**
     * 初始化Kerberos的连接信息
     * @return
     */
    @Override
    public boolean initKerberosUrlFromConfig() {
        KerberosConnectionsInfoEntity kerberosConnectionsInfoEntity = new KerberosConnectionsInfoEntity();
        kerberosConnectionsInfoEntity.setKerberosConnectName(this.kerberosParamEntity.getKerberosConnectName());
        kerberosConnectionsInfoEntity.setKerberosServerIp(this.kerberosParamEntity.getKerberosServerIp());
        kerberosConnectionsInfoEntity.setKerberosDomain(this.kerberosParamEntity.getServerDomain());
        kerberosConnectionsInfoEntity.setKerberosKeytabPath(this.kerberosParamEntity.getKeytabPath());
        String kerberosSyncAdmin = this.kerberosParamEntity.getKerberosSyncAdmin();
        if(kerberosSyncAdmin.equalsIgnoreCase("true")) {
            kerberosConnectionsInfoEntity.setKerberosSyncAdmin("1");
        }else {
            kerberosConnectionsInfoEntity.setKerberosSyncAdmin("0");
        }
        kerberosConnectionsInfoEntity.setCreateDate(this.myTimeUtils.getNowTimeYMD());
        kerberosConnectionsInfoEntity.setCreateUser(this.unixParamEntity.getUserFlag());
        int insert = this.kerberosConnectionsInfoMapper.insert(kerberosConnectionsInfoEntity);
        if(insert>0){
            this.logger.info("init Kerberos info from Config successfully.");
            return true;
        }else {
            return false;
        }
    }

    /**
     * 插入新的Kerberos连接
     * @param kerberosConnectionsInfoVo
     * @return
     */
    @Override
    public WebResult insertNewKerberosInfo(KerberosConnectionsInfoVo kerberosConnectionsInfoVo) {
        KerberosConnectionsInfoEntity kerberosConnectionsInfoEntity = new KerberosConnectionsInfoEntity();
        kerberosConnectionsInfoEntity.setKerberosConnectName(kerberosConnectionsInfoVo.getConnectName());
        kerberosConnectionsInfoEntity.setKerberosServerIp(kerberosConnectionsInfoVo.getServerIp());
        kerberosConnectionsInfoEntity.setKerberosDomain(kerberosConnectionsInfoVo.getDomain());
        kerberosConnectionsInfoEntity.setKerberosKeytabPath(kerberosConnectionsInfoVo.getKeyPath());
        kerberosConnectionsInfoEntity.setKerberosSyncAdmin(kerberosConnectionsInfoVo.getSyncAdmin());
        kerberosConnectionsInfoEntity.setCreateUser("sys");
        kerberosConnectionsInfoEntity.setComment(kerberosConnectionsInfoVo.getComment());
        if(kerberosConnectionsInfoVo.getCreateDate() == null){
            kerberosConnectionsInfoEntity.setCreateDate(this.myTimeUtils.getNowTimeYMD());
        }else {
            kerberosConnectionsInfoEntity.setCreateDate(kerberosConnectionsInfoVo.getCreateDate());
        }
        if(kerberosConnectionsInfoVo.getCreateDate() == null){
            kerberosConnectionsInfoEntity.setUpdateDate(this.myTimeUtils.getNowTimeYMD());
        }else {
            kerberosConnectionsInfoEntity.setUpdateDate(kerberosConnectionsInfoVo.getUpdateDate());
        }
        Integer integer = this.clusterIpsMapper.selectCountByIpAddr(kerberosConnectionsInfoEntity.getKerberosServerIp());
        if(integer<=0){
            return  new WebResult(ExceptionEnum.CLUSTER_IP_NOT_EXISTS.getCode(),ExceptionEnum.CLUSTER_IP_NOT_EXISTS.getMessage(),kerberosConnectionsInfoEntity.getKerberosServerIp());
        }
        Integer rs = this.kerberosConnectionsInfoMapper.selectByConnectName(kerberosConnectionsInfoEntity.getKerberosConnectName());
        if(rs <= 0){
            this.kerberosConnectionsInfoMapper.insert(kerberosConnectionsInfoEntity);
            this.logger.info(String.format("-----Connection name:%s already exists", kerberosConnectionsInfoEntity.getKerberosConnectName()));
            return  new WebResult(ExceptionEnum.KERBEROS_CONNECTION_INSERT_SUCCESS.getCode(),ExceptionEnum.KERBEROS_CONNECTION_INSERT_SUCCESS.getMessage(),kerberosConnectionsInfoEntity.toString());
        }else {
            return  new WebResult(ExceptionEnum.KERBEROS_CONNECTION_FOUND.getCode(),ExceptionEnum.KERBEROS_CONNECTION_FOUND.getMessage(),kerberosConnectionsInfoEntity.toString());
        }
    }
}
