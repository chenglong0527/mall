package com.pandax.litemall.controller;


import com.pandax.litemall.bean.BaseReqVo;
import com.pandax.litemall.bean.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {
    @Value("${my.visitPath}")
    private String visitPath;
    @Value("${my.storagePath}")
    private String storagePath;
    @RequestMapping("admin/storage/create")
    public BaseReqVo storageCreate(@RequestParam("file") MultipartFile myfile){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>();
        String originalFilename = myfile.getOriginalFilename();
        String suffixName=originalFilename.substring(originalFilename.lastIndexOf('.'));
        //获取相对路径
        StringBuilder sb=new StringBuilder();

        UUID uuid = UUID.randomUUID();
        String string = Integer.toHexString(uuid.hashCode());
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            sb.append(aChar).append("/");
        }
        //获取存储的绝对路径
        String storageAbsolutePath=storagePath+sb.toString()+uuid+suffixName;
        System.out.println("存储的绝对路径="+storageAbsolutePath);
        //将文件存储到static中
        try {
            File file = new File(storageAbsolutePath);
            if(!file.exists()){
                file.mkdirs();
            }
            myfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取访问的绝对路径
        String visitAbsolutePath=visitPath+sb.toString()+uuid+suffixName;
        System.out.println("访问的绝对路径="+visitAbsolutePath);
        Storage storage = new Storage();
        storage.setUrl(visitAbsolutePath);
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());

        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(storage);
        return baseReqVo;
    }
}
