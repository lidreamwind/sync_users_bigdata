package com.thriteen.bgd.usersync.entity.dto.kerberos;

/**
 * @Author: Lph
 * @Date: 2020/11/16 22:12
 * @Function:  用于Kerberos初始化
 * @Version 1.0
 */
public class KerberosInitDto {
    /**
     * Kerberos 服务所在的地址
     */
    private String ip;
    /**
     * Kerberos服务器的连接端口
     */
    private Integer port;
    /**
     * Kerberos服务器的连接用户名
     */
    private String remoteUser;
    /**
     * Kerberos服务器的连接密码
     */
    private String remotePasswd;
    /**
     * 生成keytab文件的路径
     */
    private String keyTabPath;
    /**
     * Kerberos的服务域名，如:liph/admin@BIGDATA.COM，其中BIGDATA.COM是此部分规则
     */
    private String kerberosDomain;
    /**
     * 免密登录的文件位置
     */
    private String sshKeyFile;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getRemotePasswd() {
        return remotePasswd;
    }

    public void setRemotePasswd(String remotePasswd) {
        this.remotePasswd = remotePasswd;
    }

    public String getKeyTabPath() {
        return keyTabPath;
    }

    public void setKeyTabPath(String keyTabPath) {
        this.keyTabPath = keyTabPath;
    }

    public String getKerberosDomain() {
        return kerberosDomain;
    }

    public void setKerberosDomain(String kerberosDomain) {
        this.kerberosDomain = kerberosDomain;
    }

    public String getSshKeyFile() {
        return sshKeyFile;
    }

    public void setSshKeyFile(String sshKeyFile) {
        this.sshKeyFile = sshKeyFile;
    }
}
