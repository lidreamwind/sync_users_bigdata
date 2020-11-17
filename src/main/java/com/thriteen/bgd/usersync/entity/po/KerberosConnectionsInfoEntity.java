package com.thriteen.bgd.usersync.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Lph
 * @Date: 2020/11/8 20:23
 * @Function:
 * @Version 1.0
 */
@TableName(value = "kerberos_connections_info")
public class KerberosConnectionsInfoEntity {
    /**
     自增ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     Kerberos的连接名称
     */
    private String kerberosConnectName;
    /**
     Kerberos服务所在的ip
     */
    private String kerberosServerIp;
    /**
     Kerberos域名，
     */
    private String kerberosDomain;
    /**
     Kerberos的keyTab文件所在路径
     */
    private String kerberosKeytabPath;
    /**
     Kerberos是否同步管理员
     */
    private String kerberosSyncAdmin;
    /**
     Kerberos默认管理员规则  kerberos_default_admin_principal
     */
    private String kerberosDefaultAdminPrincipal;
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

    public String getKerberosConnectName() {
        return kerberosConnectName;
    }

    public void setKerberosConnectName(String kerberosConnectName) {
        this.kerberosConnectName = kerberosConnectName;
    }

    public String getKerberosServerIp() {
        return kerberosServerIp;
    }

    public void setKerberosServerIp(String kerberosServerIp) {
        this.kerberosServerIp = kerberosServerIp;
    }

    public String getKerberosDomain() {
        return kerberosDomain;
    }

    public void setKerberosDomain(String kerberosDomain) {
        this.kerberosDomain = kerberosDomain;
    }

    public String getKerberosKeytabPath() {
        return kerberosKeytabPath;
    }

    public void setKerberosKeytabPath(String kerberosKeytabPath) {
        this.kerberosKeytabPath = kerberosKeytabPath;
    }

    public String getKerberosSyncAdmin() {
        return kerberosSyncAdmin;
    }

    public void setKerberosSyncAdmin(String kerberosSyncAdmin) {
        this.kerberosSyncAdmin = kerberosSyncAdmin;
    }

    public String getKerberosDefaultAdminPrincipal() {
        return kerberosDefaultAdminPrincipal;
    }

    public void setKerberosDefaultAdminPrincipal(String kerberosDefaultAdminPrincipal) {
        this.kerberosDefaultAdminPrincipal = kerberosDefaultAdminPrincipal;
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

    @Override
    public String toString() {
        return "KerberosConnectionsInfoEntity{" +
                "kerberosConnectName='" + kerberosConnectName + '\'' +
                ", kerberosServerIp='" + kerberosServerIp + '\'' +
                ", kerberosDomain='" + kerberosDomain + '\'' +
                ", kerberosKeytabPath='" + kerberosKeytabPath + '\'' +
                ", kerberosSyncAdmin='" + kerberosSyncAdmin + '\'' +
                ", kerberosDefaultAdminPrincipal='" + kerberosDefaultAdminPrincipal + '\'' +
                '}';
    }
}
