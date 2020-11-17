package com.thriteen.bgd.usersync.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Lph
 * @Date: 2020/11/8 20:15
 * @Function:
 * @Version 1.0
 */
@TableName(value = "user_kerberos")
public class UserKerberosEntity {
    /**
     自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     所在机器
     */
    private String userIp;
    /**
     Kerberos用户的简称，如admin,liph/admin
     */
    private String userShortName;
    /**
     Kerberos用户的密码
     */
    private String userPasswd;
    /**
     用户的keyFile所在路径
     */
    private String userKeyFile;
    /**
     用户的全名，如liph@BIGDATA.COM
     */
    private String userFullName;
    /**
     用户的是否是管理员，1代表是管理员，0标识不是管理员
     */
    private String userIsAdmin;
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

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserShortName() {
        return userShortName;
    }

    public void setUserShortName(String userShortName) {
        this.userShortName = userShortName;
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

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(String userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
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
}
