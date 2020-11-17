package com.thriteen.bgd.usersync.entity.dto.ldap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Lph
 * @Date: 2020/11/14 22:43
 * @Function:  创建用户所需要的实体类
 * @Version 1.0
 */
@Component
@PropertySource(value = {"classpath:myconfig.properties"})
public class LdapUserDto {
    /**
     *  givenname，显示的名字，可修改，姓
     */
  private String givenNamePro;
  private String givenNameValue;
    /**
     * Second Name， 第二个名字，非姓氏
     */
  private String snPro;
  private String snValue;
    /**
     * 用户密码
     */
  private String userpasswordPro;
  private String userpasswordValue;
    /**
     * 用户名，用于登录
     */
  private String uidnumberPro;
  private String uidnumbeerValue;
    /**
     * 用户所属组
     */
  private String gidnumberPro;
  private String gidnumberValue;
    /**
     * 用户类别
     */
  private String objectclassPro;
  private String objectclassValueOne;
  private String objectclassValueTwo;
  private String objectclassValueThree;
    /**
     * 用户的id编号
     */
  @Value("${myconfig.ldap.default-user-property}")
  private String uidPro;
  private String uidValue;
    /**
     * 用户名的全称，包括姓氏
     */
  private String cnPro;
  private String cnValue;
    /**
     * 用户所在目录
     */
  private String homedirectoryPro;
  private String homedirectoryValue;

    /**
     * 用户的shell
     */
    private String loginshellPro;
    private String loginshellValue;

    /**
     *  以下几个变量，不是Ldap User的属性，是为了构建插入的元素
     *  成员的标识uid
     */
    private String uid;
    /**
     * 基础域，如：ou=users,dc=bigdata,dc=com
     */
    private String baseDn;

    /**
     * 用户名
     */
    private String userName;

    public LdapUserDto() {
        this.cnPro = "cn";
        this.homedirectoryPro = "homedirectory";
        this.objectclassPro = "objectclass";
        this.gidnumberPro = "gidnumber";
        this.uidnumberPro = "uidnumber";
        this.userpasswordPro = "userpassword";
        this.snPro = "sn";
        this.givenNamePro = "givenname";
        this.loginshellPro = "loginshell";
        this.objectclassValueOne = "inetOrgPerson";
        this.objectclassValueTwo = "posixAccount";
        this.objectclassValueThree = "top";
        this.loginshellValue="/bin/sh";
        this.userName = this.uidValue;
        if(this.uidPro == null){
            this.uidPro = "uid";
        }
    }

    public String getGivenNamePro() {
        return givenNamePro;
    }

    public void setGivenNamePro(String givenNamePro) {
        this.givenNamePro = givenNamePro;
    }

    public String getGivenNameValue() {
        return givenNameValue;
    }

    public void setGivenNameValue(String givenNameValue) {
        this.givenNameValue = givenNameValue;
    }

    public String getSnPro() {
        return snPro;
    }

    public void setSnPro(String snPro) {
        this.snPro = snPro;
    }

    public String getSnValue() {
        return snValue;
    }

    public void setSnValue(String snValue) {
        this.snValue = snValue;
    }

    public String getUserpasswordPro() {
        return userpasswordPro;
    }

    public void setUserpasswordPro(String userpasswordPro) {
        this.userpasswordPro = userpasswordPro;
    }

    public String getUserpasswordValue() {
        return userpasswordValue;
    }

    public void setUserpasswordValue(String userpasswordValue) {
        this.userpasswordValue = userpasswordValue;
    }

    public String getUidnumberPro() {
        return uidnumberPro;
    }

    public void setUidnumberPro(String uidnumberPro) {
        this.uidnumberPro = uidnumberPro;
    }

    public String getUidnumbeerValue() {
        return uidnumbeerValue;
    }

    public void setUidnumbeerValue(String uidnumbeerValue) {
        this.uidnumbeerValue = uidnumbeerValue;
    }

    public String getGidnumberPro() {
        return gidnumberPro;
    }

    public void setGidnumberPro(String gidnumberPro) {
        this.gidnumberPro = gidnumberPro;
    }

    public String getGidnumberValue() {
        return gidnumberValue;
    }

    public void setGidnumberValue(String gidnumberValue) {
        this.gidnumberValue = gidnumberValue;
    }

    public String getObjectclassPro() {
        return objectclassPro;
    }

    public void setObjectclassPro(String objectclassPro) {
        this.objectclassPro = objectclassPro;
    }

    public String getObjectclassValueOne() {
        return objectclassValueOne;
    }

    public void setObjectclassValueOne(String objectclassValueOne) {
        this.objectclassValueOne = objectclassValueOne;
    }

    public String getObjectclassValueTwo() {
        return objectclassValueTwo;
    }

    public void setObjectclassValueTwo(String objectclassValueTwo) {
        this.objectclassValueTwo = objectclassValueTwo;
    }

    public String getObjectclassValueThree() {
        return objectclassValueThree;
    }

    public void setObjectclassValueThree(String objectclassValueThree) {
        this.objectclassValueThree = objectclassValueThree;
    }

    public String getUidPro() {
        return uidPro;
    }

    public void setUidPro(String uidPro) {
        this.uidPro = uidPro;
    }

    public String getUidValue() {
        return uidValue;
    }

    public void setUidValue(String uidValue) {
        this.uidValue = uidValue;
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

    public String getHomedirectoryPro() {
        return homedirectoryPro;
    }

    public void setHomedirectoryPro(String homedirectoryPro) {
        this.homedirectoryPro = homedirectoryPro;
    }

    public String getHomedirectoryValue() {
        return homedirectoryValue;
    }

    public void setHomedirectoryValue(String homedirectoryValue) {
        this.homedirectoryValue = homedirectoryValue;
    }

    public String getLoginshellPro() {
        return loginshellPro;
    }

    public void setLoginshellPro(String loginshellPro) {
        this.loginshellPro = loginshellPro;
    }

    public String getLoginshellValue() {
        return loginshellValue;
    }

    public void setLoginshellValue(String loginshellValue) {
        this.loginshellValue = loginshellValue;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBaseDn() {
        return baseDn;
    }

    public void setBaseDn(String baseDn) {
        this.baseDn = baseDn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String[] selectArrayAttributes(){
        String[] array = new String[10];
        array[0] = this.uidPro;
        array[1] = this.userpasswordPro;
        array[2] = this.uidnumberPro;
        array[3] = this.gidnumberPro;
        array[4] = this.cnPro;
        array[5] = this.snPro;
        array[6] = this.loginshellPro;
        array[7] = this.homedirectoryPro;
        array[8] = this.objectclassPro;
        array[9] = this.givenNamePro;
        return array;
    }
}
