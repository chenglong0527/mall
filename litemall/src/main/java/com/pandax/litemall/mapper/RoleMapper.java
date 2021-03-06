package com.pandax.litemall.mapper;

import com.pandax.litemall.bean.Role;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Da
 * @version 1.0
 * @date 2019/11/15
 * @time 21:16
 */

public interface RoleMapper {
    List<Role> selectRoles();

    String selectNameById(@Param("roleId")Integer roleId);
}
