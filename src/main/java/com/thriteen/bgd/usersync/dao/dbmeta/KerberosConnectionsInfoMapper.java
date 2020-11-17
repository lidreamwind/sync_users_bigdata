package com.thriteen.bgd.usersync.dao.dbmeta;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thriteen.bgd.usersync.entity.po.KerberosConnectionsInfoEntity;

/**
 * @Author: Lph
 * @Date: 2020/11/8 20:28
 * @Function:
 * @Version 1.0
 */
public interface KerberosConnectionsInfoMapper extends BaseMapper<KerberosConnectionsInfoEntity> {
    Integer selectByConnectName(String kerberosConnectName);
}
