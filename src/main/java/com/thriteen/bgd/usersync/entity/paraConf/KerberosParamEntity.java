package com.thriteen.bgd.usersync.entity.paraConf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Lph
 * @Date: 2020/11/16 16:53
 * @Function:
 * @Version 1.0
 */
@Component
@PropertySource(value = {"classpath:myconfig.properties"})
public class KerberosParamEntity {
    /**
     * 配置文件中的连接名字
     */
    @Value("${myconfig.kerberos.connect-name}")
    private String kerberosConnectName;
    /**
     * 是否同步管理员账号
     */
    @Value("${myconfig.kerberos.sysn-admin}")
    private String kerberosSyncAdmin;
    /**
     * 是否同步管理员账号
     */
    @Value("${myconfig.kerberos.server-ip}")
    private String kerberosServerIp;

    /**
     * Kerberos 操作用户
     */
    private String kerberosServerUser;
    /**
     * Kerberos 操作用户和密码
     */
    private String kerberosServerUserPasswd;

    /**
     * Kerberos 操作用户
     */
    @Value("${myconfig.kerberos.server-default-admin-principal}")
    private boolean kerberosDefaultAdminPrincipal;
    /**
     * Kerberos 的域名
     */
    @Value("${myconfig.kerberos.server-domain}")
    private String serverDomain;

    /**
     *  用户对应的keytab路径
     */
    @Value("${myconfig.kerberos.keytab-path}")
    private String keytabPath;

    public String getKerberosConnectName() {
        return kerberosConnectName;
    }

    public void setKerberosConnectName(String kerberosConnectName) {
        this.kerberosConnectName = kerberosConnectName;
    }

    public String getKerberosSyncAdmin() {
        return kerberosSyncAdmin;
    }

    public void setKerberosSyncAdmin(String kerberosSyncAdmin) {
        this.kerberosSyncAdmin = kerberosSyncAdmin;
    }

    public String getKerberosServerIp() {
        return kerberosServerIp;
    }

    public void setKerberosServerIp(String kerberosServerIp) {
        this.kerberosServerIp = kerberosServerIp;
    }

    public String getKerberosServerUser() {
        return kerberosServerUser;
    }

    public void setKerberosServerUser(String kerberosServerUser) {
        this.kerberosServerUser = kerberosServerUser;
    }

    public String getKerberosServerUserPasswd() {
        return kerberosServerUserPasswd;
    }

    public void setKerberosServerUserPasswd(String kerberosServerUserPasswd) {
        this.kerberosServerUserPasswd = kerberosServerUserPasswd;
    }

    public boolean isKerberosDefaultAdminPrincipal() {
        return kerberosDefaultAdminPrincipal;
    }

    public void setKerberosDefaultAdminPrincipal(boolean kerberosDefaultAdminPrincipal) {
        this.kerberosDefaultAdminPrincipal = kerberosDefaultAdminPrincipal;
    }

    public String getServerDomain() {
        return serverDomain;
    }

    public void setServerDomain(String serverDomain) {
        this.serverDomain = serverDomain;
    }

    public String getKeytabPath() {
        return keytabPath;
    }

    public void setKeytabPath(String keytabPath) {
        this.keytabPath = keytabPath;
    }
}
