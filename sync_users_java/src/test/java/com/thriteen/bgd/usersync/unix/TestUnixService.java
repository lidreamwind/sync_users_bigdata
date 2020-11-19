package com.thriteen.bgd.usersync.unix;

import com.thriteen.bgd.usersync.services.UnixUserAndIpService;
import com.thriteen.bgd.usersync.services.impl.UnixUserAndIpServiceImpl;
import org.junit.Test;

/**
 * @Author: Lph
 * @Date: 2020/11/13 9:34
 * @Function:
 * @Version 1.0
 */
public class TestUnixService {
    @Test
    public void testInitUnixIps(){
        UnixUserAndIpService unixUserAndIpService = new UnixUserAndIpServiceImpl();
        unixUserAndIpService.initUnixIpForDbmeta();
    }
    @Test
    public void testStr(){
        String a = " ";
        System.out.println(a.replace(" ","").isEmpty());
    }
}
