package com.thriteen.bgd.usersync.dao.dbmeta;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thriteen.bgd.usersync.entity.po.ClusterIpsEntity;
import com.thriteen.bgd.usersync.entity.dto.dbmeta.ClusterIpUserPasswdFileDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Lph
 * @Date: 2020/11/8 20:37
 * @Function:
 * @Version 1.0
 */
public interface ClusterIpsMapper extends BaseMapper<ClusterIpsEntity> {

    /**
     查询用户是否存在
      */
    Integer selectCountByIpAddr(@Param("ip") String ip);

    /**
     *  查询字段,ip-user-password-ssh_key_file
     * @return
     */
    List<ClusterIpUserPasswdFileDto> selectAllIpsUserPasswdKeyFile();

}
