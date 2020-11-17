package com.thriteen.bgd.usersync.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Lph
 * @Date: 2020/11/8 20:29
 * @Function:
 * @Version 1.0
 */
@TableName(value = "cluster_ips")
public class ClusterIpsEntity {
    /**
     自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
      服务器ip
     */
    
    private String ipAddr;
    /**
      服务器的简称
     */
    private String shortName;
    /**
     服务器全称
     */
    private String fullName;
    /**
     * 远程登陆用户名
     */
    private String userName;
    /**
     远程用户登录密码
     */
    private String userPasswd;
    /**
     远程 服务的免密登录私钥文件按
     */
    private String userKeyfile;
    /**
     远程的端口
     */
    private Integer userPort;
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

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getUserKeyfile() {
        return userKeyfile;
    }

    public void setUserKeyfile(String userKeyfile) {
        this.userKeyfile = userKeyfile;
    }

    public Integer getUserPort() {
        return userPort;
    }

    public void setUserPort(Integer userPort) {
        this.userPort = userPort;
    }
}
