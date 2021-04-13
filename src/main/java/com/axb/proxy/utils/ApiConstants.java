package com.axb.proxy.utils;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 15:41
 */
public class ApiConstants {

    public static final String AXB_BIND_URL = "/v2/axb/mode102";

    public static final String BASE_AXB_UNBIND_URL = "/v2/axb/";

    /**
     * 短信平台消息发送接口地址
     * */
    public static final String SEND_MESSAGE_URL = "smsgwhttp/sms/submit";

    public static String getBaseAxbUnbindUrl(String subId){
        return BASE_AXB_UNBIND_URL + subId;
    }

}
