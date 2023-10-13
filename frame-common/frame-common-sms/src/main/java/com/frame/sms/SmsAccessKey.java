package com.frame.sms;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * sms服务密钥
 *
 * @author: chenyuntao
 **/
@Component
@Data
public class SmsAccessKey {

//    @Value("${sms.access-key.id}")
    private String accessKeyId;

//    @Value("${sms.access-key.secret}")
    private String accessKeySecret;

}
