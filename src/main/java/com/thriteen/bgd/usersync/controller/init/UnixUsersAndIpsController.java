package com.thriteen.bgd.usersync.controller.init;

import com.thriteen.bgd.usersync.services.UnixUserAndIpService;
import com.thriteen.bgd.usersync.utils.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: Lph
 * @Date: 2020/11/13 9:56
 * @Function:
 * @Version 1.0
 */
@RestController
@RequestMapping("init")
public class UnixUsersAndIpsController {
    @Autowired
    private UnixUserAndIpService unixUserAndIpService;

    /**
     *  初始化系统ip
     * @return
     */
    @RequestMapping(value = "ips",method = RequestMethod.GET)
    public boolean initClusterIPs(){
        boolean b = unixUserAndIpService.initUnixIpForDbmeta();
        if(b) {
            return true;
        }
        return false;
    }
    /**
     * 初始化系统用户
     */
    @RequestMapping(value = "unix/users",method = RequestMethod.GET)
    public WebResult initClusterUnixUser(){
        HashMap<String,String> rs = unixUserAndIpService.initUnixUserForDbmeta();
        return new WebResult<HashMap<String,String>>(rs);
    }
}
