package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.qiniu")
@Data
public class QiNiuProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String imgUrl;

}
