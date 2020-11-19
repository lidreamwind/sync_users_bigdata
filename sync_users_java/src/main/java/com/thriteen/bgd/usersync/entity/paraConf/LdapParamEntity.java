package com.thriteen.bgd.usersync.entity.paraConf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Lph
 * @Date: 2020/11/14 17:30
 * @Function:
 * @Version 1.0
 */
@Component
@PropertySource(value = {"classpath:myconfig.properties"})
public class LdapParamEntity {
    @Value("${myconfig.ldap.url}")
    private String ldapUrl;
    @Value("${myconfig.ldap.baseDN}")
    private String baseDN;
    @Value("${myconfig.ldap.admin-user}")
    private String adminUser;
    @Value("${myconfig.ldap.admin-user-password}")
    private String adminPasswd;
    @Value("${myconfig.ldap.factory-class}")
    private String factorClass;
    @Value("${myconfig.ldap.security-level}")
    private String securityLevel;
    @Value("${myconfig.ldap.default-user-property}")
    private String userDefaultProper;
    @Value("${myconfig.ldap.connection-name}")
    private String ldapConnectName;

    public String getLdapUrl() {
        return ldapUrl;
    }

    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }

    public String getBaseDN() {
        return baseDN;
    }

    public void setBaseDN(String baseDN) {
        this.baseDN = baseDN;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public String getAdminPasswd() {
        return adminPasswd;
    }

    public void setAdminPasswd(String adminPasswd) {
        this.adminPasswd = adminPasswd;
    }

    public String getFactorClass() {
        return factorClass;
    }

    public void setFactorClass(String factorClass) {
        this.factorClass = factorClass;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getUserDefaultProper() {
        return userDefaultProper;
    }

    public void setUserDefaultProper(String userDefaultProper) {
        this.userDefaultProper = userDefaultProper;
    }

    public String getLdapConnectName() {
        return ldapConnectName;
    }

    public void setLdapConnectName(String ldapConnectName) {
        this.ldapConnectName = ldapConnectName;
    }
}
