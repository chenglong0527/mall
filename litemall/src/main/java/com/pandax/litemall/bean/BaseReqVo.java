package com.pandax.litemall.bean;

import lombok.Data;

@Data
public class BaseReqVo<T> {
    T data;
    String errmsg;
    int errno;
    public static BaseReqVo ok(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
    public static BaseReqVo ok(Object o){
        BaseReqVo ok = ok();
        ok.setData(o);
        return ok;
    }
    public static BaseReqVo fail(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        baseReqVo.setErrno(500);
        baseReqVo.setErrmsg("失败");
        return baseReqVo;
    }
}
