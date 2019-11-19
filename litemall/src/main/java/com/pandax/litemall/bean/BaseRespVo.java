package com.pandax.litemall.bean;

import lombok.Data;

@Data
public class BaseRespVo<T> {
    T data;
    String errmsg;
    int errno;

    public static BaseRespVo ok(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
    public static BaseRespVo ok(Object data){
        BaseRespVo baseRespVo = BaseRespVo.ok();
        baseRespVo.setData(data);
        return baseRespVo;
    }
    public static BaseRespVo fail(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("失败");
        baseRespVo.setErrno(500);
        return baseRespVo;
    }
}
