package com.thriteen.bgd.usersync.entity.dto.ldap;

/**
 * @Author: Lph
 * @Date: 2020/11/15 10:03
 * @Function:  增加Ldap的组织，Organization Unit
 * @Version 1.0
 */
public class LdapOrganizationUnitDto {
    /**
     * 组织属性，
     */
    private String ouValue;
    /**
     * 属性名称
     */
    private String ouPro;
    /**
     * ObjectClass对应的两个值
     */
    private String objectClassValueOne;
    private String objectClassValueTwo;
    private String objectClassPro;

    public LdapOrganizationUnitDto() {
        this.ouPro="ou";
        this.objectClassValueTwo = "top";
        this.objectClassValueOne = "organizationalUnit";
        this.objectClassPro = "objectClass";
    }

    public String getOuValue() {
        return ouValue;
    }

    public void setOuValue(String ouValue) {
        this.ouValue = ouValue;
    }

    public String getOuPro() {
        return ouPro;
    }

    public void setOuPro(String ouPro) {
        this.ouPro = ouPro;
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
