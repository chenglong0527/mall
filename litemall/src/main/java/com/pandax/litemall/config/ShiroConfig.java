package com.pandax.litemall.config;

import com.pandax.litemall.shiro.AdminRealm;
import com.pandax.litemall.shiro.MallRealmAuthenticator;
import com.pandax.litemall.shiro.MallSessionManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        //未认证通过的重定向
        factoryBean.setLoginUrl("/admin/auth/fail");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //不用认证的url，(钥匙)
        map.put("/wx/**", "anon");
        map.put("/admin/auth/login", "anon");
        map.put("/wx/auth/login","anon");
        map.put("/**/*.jpg", "anon");
        map.put("/**/*.png", "anon");
        //map.put("/admin/auth/logout", "logout");
//        map.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    /**
     * 声明式鉴权
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor sourceAdvisor(DefaultWebSecurityManager manager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(manager);
        return attributeSourceAdvisor;
    }
    @Bean
    public DefaultWebSecurityManager securityManager(MallRealmAuthenticator mallRealmAuthenticator,
                                                     MallSessionManager mallSessionManager,
                                                     AdminRealm adminRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        ArrayList<Realm> list = new ArrayList<>();
        list.add(adminRealm);
        securityManager.setRealms(list);
        securityManager.setAuthenticator(mallRealmAuthenticator);
        securityManager.setSessionManager(mallSessionManager);
        return securityManager;
    }
    @Bean
    public MallSessionManager mallSessionManager(){
        MallSessionManager mallSessionManager = new MallSessionManager();
        mallSessionManager.setDeleteInvalidSessions(true);
        return mallSessionManager;
    }

    /**
     * 重写权限认证器
     */
    @Bean
    public MallRealmAuthenticator realmAuthenticator(AdminRealm adminRealm){
        MallRealmAuthenticator authenticator = new MallRealmAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        authenticator.setRealms(realms);
        return authenticator;
    }
}
