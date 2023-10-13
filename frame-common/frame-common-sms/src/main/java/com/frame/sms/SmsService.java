package com.frame.sms;

import com.aliyun.dysmsapi20170525.Client;

import java.util.List;

/**
 * @author chenyuntao
 */
public interface SmsService {

    /**
     * 创建 client
     *
     * @return
     * @throws Exception
     */
    Client createClient() throws Exception;


    /**
     * 发短信
     *
     * @param telNum
     * @param templateCode
     * @param param
     * @throws Exception
     */
    void sendSms(List<String> telNum, String templateCode, String param) throws Exception;
}
