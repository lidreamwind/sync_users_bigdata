package com.thriteen.bgd.usersync.entity.dto.utils;

/**
 * @Author: Lph
 * @Date: 2020/11/17 16:36
 * @Function:
 * @Version 1.0
 */
public class UnixUserOperationDto {
    /**
     * 远程ip
     */
    private String remoteIp;
    /**
     * 远程服务器端口
     */
    private Integer remotePort;
    /**
     * 远程服务连接用户名
     */
    private String userName;
    /**
     * 远程连接的用户密码
     */
    private String userPasswd;
    /**
     * 远程用户连接的密钥文件
     */
    private String userSshFile;
    /**
     * 远程连接的超时时间
     */
    private Integer connectTimeout;
    private Integer keyOut;

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public Integer getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
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

    public String getUserSshFile() {
        return userSshFile;
    }

    public void setUserSshFile(String userSshFile) {
        this.userSshFile = userSshFile;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getKeyOut() {
        return keyOut;
    }

    public void setKeyOut(Integer keyOut) {
        this.keyOut = keyOut;
    }
}
