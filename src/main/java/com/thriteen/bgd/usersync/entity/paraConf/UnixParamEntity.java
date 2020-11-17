package com.thriteen.bgd.usersync.entity.paraConf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Lph
 * @Date: 2020/11/12 23:00
 * @Function:
 * @Version 1.0
 */
@Component
@PropertySource(value = {"classpath:myconfig.properties"})
public class UnixParamEntity {
    @Value("${myconfig.remote.ssh-timeout.connectout}")
    private Integer connectTimeout;
    @Value("${myconfig.remote.ssh-timeout.kexout}")
    private Integer kexTimeOut;
    @Value("${myconfig.remote.ssh-port}")
    private Integer port;
    @Value("${myconfig.remote.ssh.keypath}")
    private String sshKeyPath;
    @Value("${myconfig.remote.hosts-file}")
    private String hostsFilePath;
    @Value("${myconfig.remote.cluster-passwd}")
    private String clusterPasswd;
    @Value("${myconfig.remote.cluster-sync-passwd}")
    private String clusterSyncPasswd;
    @Value("${myconfig.default.user}")
    private String userFlag;

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getKexTimeOut() {
        return kexTimeOut;
    }

    public void setKexTimeOut(Integer kexTimeOut) {
        this.kexTimeOut = kexTimeOut;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSshKeyPath() {
        return sshKeyPath.replace(" ","");
    }

    public void setSshKeyPath(String sshKeyPath) {
        this.sshKeyPath = sshKeyPath;
    }

    public String getHostsFilePath() {
        return hostsFilePath;
    }

    public void setHostsFilePath(String hostsFilePath) {
        this.hostsFilePath = hostsFilePath;
    }

    public String getClusterPasswd() {
        return clusterPasswd;
    }

    public void setClusterPasswd(String clusterPasswd) {
        this.clusterPasswd = clusterPasswd;
    }

    public String getClusterSyncPasswd() {
        return clusterSyncPasswd;
    }

    public void setClusterSyncPasswd(String clusterSyncPasswd) {
        this.clusterSyncPasswd = clusterSyncPasswd;
    }

    public String getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag;
    }
}
