package com.thriteen.bgd.usersync.entity.vo.ldap;

/**
 * @Author: Lph
 * @Date: 2020/11/14 19:27
 * @Function:
 * @Version 1.0
 */
public class LdapConnectionsUrlVo {

    private String ldapUrlName;
    /**
     LDAP连接URL
     */
    private String ldapUrl;
    /**
     LDAP管理员
     */
    private String ldapAdminUser;
    /**
     LDAP管理密码
     */
    private String ldapAdminPasswd;
    /**
     LDAP基础域
     */
    private String ldapBaseDn;
    /**
     LDAP用户属性名称
     */
    private String ldapUserProper;
    /**
     LDAP的工厂类
     */
    private String ldapFactoryClass;
    /**
     LDAP的认证级别
     */
    private String ldapSecurityLevel;

    /**
     创建用户
     */
    private String createUser;
    /**
     备注
     */
    private String comment;

    public String getLdapUrlName() {
        return ldapUrlName;
    }

    public void setLdapUrlName(String ldapUrlName) {
        this.ldapUrlName = ldapUrlName;
    }

    public String getLdapUrl() {
        return ldapUrl;
    }

    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }

    public String getLdapAdminUser() {
        return ldapAdminUser;
    }

    public void setLdapAdminUser(String ldapAdminUser) {
        this.ldapAdminUser = ldapAdminUser;
    }

    public String getLdapAdminPasswd() {
        return ldapAdminPasswd;
    }

    public void setLdapAdminPasswd(String ldapAdminPasswd) {
        this.ldapAdminPasswd = ldapAdminPasswd;
    }

    public String getLdapBaseDn() {
        return ldapBaseDn;
    }

    public void setLdapBaseDn(String ldapBaseDn) {
        this.ldapBaseDn = ldapBaseDn;
    }

    public String getLdapUserProper() {
        return ldapUserProper;
    }

    public void setLdapUserProper(String ldapUserProper) {
        this.ldapUserProper = ldapUserProper;
    }

    public String getLdapFactoryClass() {
        return ldapFactoryClass;
    }

    public void setLdapFactoryClass(String ldapFactoryClass) {
        this.ldapFactoryClass = ldapFactoryClass;
    }

    public String getLdapSecurityLevel() {
        return ldapSecurityLevel;
    }

    public void setLdapSecurityLevel(String ldapSecurityLevel) {
        this.ldapSecurityLevel = ldapSecurityLevel;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "LdapConnectionsUrlVo{" +
                "ldapUrlName='" + ldapUrlName + '\'' +
                ", ldapUrl='" + ldapUrl + '\'' +
                ", ldapAdminUser='" + ldapAdminUser + '\'' +
                ", ldapAdminPasswd='" + ldapAdminPasswd + '\'' +
                ", ldapBaseDn='" + ldapBaseDn + '\'' +
                ", ldapUserProper='" + ldapUserProper + '\'' +
                ", ldapFactoryClass='" + ldapFactoryClass + '\'' +
                ", ldapSecurityLevel='" + ldapSecurityLevel + '\'' +
                ", createUser='" + createUser + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
