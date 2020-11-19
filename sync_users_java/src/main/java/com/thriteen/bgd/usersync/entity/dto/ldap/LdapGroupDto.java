package com.thriteen.bgd.usersync.entity.dto.ldap;

/**
 * @Author: Lph
 * @Date: 2020/11/14 22:44
 * @Function:  用户分组，有些许默认值，一般说来，在组织单位之下
 * @Version 1.0
 */
public class LdapGroupDto {
    /**
     *  显示名字，分组名称
     */
    private String cnPro;
    private String cnValue;
    /**
     * 组编号
     */
    private String gidNumber;
    private String gidNumberPro;
    /**
     * object class
     */
    private String objectClassValueOne;
    private String objectClassValueTwo;
    private String objectClassPro;

    public LdapGroupDto() {
        this.gidNumber = gidNumber;
        this.cnPro = "cn";
        this.gidNumberPro="gidnumber";
        this.objectClassPro="objectclass";
        this.objectClassValueOne="posixGroup";
        this.objectClassValueTwo="top";
    }

    public String getCnPro() {
        return cnPro;
    }

    public void setCnPro(String cnPro) {
        this.cnPro = cnPro;
    }

    public String getCnValue() {
        return cnValue;
    }

    public void setCnValue(String cnValue) {
        this.cnValue = cnValue;
    }

    public String getGidNumber() {
        return gidNumber;
    }

    public void setGidNumber(String gidNumber) {
        this.gidNumber = gidNumber;
    }

    public String getGidNumberPro() {
        return gidNumberPro;
    }

    public void setGidNumberPro(String gidNumberPro) {
        this.gidNumberPro = gidNumberPro;
    }

    public String getObjectClassValueOne() {
        return objectClassValueOne;
    }

    public void setObjectClassValueOne(String objectClassValueOne) {
        this.objectClassValueOne = objectClassValueOne;
    }

    public String getObjectClassValueTwo() {
        return objectClassValueTwo;
    }

    public void setObjectClassValueTwo(String objectClassValueTwo) {
        this.objectClassValueTwo = objectClassValueTwo;
    }

    public String getObjectClassPro() {
        return objectClassPro;
    }

    public void setObjectClassPro(String objectClassPro) {
        this.objectClassPro = objectClassPro;
    }
}