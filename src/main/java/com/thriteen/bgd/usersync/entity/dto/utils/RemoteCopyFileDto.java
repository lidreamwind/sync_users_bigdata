package com.thriteen.bgd.usersync.entity.dto.utils;

import java.util.Arrays;

/**
 * @Author: Lph
 * @Date: 2020/11/17 14:48
 * @Function:
 * @Version 1.0
 */
public class RemoteCopyFileDto {
    /**
     * 文件所在服务器
     */
    private String fromRemoteHostIp;
    /**
     * 文件所在服务器的用户
     */
    private String fromRemoteUser;
    /**
     * 文件所在服务器的密码
     */
    private String fromRemotePasswd;
    /**
     * 文件所在服务器的秘钥文件
     */
    private String fromRemoeteSShFile;
    /**
     * 文件所在服务器的端口
     */
    private Integer fromRemotePort;
    /**
     * 文件所在服务器的连接超时时间
     */
    private Integer fromConnectTimeOut;
    private Integer fromKexTimeOut;
    /**
     * 需要拷贝的文件
     */
    private String[] fromRemotefiles;

    /**
     * 目标所在服务器
     */
    private String toRemoteHostIp;
    /**
     * 目标服务器的用户
     */
    private String toRemoteUser;
    /**
     * 目标服务器的密码
     */
    private String toRemotePasswd;
    /**
     * 目标服务器的秘钥文件
     */
    private String toRemoeteSShFile;
    /**
     * 目标服务器的端口
     */
    private Integer toRemotePort;
    /**
     * 目标服务器的连接超时时间
     */
    private Integer toConnectTimeOut;
    private Integer toKexTimeOut;
    /**
     * 需要拷贝的目录
     */
    private String toRemotePath;

    /**
     * 临时目录设置
     */
    private String tempDir;

    public RemoteCopyFileDto() {
        this.tempDir = "/tmp/usr_sync/";
    }

    public String getFromRemoteHostIp() {
        return fromRemoteHostIp;
    }

    public void setFromRemoteHostIp(String fromRemoteHostIp) {
        this.fromRemoteHostIp = fromRemoteHostIp;
    }

    public String getFromRemoteUser() {
        return fromRemoteUser;
    }

    public void setFromRemoteUser(String fromRemoteUser) {
        this.fromRemoteUser = fromRemoteUser;
    }

    public String getFromRemotePasswd() {
        return fromRemotePasswd;
    }

    public void setFromRemotePasswd(String fromRemotePasswd) {
        this.fromRemotePasswd = fromRemotePasswd;
    }

    public String getFromRemoeteSShFile() {
        return fromRemoeteSShFile;
    }

    public void setFromRemoeteSShFile(String fromRemoeteSShFile) {
        this.fromRemoeteSShFile = fromRemoeteSShFile;
    }

    public Integer getFromRemotePort() {
        return fromRemotePort;
    }

    public void setFromRemotePort(Integer fromRemotePort) {
        this.fromRemotePort = fromRemotePort;
    }

    public Integer getFromConnectTimeOut() {
        return fromConnectTimeOut;
    }

    public void setFromConnectTimeOut(Integer fromConnectTimeOut) {
        this.fromConnectTimeOut = fromConnectTimeOut;
    }

    public Integer getFromKexTimeOut() {
        return fromKexTimeOut;
    }

    public void setFromKexTimeOut(Integer fromKexTimeOut) {
        this.fromKexTimeOut = fromKexTimeOut;
    }

    public String[] getFromRemotefiles() {
        return fromRemotefiles;
    }

    public void setFromRemotefiles(String[] fromRemotefiles) {
        this.fromRemotefiles = fromRemotefiles;
    }

    public String getToRemoteHostIp() {
        return toRemoteHostIp;
    }

    public void setToRemoteHostIp(String toRemoteHostIp) {
        this.toRemoteHostIp = toRemoteHostIp;
    }

    public String getToRemoteUser() {
        return toRemoteUser;
    }

    public void setToRemoteUser(String toRemoteUser) {
        this.toRemoteUser = toRemoteUser;
    }

    public String getToRemotePasswd() {
        return toRemotePasswd;
    }

    public void setToRemotePasswd(String toRemotePasswd) {
        this.toRemotePasswd = toRemotePasswd;
    }

    public String getToRemoeteSShFile() {
        return toRemoeteSShFile;
    }

    public void setToRemoeteSShFile(String toRemoeteSShFile) {
        this.toRemoeteSShFile = toRemoeteSShFile;
    }

    public Integer getToRemotePort() {
        return toRemotePort;
    }

    public void setToRemotePort(Integer toRemotePort) {
        this.toRemotePort = toRemotePort;
    }

    public Integer getToConnectTimeOut() {
        return toConnectTimeOut;
    }

    public void setToConnectTimeOut(Integer toConnectTimeOut) {
        this.toConnectTimeOut = toConnectTimeOut;
    }

    public Integer getToKexTimeOut() {
        return toKexTimeOut;
    }

    public void setToKexTimeOut(Integer toKexTimeOut) {
        this.toKexTimeOut = toKexTimeOut;
    }

    public String getToRemotePath() {
        return toRemotePath;
    }

    public void setToRemotePath(String toRemotePath) {
        this.toRemotePath = toRemotePath;
    }

    public String getTempDir() {
        return tempDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    @Override
    public String toString() {
        return "RemoteCopyFileDto{" +
                "fromRemoteHostIp='" + fromRemoteHostIp + '\'' +
                ", fromRemoteUser='" + fromRemoteUser + '\'' +
                ", fromRemotefiles=" + Arrays.toString(fromRemotefiles) +
                ", toRemoteHostIp='" + toRemoteHostIp + '\'' +
                ", toRemoteUser='" + toRemoteUser + '\'' +
                ", toRemotePath='" + toRemotePath + '\'' +
                '}';
    }
}
