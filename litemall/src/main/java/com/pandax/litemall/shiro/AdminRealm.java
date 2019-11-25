package com.pandax.litemall.shiro;

import com.pandax.litemall.bean.Admin;
import com.pandax.litemall.mapper.AdminMapper;
import com.pandax.litemall.mapper.PermissionMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AdminRealm extends AuthorizingRealm {
    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Autowired
    AdminMapper adminMapper;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        MallToken token= (MallToken) authenticationToken;
        //获取username
        String username = token.getUsername();
        //获取存在数据库中的账户真实数据
        Admin admin = adminMapper.selectAdminsByUsername(username);
        //构造认证器信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户的基本信息
        Admin admin= (Admin) principalCollection.getPrimaryPrincipal();
        Integer[] roleIds = admin.getRoleIds();
        ArrayList<String> permissions = new ArrayList<>();
        for (Integer roleId : roleIds) {
            List<String> list = permissionMapper.selectPersionsByRoleId(roleId);
            permissions.addAll(list);
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }


}
