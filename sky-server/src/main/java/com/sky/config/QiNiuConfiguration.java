package com.sky.config;

import com.sky.properties.QiNiuProperties;
import com.sky.utils.QiNiuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* 配置类，用于创建QiNiuUtil对象
* */
@Configuration
@Slf4j
public class QiNiuConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public QiNiuUtil qiiNiuUtil  (QiNiuProperties qiNiuProperties){
        log.info("开始创建七牛云文件上传工具对象:{}",qiNiuProperties);
        return new QiNiuUtil(qiNiuProperties.getAccessKeyId(),
                qiNiuProperties.getAccessKeySecret(),
                qiNiuProperties.getBucketName(),
                qiNiuProperties.getImgUrl());
    }
}
