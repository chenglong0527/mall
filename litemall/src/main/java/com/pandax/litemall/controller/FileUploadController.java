package com.pandax.litemall.controller;


import com.pandax.litemall.bean.Admin;
import com.pandax.litemall.bean.BaseReqVo;
import com.pandax.litemall.bean.Storage;
import com.pandax.litemall.utils.FileUpload;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    FileUpload fileUpload;
    @RequestMapping("admin/storage/create")
    public BaseReqVo storageCreate(@RequestParam("file") MultipartFile myfile){
        String filePath = fileUpload.upload(myfile);
        Storage storage = new Storage();
        storage.setUrl(filePath);
        return BaseReqVo.ok(storage);
    }
}
