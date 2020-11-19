package com.thriteen.bgd.usersync.entity.vo.kerberos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Lph
 * @Date: 2020/11/17 10:34
 * @Function:  从前台获取的数据
 * @Version 1.0
 */
public class KerberosConnectionsInfoVo {
    /**
     Kerberos的连接名称
     */
    private String connectName;
    /**
     Kerberos服务所在的ip
     */
    private String serverIp;
    /**
     Kerberos域名，
     */
    private String domain;
    /**
     Kerberos的keyTab文件所在路径
     */
    private String keyPath;
    /**
     Kerberos是否同步管理员
     */
    private String syncAdmin;
    /**
     Kerberos默认管理员规则  kerberos_default_admin_principal
     */
    private String defaultPrincipal;
    /**
     创建时间
     */
    //注解@JsonFormat主要是后台到前台的时间格式的转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //注解@DataFormAT主要是前后到后台的时间格式的转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     更新时间
     */
    //注解@JsonFormat主要是后台到前台的时间格式的转换
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //注解@DataFormAT主要是前后到后台的时间格式的转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    /**
     备注
     */
    private String comment;

    public String getConnectName() {
        return connectName;
    }

    public void setConnectName(String connectName) {
        this.connectName = connectName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getSyncAdmin() {
        return syncAdmin;
    }

    public void setSyncAdmin(String syncAdmin) {
        this.syncAdmin = syncAdmin;
    }

    public String getDefaultPrincipal() {
        return defaultPrincipal;
    }

    public void setDefaultPrincipal(String defaultPrincipal) {
        this.defaultPrincipal = defaultPrincipal;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
