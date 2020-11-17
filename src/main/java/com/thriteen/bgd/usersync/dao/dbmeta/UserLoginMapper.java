package com.thriteen.bgd.usersync.dao.dbmeta;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thriteen.bgd.usersync.entity.po.UserLoginEntity;

public interface UserLoginMapper extends BaseMapper<UserLoginEntity> {
    public String selectByName();
}
