package com.thriteen.bgd.usersync.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Lph
 * @Date: 2020/11/14 18:18
 * @Function:
 * @Version 1.0
 */
@TableName(value = "ldap_connections_url")
public class LdapConnectionsUrlEntity {
    /**
     自动增长ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     LDAP连接名称
     */
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
     LDAP服务所在ip服务器
     */
    private String ldapServerIp;
    /**
     创建时间
     */
    //注解@JsonFormat主要是后台到前台的时间格式的转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //注解@DataFormAT主要是前后到后台的时间格式的转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     创建用户
     */
    private String createUser;
    /**
     更新时间
     */
    //注解@JsonFormat主要是后台到前台的时间格式的转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //注解@DataFormAT主要是前后到后台的时间格式的转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    /**
     更新用户
     */
    private String updateUser;
    /**
     备注
     */
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLdapUrlName() {
        return ldapUrlName;
    }

    public void setLdapUrlName(String ldapUrlName) {
        this.ldapUrlName = ldapUrlName;
    }

    public String getLdapServerIp() {
        return ldapServerIp;
    }

    public void setLdapServerIp(String ldapServerIp) {
        this.ldapServerIp = ldapServerIp;
    }

    @Override
    public String toString() {
        return "LdapConnectionsUrlEntity{" +
                "id=" + id +
                ", ldapUrlName='" + ldapUrlName + '\'' +
                ", ldapUrl='" + ldapUrl + '\'' +
                ", ldapAdminUser='" + ldapAdminUser + '\'' +
                ", ldapAdminPasswd='" + ldapAdminPasswd + '\'' +
                ", ldapBaseDn='" + ldapBaseDn + '\'' +
                ", ldapUserProper='" + ldapUserProper + '\'' +
                ", ldapFactoryClass='" + ldapFactoryClass + '\'' +
                ", ldapSecurityLevel='" + ldapSecurityLevel + '\'' +
                ", ldapServerIp='" + ldapServerIp + '\'' +
                ", createDate=" + createDate +
                ", createUser='" + createUser + '\'' +
                ", updateDate=" + updateDate +
                ", updateUser='" + updateUser + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
