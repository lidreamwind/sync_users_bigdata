package com.thriteen.bgd.usersync.services;

import com.thriteen.bgd.usersync.entity.po.LdapConnectionsUrlEntity;
import com.thriteen.bgd.usersync.entity.vo.ldap.LdapConnectionsUrlVo;
import com.thriteen.bgd.usersync.utils.WebResult;

/**
 * @Author: Lph
 * @Date: 2020/11/14 17:35
 * @Function:
 * @Version 1.0
 */
public interface LdapService {
    /**
     * 初始化LDAP连接信息，从配置文件读取插入到数据库元信息中。
     * 1 是成功， 2是连接名存在， 3是失败
     * @return
     */
    public Integer initLdapUrls();

    /**
     * 插入新创建的连接信息
     * @param ldapConnectionsUrlVo
     * @return
     */
    public WebResult insertLdapConnectionUrlInfo(LdapConnectionsUrlVo ldapConnectionsUrlVo);

    /**
     *  初始化LDAP用户信息，同步LDAP用户到元数据库中。
     */
    public Integer initLdapUsersFromLdapServerToDbmeta(String connectName);
}
