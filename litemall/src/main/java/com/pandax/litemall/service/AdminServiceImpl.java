package com.pandax.litemall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pandax.litemall.bean.Admin;
import com.pandax.litemall.bean.AdminExample;
import com.pandax.litemall.bean.Role;
import com.pandax.litemall.mapper.AdminMapper;
import com.pandax.litemall.mapper.PermissionMapper;
import com.pandax.litemall.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Da
 * @version 1.0
 * @date 2019/11/15
 * @time 17:28
 */

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminMapper adminMapper;


    @Override
    public HashMap<String, Object> queryUsers(Integer page, Integer limit,
                                              String sort, String order) {
        PageHelper.startPage(page, limit);
        HashMap<String, Object> map = new HashMap<>();
        List<Admin> adminList = adminMapper.selectAdmins(sort, order);
        for (Admin admin : adminList) {
            System.out.println(admin);
        }
        PageInfo<Admin> adminPageInfo = new PageInfo<>(adminList);
        map.put("items", adminList);
        map.put("total", adminPageInfo.getTotal());
        return map;
    }

    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public List<String> selectPermissionByUsername(String username) {
        //通过姓名查出roles
        Role role = adminMapper.selectRolesByUsername(username);
        String[] roles = role.getRoles();
        //通过roles获取角色对应的权限
        ArrayList<String> list = new ArrayList<>();
        for (String s : roles) {
            List<String> list1=permissionMapper.selectPersionsByRoleId(Integer.parseInt(s));
            for (String s1 : list1) {
                list.add(s1);
            }

        }
        return list;
    }
}
