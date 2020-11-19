package com.thriteen.bgd.usersync.dao.dbmeta;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thriteen.bgd.usersync.entity.po.LdapConnectionsUrlEntity;

/**
 * @Author: Lph
 * @Date: 2020/11/14 18:26
 * @Function:
 * @Version 1.0
 */
public interface LdapConnectionsUrlMapper extends BaseMapper<LdapConnectionsUrlEntity> {
    /**
     * 根据Ldap连接名字查询是否有重复
     * @param ldapName Ldap连接名字
     * @return 返回统计数量
     */
    Integer selectByLdapName(String ldapName);

    /**
     * 根据Ldap的连接名称，获取记录信息
     * @param connectName
     * @return
     */
    LdapConnectionsUrlEntity selectAllByLdapUrlName(String connectName);
}