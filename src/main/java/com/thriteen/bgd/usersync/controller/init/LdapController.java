package com.thriteen.bgd.usersync.controller.init;

import com.thriteen.bgd.usersync.entity.vo.ldap.LdapConnectionsUrlVo;
import com.thriteen.bgd.usersync.services.LdapService;
import com.thriteen.bgd.usersync.utils.ExceptionEnum;
import com.thriteen.bgd.usersync.utils.WebResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lph
 * @Date: 2020/11/14 18:42
 * @Function:
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "ldap")
public class LdapController {
    @Autowired
    private LdapService ldapService;

    /**
     * 初始化连接，从配置文件中获取ldap连接，并插入数据库中
     * @return
     */
    @RequestMapping(value = "initUrls", method = RequestMethod.GET)
    public WebResult initLdapInfoFromConfiguration(){
        Integer b = this.ldapService.initLdapUrls();
        if(b == 1){
            return new WebResult(ExceptionEnum.LDAP_INIT_SUCCESS.getCode(),ExceptionEnum.LDAP_INIT_SUCCESS.getMessage(),"successfully.");
        }else if(b == 2){
            return new WebResult(ExceptionEnum.LDAP_INIT_EXISTS.getCode(),ExceptionEnum.LDAP_INIT_EXISTS.getMessage(),"failed.");
        }else {
            return new WebResult(ExceptionEnum.LDAP_INIT_FAILED.getCode(),ExceptionEnum.LDAP_INIT_FAILED.getMessage(),"error.");
        }
    }
    /**
     * 从前台插入数据
     */
    @RequestMapping(value = "new", method = RequestMethod.PUT)
    public WebResult insertLdapConnectInfo(@RequestBody  LdapConnectionsUrlVo ldapConnectionsUrlVo){
        return this.ldapService.insertLdapConnectionUrlInfo(ldapConnectionsUrlVo);
    }
    /**
     * 初始化 Ldap用户到数据库中
     */
    @RequestMapping(value = "initusers",method = RequestMethod.GET)
    public WebResult initUsersFromLdapServerToMysql(@Param("connectname") String connectName){
        Integer integer = this.ldapService.initLdapUsersFromLdapServerToDbmeta(connectName);
        if(integer==0){
            return new WebResult(ExceptionEnum.LDAP_NO_URL.getCode(),ExceptionEnum.LDAP_NO_URL.getMessage(),"failed");
        }else if(integer == -1){
            return new WebResult(ExceptionEnum.LDAP_NO_USER.getCode(),ExceptionEnum.LDAP_NO_USER.getMessage(),"failed");
        }else {
            return new WebResult(integer);
        }
    }

}
