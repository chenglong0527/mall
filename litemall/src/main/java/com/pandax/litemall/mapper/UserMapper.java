package com.pandax.litemall.mapper;

import com.pandax.litemall.bean.User;import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User selectUserByName(@Param("username")String username);

    List<String> selectPermissionByUsername(String username);
}
