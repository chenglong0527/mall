package com.pandax.litemall.controller;

import com.pandax.litemall.bean.Admin;
import com.pandax.litemall.bean.BaseReqVo;
import com.pandax.litemall.bean.LoginVo;
import com.pandax.litemall.mapper.PermissionMapper;
import com.pandax.litemall.mapper.RoleMapper;
import com.pandax.litemall.shiro.MallToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class AuthController {

    @RequestMapping("admin/auth/login")
    public BaseReqVo login(@RequestBody LoginVo loginVo) {
        Subject subject = SecurityUtils.getSubject();
        MallToken token = new MallToken(loginVo.getUsername(), loginVo.getPassword(),"admin");
        try {
            subject.login(token);
        } catch (Exception e) {
            return BaseReqVo.fail();
        }
        Serializable id = subject.getSession().getId();
        return BaseReqVo.ok(id);
    }

    /**
     * 获取用户所具有的所有权限
     *
     * @param token
     * @return
     */
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RoleMapper roleMapper;

    @RequestMapping("admin/auth/info")
    public BaseReqVo info(String token) {
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();
        //获取权限id
        Integer[] roleIds = admin.getRoleIds();
        ArrayList<String> permissions = new ArrayList<>();
        ArrayList<String> roles = new ArrayList<>();
        for (Integer roleId : roleIds) {
            List<String> list = permissionMapper.selectPersionsByRoleId(roleId);
            permissions.addAll(list);
            //获取权限名
            String roleName = roleMapper.selectNameById(roleId);
            roles.add(roleName);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("avatar", "");
        map.put("name", admin.getUsername());
        if("admin123".equals(admin.getUsername())){
            permissions.clear();
            permissions.add("*");
        }
        map.put("perms", permissions);
        map.put("roles", roles);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("admin/auth/logout")
    public BaseReqVo logout() {
        SecurityUtils.getSubject().logout();
        return BaseReqVo.ok();
    }
}
