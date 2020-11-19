package com.thriteen.bgd.usersync.services.impl;

import com.thriteen.bgd.usersync.dao.dbmeta.ClusterIpsMapper;
import com.thriteen.bgd.usersync.dao.dbmeta.LdapConnectionsUrlMapper;
import com.thriteen.bgd.usersync.dao.dbmeta.UserLdapMapper;
import com.thriteen.bgd.usersync.entity.dto.ldap.LdapUserDto;
import com.thriteen.bgd.usersync.entity.po.LdapConnectionsUrlEntity;
import com.thriteen.bgd.usersync.entity.paraConf.LdapParamEntity;
import com.thriteen.bgd.usersync.entity.paraConf.UnixParamEntity;
import com.thriteen.bgd.usersync.entity.po.UserLdapEntity;
import com.thriteen.bgd.usersync.entity.vo.ldap.LdapConnectionsUrlVo;
import com.thriteen.bgd.usersync.services.LdapService;
import com.thriteen.bgd.usersync.utils.ExceptionEnum;
import com.thriteen.bgd.usersync.utils.MyTimeUtils;
import com.thriteen.bgd.usersync.utils.WebResult;
import com.thriteen.bgd.usersync.utils.ldap.LdapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lph
 * @Date: 2020/11/14 17:36
 * @Function:
 * @Version 1.0
 */
@Service
public class LdapServiceImpl implements LdapService {
    @Autowired
    private LdapParamEntity ldapParamEntity;
    @Autowired
    private UnixParamEntity unixParamEntity;
    @Autowired
    private LdapConnectionsUrlMapper ldapConnectionsUrlMapper;
    @Autowired
    private UserLdapMapper userLdapMapper;
    @Autowired
    private ClusterIpsMapper clusterIpsMapper;

    private MyTimeUtils myTimeUtils = new MyTimeUtils();
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 初始化LDAP的信息，将配置文件中填写的URL信息更新到数据库中。
     * @return
     */
    @Override
    public Integer initLdapUrls() {
        LdapConnectionsUrlEntity ldapConnectionsUrlEntity = new LdapConnectionsUrlEntity();
        ldapConnectionsUrlEntity.setCreateDate(myTimeUtils.getNowTimeYMD());
        ldapConnectionsUrlEntity.setLdapUrl(ldapParamEntity.getLdapUrl());
        ldapConnectionsUrlEntity.setLdapAdminUser(ldapParamEntity.getAdminUser());
        ldapConnectionsUrlEntity.setLdapAdminPasswd(ldapParamEntity.getAdminPasswd());
        ldapConnectionsUrlEntity.setLdapBaseDn(ldapParamEntity.getBaseDN());
        ldapConnectionsUrlEntity.setLdapFactoryClass(ldapParamEntity.getFactorClass());
        ldapConnectionsUrlEntity.setLdapSecurityLevel(ldapParamEntity.getSecurityLevel());
        ldapConnectionsUrlEntity.setLdapUrlName(ldapParamEntity.getLdapConnectName());
        ldapConnectionsUrlEntity.setCreateUser(unixParamEntity.getUserFlag());
        ldapConnectionsUrlEntity.setLdapServerIp(this.getIpFromLdapUrl(ldapParamEntity.getLdapUrl()));
        try{
            if(this.ldapConnectionsUrlMapper.selectByLdapName(ldapParamEntity.getLdapConnectName())<=0) {
                this.ldapConnectionsUrlMapper.insert(ldapConnectionsUrlEntity);
                this.logger.info("-----the Ldap infos from configration inits successfully.");
                return 1;
            }else {
                this.logger.info("-----the Ldap connection name has existed");
                return 2;
            }
        }catch (Exception e){
            this.logger.error("-----the Ldap infos from configration inits failed.");
            return 0;
        }
    }

    /**
     * 从Ldap服务连接中获取ip信息
     * @param url
     * @return
     */
    private String getIpFromLdapUrl(String url){
        if(url != null) {
            String[] s = url.replace(" ", "").split("/");
            return s[2].substring(0);
        }
        return "";
    }

    /**
     * 插入自定义的ldap url信息
     * @param ldapConnectionsUrlVo
     * @return
     */
    @Override
    public WebResult insertLdapConnectionUrlInfo(LdapConnectionsUrlVo ldapConnectionsUrlVo) {
        Integer integer = this.clusterIpsMapper.selectCountByIpAddr(this.getIpFromLdapUrl(ldapConnectionsUrlVo.getLdapUrl()));
        if(integer<=0){
            return  new WebResult(ExceptionEnum.CLUSTER_IP_NOT_EXISTS.getCode(),ExceptionEnum.CLUSTER_IP_NOT_EXISTS.getMessage());
        }

        if(this.ldapConnectionsUrlMapper.selectByLdapName(ldapConnectionsUrlVo.getLdapUrlName())<=0) {
            try {
                LdapConnectionsUrlEntity ldapConnectionsUrlEntity = new LdapConnectionsUrlEntity();
                ldapConnectionsUrlEntity.setLdapUrlName(ldapConnectionsUrlVo.getLdapUrlName());
                ldapConnectionsUrlEntity.setLdapUrl(ldapConnectionsUrlVo.getLdapUrl());
                ldapConnectionsUrlEntity.setLdapAdminUser(ldapConnectionsUrlVo.getLdapAdminUser());
                ldapConnectionsUrlEntity.setLdapAdminPasswd(ldapConnectionsUrlVo.getLdapAdminPasswd());
                ldapConnectionsUrlEntity.setLdapBaseDn(ldapConnectionsUrlVo.getLdapBaseDn());
                ldapConnectionsUrlEntity.setLdapUserProper(ldapConnectionsUrlVo.getLdapUserProper());
                ldapConnectionsUrlEntity.setLdapFactoryClass(ldapConnectionsUrlVo.getLdapFactoryClass());
                ldapConnectionsUrlEntity.setLdapSecurityLevel(ldapConnectionsUrlVo.getLdapSecurityLevel());
                ldapConnectionsUrlEntity.setCreateUser(ldapConnectionsUrlVo.getCreateUser());
                ldapConnectionsUrlEntity.setCreateDate(this.myTimeUtils.getNowTimeYMD());
                ldapConnectionsUrlEntity.setComment(ldapConnectionsUrlVo.getComment());

                this.ldapConnectionsUrlMapper.insert(ldapConnectionsUrlEntity);
                this.logger.info(String.format("-----the Ldap infos:%s insert successfully.", ldapConnectionsUrlVo.toString()));
                return new WebResult(ExceptionEnum.LDAP_INIT_INSERT.getCode(),ExceptionEnum.LDAP_INIT_INSERT.getMessage(),"successfully.");
            }catch (Exception e){
                this.logger.error(String.format("-----the Ldap infos:%s insert failed.", ldapConnectionsUrlVo.toString()));
                return new WebResult(ExceptionEnum.LDAP_INIT_EXISTS.getCode(),ExceptionEnum.LDAP_INIT_EXISTS.getMessage(),"failed.");
            }
        }else {
            this.logger.info(String.format("-----the Ldap connection name:%s has existed", ldapConnectionsUrlVo.getLdapUrlName()));
            return new WebResult(ExceptionEnum.LDAP_INIT_FAILED.getCode(),ExceptionEnum.LDAP_INIT_FAILED.getMessage(),"error.");
        }
    }

    /**
     * 同步LDAP用户信息到MySQL中
     * @param connectName, 连接名称，根据连接名称，同步数据
     * @return
     */
    @Override
    public Integer initLdapUsersFromLdapServerToDbmeta(String connectName) {
        Integer integer = this.ldapConnectionsUrlMapper.selectByLdapName(connectName);
        if(integer>0) {
            //获取 Ldap连接信息，  查询Ldap User信息
            LdapConnectionsUrlEntity ldapConnectionsUrlEntity = this.ldapConnectionsUrlMapper.selectAllByLdapUrlName(connectName);
            LdapUtils ldapUtils = new LdapUtils(ldapConnectionsUrlEntity.getLdapUrl(),ldapConnectionsUrlEntity.getLdapAdminUser(),
                    ldapConnectionsUrlEntity.getLdapSecurityLevel(),ldapConnectionsUrlEntity.getLdapAdminPasswd(),ldapConnectionsUrlEntity.getLdapFactoryClass());
            LdapUserDto ldapUserDto = new LdapUserDto();
            ldapUserDto.setBaseDn(ldapConnectionsUrlEntity.getLdapBaseDn());
            ldapUserDto.setUid(ldapConnectionsUrlEntity.getLdapUserProper());
            List<LdapUserDto> ldapUserDtos = ldapUtils.selectAllUser(ldapUserDto);
            // 便利，更新到数据库
            if(ldapUserDtos.size()<=0){
                this.logger.info("-----please check the Ldap server, maybe there are no users.");
                return -1;
            }else {
                for (LdapUserDto userDto : ldapUserDtos) {
                    UserLdapEntity userLdapEntity = new UserLdapEntity();
                    userLdapEntity.setUserName(userDto.getUidnumbeerValue());
                    userLdapEntity.setUserPasswd(userDto.getUserpasswordValue());
                    userLdapEntity.setUserDomain(ldapConnectionsUrlEntity.getLdapBaseDn());
                    userLdapEntity.setUserUid(userDto.getUidPro());
                    userLdapEntity.setUserLdapIp(ldapConnectionsUrlEntity.getLdapServerIp());
                    userLdapEntity.setUserFlag("sysinit");
                    userLdapEntity.setCreateDate(myTimeUtils.getNowTimeYMD());
                    userLdapEntity.getCreateUser(this.unixParamEntity.getUserFlag());
                    this.userLdapMapper.insert(userLdapEntity);
                }
                return ldapUserDtos.size();
            }
        }else {
            this.logger.info(String.format("-----Ldap Url is not exists, Ldap Url name:%s", connectName));
            return integer;
        }
    }
}
