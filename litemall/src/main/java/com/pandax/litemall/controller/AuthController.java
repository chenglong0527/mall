package com.pandax.litemall.controller;
import java.io.Serializable;
import java.util.ArrayList;

import com.pandax.litemall.bean.BaseReqVo;
import com.pandax.litemall.bean.BaseRespVo;
import com.pandax.litemall.bean.InfoData;
import com.pandax.litemall.bean.LoginVo;
import com.pandax.litemall.shiro.CustomToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping("admin/auth/login")
    public BaseRespVo login(@RequestBody LoginVo loginVo){
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();

        CustomToken authenticationToken = new CustomToken(username, password,"admin");
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            return BaseRespVo.fail();
            //e.printStackTrace();
        }
        boolean permitted = subject.isPermitted("admin:query");
        System.out.println(permitted);
        Serializable id = subject.getSession().getId();
        return BaseRespVo.ok(id);
    }
    @RequestMapping("admin/auth/info")
    public BaseReqVo info(String token){
        BaseReqVo baseReqVo = new BaseReqVo();
        InfoData data = new InfoData();
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setName("songge");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.setRoles(roles);

        baseReqVo.setData(data);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);


        return baseReqVo;
    }
}
