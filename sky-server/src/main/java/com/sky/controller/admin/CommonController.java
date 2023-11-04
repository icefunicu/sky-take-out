package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.QiNiuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
* 通用接口
* */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

//    @Autowired
//    private AliOssUtil aliOssUtil;
    @Autowired
    private QiNiuUtil qiNiuUtil;


//    @ApiOperation("文件上传")
//    @PostMapping("/upload")
//    public Result<String> upload(MultipartFile file){
//        log.info("文件上传：{}",file);
//
//        try {
//            //原始文件名
//            String originalFilename= file.getOriginalFilename();
//            //截取后缀
//            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String s = UUID.randomUUID().toString() + extension;
//            //文件的请求路径
//            String FilePath = aliOssUtil.upload(file.getBytes(), s);
//
//            return Result.success(FilePath);
//        } catch (IOException e) {
//            log.error("文件上传失败：{}",e);
//        }
//        return Result.error(MessageConstant.UPLOAD_FAILED);
//    }

    /**
     * 七牛云对象储存
     * */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}",file);
        try {
            String FilePath = qiNiuUtil.upload(file.getBytes(), file.getOriginalFilename());
            return Result.success(FilePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

}
