package com.thriteen.bgd.usersync.utils;

/**
 * @Author: Lph
 * @Date: 2020/11/12 13:30
 * @Function:
 * @Version 1.0
 */
public class ResultFormatTools {
    /**
     * format the log for only CommandRemoteTools.
     * @param s
     * @param ip
     * @return
     */
    public String logHostFormat(String s,String ip){
        return String.format(s, ip);
    }
    public String logHostFormat(String s,String ip,String user,String passwd){
        return String.format(s, user,passwd,ip);
    }
}
