package com.pandax.litemall.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class MallSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //X-Litemall-Admin-Token
//        String header = request.getHeader("X-cskaoyanmall-Admin-Token");
        String header1 = request.getHeader("X-Litemall-Admin-Token");
//        String header2 = request.getHeader("X-Litemall-Token");
        String header2 = request.getHeader("X-cskaoyanmall-Admin-Token");
        String header = null;
        if (header1 != null) {
            header = header1;
        }
        if (header2 != null) {
            header = header2;
        }
        if (header != null && !"".equals(header)) {
            return header;
        }
        return super.getSessionId(request, response);
    }
}
