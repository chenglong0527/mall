package com.pandax.litemall.mapper;

import com.pandax.litemall.bean.Admin;

import java.util.List;

import com.pandax.litemall.bean.Role;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    List<Admin> selectAdmins(@Param("sort") String sort, @Param("order") String order);

    Admin selectAdminsByUsername(@Param("username")String username);


    Role selectRolesByUsername(@Param("username")String username);
}
