package com.thriteen.bgd.usersync.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Lph
 * @Date: 2020/11/8 20:06
 * @Function:
 * @Version 1.0
 */
@TableName(value = "user_ldap")
public class UserLdapEntity {
    /**
     * 自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * ldap用户名，uid=后面的
     */
    private String userName;
    /**
     * ldap用户的密码
     */
    private String userPasswd;
    /**
     * ldap用户所在域，如：ou=users,dc=bigdta,dc=com
     */
    private String userDomain;
    /**
     * 用户对应的关键字uid
     */
    private String userUid;
    /**
     * 数据来源，系统初始化:sysinit和用户创建:usercreate
     */
    private String userFlag;
    /**
     * 用户所在Ldap服务
     */
    private String userLdapIp;
    /**
     * 创建时间
     */
    //注解@JsonFormat主要是后台到前台的时间格式的转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //注解@DataFormAT主要是前后到后台的时间格式的转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 创建用户
     */
    private String createUser;
    /**
     * 更新时间
     */
    //注解@JsonFormat主要是后台到前台的时间格式的转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //注解@DataFormAT主要是前后到后台的时间格式的转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    /**
     * 更新用户
     */
    private String updateUser;
    /**
     * 备注,整体，uid=user,ou=users,dc=bigdata,dc=com
     */
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser(String userFlag) {
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

    public String getUserLdapIp() {
        return userLdapIp;
    }

    public void setUserLdapIp(String userLdapIp) {
        this.userLdapIp = userLdapIp;
    }

    public String getCreateUser() {
        return createUser;
    }
}
