package com.thriteen.bgd.usersync.ldap;

import com.thriteen.bgd.usersync.entity.dto.ldap.LdapUserDto;
import com.thriteen.bgd.usersync.utils.ldap.LdapUtils;
import org.junit.Test;

import java.util.List;

/**
 * @Author: Lph
 * @Date: 2020/11/14 17:43
 * @Function:
 * @Version 1.0
 */
public class LdapTest {
    @Test
    public void testLdapSelectAll() {
        String url = "ldap://192.168.233.69:389";
        String baseDn = "dc=bigdata,dc=com";
        String root = "uid=admin,dc=bigdata,dc=com";
        String passwd = "admin";
        String factoryClass = "com.sun.jndi.ldap.LdapCtxFactory";
        String securityLevel = "simple";

        LdapUtils ldapUtils = new LdapUtils(url,root,securityLevel,passwd,factoryClass);
        LdapUserDto ldapUserDto = new LdapUserDto();
        ldapUserDto.setBaseDn("ou=users,dc=bigdata,dc=com");
        List<LdapUserDto> ldapUserDtos = ldapUtils.selectAllUser(ldapUserDto);
        System.out.println(1);
    }
    @Test
    public void testLdapSelectOne() {
        String url = "ldap://192.168.233.69:389";
        String baseDn = "dc=bigdata,dc=com";
        String root = "uid=admin,dc=bigdata,dc=com";
        String passwd = "admin";
        String factoryClass = "com.sun.jndi.ldap.LdapCtxFactory";
        String securityLevel = "simple";

        LdapUtils ldapUtils = new LdapUtils(url,root,securityLevel,passwd,factoryClass);
        LdapUserDto ldapUserDto = new LdapUserDto();
        ldapUserDto.setBaseDn("uid=liph,cn=dev,ou=users,dc=bigdata,dc=com");
        List<LdapUserDto> ldapUserDtos = ldapUtils.selectAllUser(ldapUserDto);
        System.out.println(1);
    }
    @Test
    public void testDelete() {
        String url = "ldap://192.168.233.69:389";
        String baseDn = "dc=bigdata,dc=com";
        String root = "uid=admin,dc=bigdata,dc=com";
        String passwd = "admin";
        String factoryClass = "com.sun.jndi.ldap.LdapCtxFactory";
        String securityLevel = "simple";

        LdapUtils ldapUtils = new LdapUtils(url,root,securityLevel,passwd,factoryClass);
        LdapUserDto ldapUserDto = new LdapUserDto();
        ldapUserDto.setBaseDn("uid=lipenghuai,ou=users,dc=bigdata,dc=com");
        boolean b = ldapUtils.deleteUserFromUser(ldapUserDto);
        System.out.println(1);
    }
    @Test
    public void testModifyPassword() {
        String url = "ldap://192.168.233.69:389";
        String baseDn = "dc=bigdata,dc=com";
        String root = "uid=admin,dc=bigdata,dc=com";
        String passwd = "admin";
        String factoryClass = "com.sun.jndi.ldap.LdapCtxFactory";
        String securityLevel = "simple";

        LdapUtils ldapUtils = new LdapUtils(url,root,securityLevel,passwd,factoryClass);
        LdapUserDto ldapUserDto = new LdapUserDto();
        ldapUserDto.setBaseDn("uid=lpenghuai,ou=users,dc=bigdata,dc=com");
        ldapUserDto.setUid("uid");
        ldapUserDto.setUserpasswordValue("123456");
        boolean b = ldapUtils.modifyUserPassword(ldapUserDto);
        System.out.println(1);
    }
    @Test
    public void getLdapIpFromUrl(){
        String str = "ldap://192.168.233.68/389";
        String[] s = str.replace(" ", "").split("/");
        System.out.println(s[2].substring(0));
    }
}
