package com.thriteen.bgd.usersync.dao.dbmeta;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thriteen.bgd.usersync.entity.po.UserLinuxEntity;

/**
 * @Author: Lph
 * @Date: 2020/11/8 20:05
 * @Function:
 * @Version 1.0
 */
public interface UserLinuxMapper extends BaseMapper<UserLinuxEntity> {

    Integer selectCountByIpAndUserName(String ip, String userName);
}
