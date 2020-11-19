package com.thriteen.bgd.usersync.controller.init;

import com.thriteen.bgd.usersync.entity.vo.kerberos.KerberosConnectionsInfoVo;
import com.thriteen.bgd.usersync.services.KerberosService;
import com.thriteen.bgd.usersync.utils.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lph
 * @Date: 2020/11/17 10:57
 * @Function:
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "kerberos")
public class KerberosController {
    @Autowired
    private KerberosService kerberosService;

    @RequestMapping(value = "new",method = RequestMethod.POST)
    public WebResult insertNewKerberosConnect(@RequestBody KerberosConnectionsInfoVo kerberosConnectionsInfoVo) {
        return this.kerberosService.insertNewKerberosInfo(kerberosConnectionsInfoVo);
    }
}
