package com.thriteen.bgd.usersync.services;

import com.thriteen.bgd.usersync.entity.po.KerberosConnectionsInfoEntity;
import com.thriteen.bgd.usersync.entity.vo.kerberos.KerberosConnectionsInfoVo;
import com.thriteen.bgd.usersync.utils.WebResult;

import java.util.HashMap;

/**
 * @Author: Lph
 * @Date: 2020/11/16 17:55
 * @Function:
 * @Version 1.0
 */
public interface KerberosService {
    /**
     * 从Kerberos同步用户到MySQL元数据库中
     * @return
     */
    public HashMap<String,Integer> initUserFromKerberosToMysql();

    /**
     * 初始化Kerberos连接到MySQL数据库中
     */
    public boolean initKerberosUrlFromConfig();

    public WebResult insertNewKerberosInfo(KerberosConnectionsInfoVo kerberosConnectionsInfoVo);
}
