package com.thriteen.bgd.usersync.kerberos;

import com.thriteen.bgd.usersync.entity.dto.kerberos.KerberosInitDto;
import com.thriteen.bgd.usersync.utils.kerberos.KerberosUtils;
import org.junit.Test;

import java.util.List;

/**
 * @Author: Lph
 * @Date: 2020/11/16 21:08
 * @Function:
 * @Version 1.0
 */
public class TestKerberos {
    /**
     * 测试Kerberos的用户信息
     */
    @Test
    public void testKerberosSelect(){
        KerberosInitDto kerberosInitDto = new KerberosInitDto();
        KerberosUtils kerberosUtils = new KerberosUtils(kerberosInitDto);
        List<String> root = kerberosUtils.readALlUsersFromKerberos(false);
        for (String s : root) {
            System.out.println(s);
        }
    }
}
