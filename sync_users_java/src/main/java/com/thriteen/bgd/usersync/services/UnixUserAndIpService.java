package com.thriteen.bgd.usersync.services;

import java.util.HashMap;

/**
 * @Author: Lph
 * @Date: 2020/11/12 22:42
 * @Function:  用于操作linux系统和linux系统ip
 * @Version 1.0
 */
public interface UnixUserAndIpService {
    /**
     * 初始化Linux用户到元数据库中
     * @return
     */
    public HashMap<String,String> initUnixUserForDbmeta();

    /**
     * 初始化系统的ip到数据库，默认初始化/etc/hosts文件
     * @return
     */
    public boolean initUnixIpForDbmeta();
}
