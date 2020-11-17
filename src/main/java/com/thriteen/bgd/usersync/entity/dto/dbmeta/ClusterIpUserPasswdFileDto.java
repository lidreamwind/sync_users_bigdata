package com.thriteen.bgd.usersync.entity.dto.dbmeta;

/**
 * @Author: Lph
 * @Date: 2020/11/14 10:57
 * @Function:
 * @Version 1.0
 */
public class ClusterIpUserPasswdFileDto {
    /**
     * ip 地址
     */
    private String ipAddr;
    /**
     * 登录ip的用户名
     */
    private String userName;
    /**
     * 登录ip的用户名对应密码
     */
    private String userPasswd;
    /**
     * 登录ip的秘钥文件
     */
    private String userKeyFile;

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getUserKeyFile() {
        return userKeyFile;
    }

    public void setUserKeyFile(String userKeyFile) {
        this.userKeyFile = userKeyFile;
    }
}
