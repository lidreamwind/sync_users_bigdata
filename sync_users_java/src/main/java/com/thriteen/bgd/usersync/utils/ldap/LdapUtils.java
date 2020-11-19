package com.thriteen.bgd.usersync.utils.ldap;

import com.thriteen.bgd.usersync.entity.dto.ldap.LdapUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @Author: Lph
 * @Date: 2020/11/14 17:36
 * @Function: 因为同步用户，会指定ldap分组，所以只需考虑增加、修改、删除用户即可。
 * @Version 1.0
 */
public class LdapUtils {
    /**
     * LDAP 连接地址
     */
    private String ldapUrl;
    /**
     * LDAP连接管理员用户
     */
    private String ldapAdminUser;
    /**
     * LDAP连接安全级别，默认为simple
     */
    private String ldapSecurityLevel;
    /**
     * LDAP连接管理员用户对应密码
     */
    private String ldapAdminUserPassword;
    /**
     * LDAP连接管理员用户的连接类
     */
    private String ldapFactoryClass;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private DirContext dirContext = null;
    private LdapContext ldapContext = null;
    private boolean isConnect = false;
    /**
     * 构造函数，必须要
     * @param ldapUrl
     * @param ldapAdminUser
     * @param ldapSecurityLevel
     * @param ldapAdminUserPassword
     * @param ldapFactoryClass
     */
    public LdapUtils(String ldapUrl, String ldapAdminUser, String ldapSecurityLevel, String ldapAdminUserPassword, String ldapFactoryClass) {
        this.ldapUrl = ldapUrl;
        this.ldapAdminUser = ldapAdminUser;
        this.ldapSecurityLevel = ldapSecurityLevel;
        this.ldapAdminUserPassword = ldapAdminUserPassword;
        this.ldapFactoryClass = ldapFactoryClass;
    }

    /**
     * 初始化 LDAP连接
     * @return
     */
    private boolean init(){
        Hashtable<String,String> env = new Hashtable<>();
        env.put(Context.PROVIDER_URL,this.ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION,this.ldapSecurityLevel);
        env.put(Context.SECURITY_PRINCIPAL,this.ldapAdminUser);
        env.put(Context.SECURITY_CREDENTIALS,this.ldapAdminUserPassword);
        env.put(Context.INITIAL_CONTEXT_FACTORY,this.ldapFactoryClass);
        try {
            this.dirContext =  new InitialDirContext(env);
            this.ldapContext = new InitialLdapContext(env, null);
            this.logger.info("-----Ldap connection creates successfully.");
            this.isConnect = true;
            return true;
        } catch (NamingException e) {
            this.logger.info("-----can not connect to Ldap Server.");
            e.printStackTrace();
            this.isConnect = false;
            return false;
        }
    }

    /**
     *  给Ldap增加成员
     * @param ldapUserDto Ldap的参数部分
     * @return
     */
    public boolean addUserMember(LdapUserDto ldapUserDto){
        if(!this.isConnect){
            this.init();
        }
        try {
            BasicAttributes attributes = new BasicAttributes();
            // ObjectClass
            BasicAttribute attribute = new BasicAttribute(ldapUserDto.getObjectclassPro());
            attribute.add(ldapUserDto.getObjectclassValueOne());
            attribute.add(ldapUserDto.getObjectclassValueTwo());
            attribute.add(ldapUserDto.getObjectclassValueThree());
            attributes.put(attribute);
            //cn
            attributes.put(ldapUserDto.getCnPro(),ldapUserDto.getCnValue());
            //gidnumber
            attributes.put(ldapUserDto.getGidnumberPro(),ldapUserDto.getGidnumberValue());
            //givenname
            attributes.put(ldapUserDto.getGivenNamePro(),ldapUserDto.getGivenNameValue());
            // shell
            attributes.put(ldapUserDto.getLoginshellPro(),ldapUserDto.getLoginshellValue());
            //homedirectory
            attributes.put(ldapUserDto.getHomedirectoryPro(),ldapUserDto.getHomedirectoryValue());
            //uidNumber
            attributes.put(ldapUserDto.getUidnumberPro(),ldapUserDto.getUidnumbeerValue());
            //sn
            attributes.put(ldapUserDto.getSnPro(),ldapUserDto.getSnValue());
            //uid
            attributes.put(ldapUserDto.getUidPro(),ldapUserDto.getUidValue());
            // userpassword
            attributes.put(ldapUserDto.getUserpasswordPro(),ldapUserDto.getUserpasswordValue());

            String userName = String.format("%s=%s,%s",ldapUserDto.getUid(),ldapUserDto.getUserName(),ldapUserDto.getBaseDn());
            this.ldapContext.createSubcontext(userName,attributes);
            return true;
        } catch (NamingException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 修改用户的属性
     */
    public boolean modifyUserPassword(LdapUserDto ldapUserDto){
        if(!this.isConnect){
            this.init();
        }
        ModificationItem[] modificationItems = new ModificationItem[1];
        String dn = null;
        if(ldapUserDto.getUid() == null || ldapUserDto.getUserName() == null) {
            dn = ldapUserDto.getBaseDn();
        }else {
            dn = String.format("%s=%s,%s", ldapUserDto.getUid(), ldapUserDto.getUserName(), ldapUserDto.getBaseDn());
        }
        if(!dn.contains("dc") && !dn.contains(ldapUserDto.getUidPro())){
            this.logger.error(String.format("-----the dn is not in right:%s", dn));
            return false;
        }

        // 修改属性如下
            //givenName
//        BasicAttribute givenName = new BasicAttribute(ldapUserDto.getGivenNamePro(),ldapUserDto.getGivenNameValue());
//        modificationItems[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, givenName);
            // userpassword
        BasicAttribute userPasswd = new BasicAttribute(ldapUserDto.getUserpasswordPro(),ldapUserDto.getUserpasswordValue());
        modificationItems[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, userPasswd);
            //gidNumber
//        BasicAttribute gidNumber = new BasicAttribute(ldapUserDto.getGidnumberPro(),ldapUserDto.getGidnumberValue());
//        modificationItems[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, gidNumber);
        //  其他属性，，按照如上方式依次添加即可
        try {
            this.ldapContext.modifyAttributes(dn,modificationItems);
            this.logger.info(String.format("-----modify user:%s attributes successfully.", dn));
            return true;
        } catch (NamingException e) {
            this.logger.info(String.format("-----to modify user:%s attributes.", dn));
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 删除用户
     */
    public boolean deleteUserFromUser(LdapUserDto ldapUserDto){
        if(!this.isConnect){
            this.init();
        }
        String dn = null;
        if(ldapUserDto.getUid() == null || ldapUserDto.getUserName() == null) {
            dn = ldapUserDto.getBaseDn();
        }else {
            dn = String.format("%s=%s,%s", ldapUserDto.getUid(), ldapUserDto.getUserName(), ldapUserDto.getBaseDn());
        }
        if(!dn.contains("dc") && !dn.contains(ldapUserDto.getUidPro())){
            this.logger.error(String.format("-----the dn is not in right:%s", dn));
            return false;
        }
        try {
            List<LdapUserDto> ldapUserDtos = this.selectAllUser(ldapUserDto);
            if(ldapUserDtos != null &&ldapUserDtos.size()>=1) {
                this.ldapContext.destroySubcontext(dn);
                this.logger.info(String.format("-----delete user:%s successfully.", dn));
            }else {
                this.logger.info(String.format("-----the user:%s does not exists.", dn));
                return false;
            }
            if(ldapUserDtos != null && this.selectAllUser(ldapUserDto).size()>=1) {
                this.logger.error(String.format("-----failed to delete user:%s.", dn));
                this.logger.error(String.format("%s should contain %s attributes.", dn,ldapUserDto.getUidPro()));
                return false;
            }
            return true;
        } catch (NamingException e) {
            this.logger.info(String.format("-----failed to delete user:%s.", dn));
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 查找所有成员，当LdapUserDto的baseDn具体到某个人时，则是查询此人的具体信息。
     */
    public List<LdapUserDto> selectAllUser(LdapUserDto ldapUserDto){
        if(!this.isConnect){
            this.init();
        }
        List<LdapUserDto> rs = new ArrayList<>();
        String filter = String.format("(&(%s=*)(%s=*))", ldapUserDto.getObjectclassPro(),ldapUserDto.getUidPro());
        String[] attrArray = ldapUserDto.selectArrayAttributes();
        SearchControls searchControls = new SearchControls();
        // 设置搜索范围
        searchControls.setSearchScope(2);
        searchControls.setReturningAttributes(attrArray);
        try {
            NamingEnumeration<SearchResult> rsUser = this.ldapContext.search(ldapUserDto.getBaseDn(),filter,searchControls);
            while (rsUser.hasMore()){
                SearchResult next = rsUser.next();
                NamingEnumeration<? extends Attribute> attrs = next.getAttributes().getAll();
                LdapUserDto newLdapUser = new LdapUserDto();
                // 配置属性，然后进行赋值
                while(attrs.hasMore()) {
                    Attribute attr = attrs.next();
                    //givenname
                    if (ldapUserDto.getGivenNamePro().equalsIgnoreCase(attr.getID())){
                        newLdapUser.setGivenNameValue((String) attr.get());
                        // sn
                    }else if(ldapUserDto.getSnPro().equalsIgnoreCase(attr.getID())){
                        newLdapUser.setSnValue((String) attr.get());
                        // loginShell
                    }else if(ldapUserDto.getLoginshellPro().equalsIgnoreCase(attr.getID())){
                        newLdapUser.setLoginshellValue((String) attr.get());
                        // userpassword
                    }else if(ldapUserDto.getUserpasswordPro().equalsIgnoreCase(attr.getID())){
                        Object value = attr.get();
                        newLdapUser.setUserpasswordValue(new String((byte [])value));
                        //uidnumber
                    }else if(ldapUserDto.getUidnumberPro().equalsIgnoreCase(attr.getID())){
                        newLdapUser.setUidnumbeerValue((String) attr.get());
                        //gidnumber
                    }else if(ldapUserDto.getGidnumberPro().equalsIgnoreCase(attr.getID())){
                        newLdapUser.setGidnumberValue((String) attr.get());
                        //uid
                    }else if(ldapUserDto.getUidPro().equalsIgnoreCase(attr.getID())){
                        newLdapUser.setUidValue((String) attr.get());
                        // cn
                    }else if(ldapUserDto.getCnPro().equalsIgnoreCase(attr.getID())){
                        newLdapUser.setCnValue((String) attr.get());
                        //homedirectory
                    }else if(ldapUserDto.getHomedirectoryPro().equalsIgnoreCase(attr.getID())){
                        newLdapUser.setHomedirectoryValue((String) attr.get());
                        //objectclass
                    }else if(ldapUserDto.getObjectclassPro().equalsIgnoreCase(attr.getID())){
                        String[] s = attr.get().toString().replace(" ", "").split(",");
                        if(s.length==3) {
                            ldapUserDto.setObjectclassValueOne(s[0]);
                            ldapUserDto.setObjectclassValueTwo(s[1]);
                            ldapUserDto.setObjectclassValueThree(s[2]);
                        }else if(s.length==2){
                            ldapUserDto.setObjectclassValueOne(s[0]);
                            ldapUserDto.setObjectclassValueTwo(s[1]);
                        }else if(s.length==1){
                            ldapUserDto.setObjectclassValueOne(s[0]);
                        }
                    }
                }
                rs.add(newLdapUser);
            }
            return rs;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

