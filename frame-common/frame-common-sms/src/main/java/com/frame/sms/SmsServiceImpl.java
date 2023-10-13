package com.frame.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * sms service
 *
 * @author: chenyuntao
 **/
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsAccessKey smsAccessKey;

    @Override
    public Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(smsAccessKey.getAccessKeyId())
                .setAccessKeySecret(smsAccessKey.getAccessKeySecret());
        // 访问的域名 固定
        config.endpoint = "aliyuncs.com";
        return new Client(config);
    }

    @Override
    public void sendSms(List<String> telNum, String templateCode, String params) {
        log.info("发送短信!!");
    }
}
