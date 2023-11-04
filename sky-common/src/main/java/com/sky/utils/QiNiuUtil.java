package com.sky.utils;


import com.aliyun.oss.ClientException;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Slf4j
public class QiNiuUtil {

    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String imgUrl;

    /**
     *
     * 文件上传到七牛云
     *
    * */
    public String upload(byte[] bytes, String objectName){
        try {
            //1.获取文件上传的流 这里传入的bytes
            //2.创建日期目录分隔
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datePath=dateFormat.format(new Date());
            //3.获取文件名 这里的 objectName就是源文件名
            String suffix = objectName.substring(objectName.lastIndexOf("."));
            String filename=datePath+"/"+UUID.randomUUID().toString().replace("-","")+suffix;
            //4.构造一个带指定Region对象的配置类
            Configuration cfg = new Configuration(Region.huadongZheJiang2());
            UploadManager uploadManager = new UploadManager(cfg);
            //5.获取七牛云提供的token
            Auth auth = Auth.create(accessKeyId,accessKeySecret);
            String upToken = auth.uploadToken(bucketName);
            uploadManager.put(bytes,filename,upToken);
            //文件访问的路径 http://pic.icefun.icu/filename
            StringBuilder stringBuilder = new StringBuilder("http://");
            stringBuilder.append(imgUrl).append("/").append(filename);
            log.info("文件上传到：{}",stringBuilder.toString());

            return stringBuilder.toString();
        } catch (QiniuException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getMessage());
            System.out.println("Error Code:" + oe.code());
            System.out.println("Request ID:" + oe.url());
            System.out.println("Host ID:" + oe.url());
        }catch (ClientException ce){
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        }
        return null;
    }

}
