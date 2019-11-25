package com.pandax.litemall.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@Data
@Component
@ConfigurationProperties(prefix = "fileupload")
public class FileUpload {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucket;
    private String prefix;
    public String upload(MultipartFile myfile){
        System.out.println(secretId);
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region1 = new Region(region);
        ClientConfig clientConfig = new ClientConfig(region1);
// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            //获取原文件类型
            String originalFilename = myfile.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String type=originalFilename.substring(i);
            //获取存入的相对路径
            String filePath = FileUploadUtils.getFilePath();
            String randomFileName = FileUploadUtils.getRandomFileName();

            // 指定要上传的文件
            File localFile = MultipartFileToFile.multipartFileToFile(myfile);
            // 指定要上传到的存储桶
            String bucketName =bucket;
            // 指定要上传到 COS 上对象键
            String key = filePath+randomFileName+type;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return prefix+key;
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
