package com.axb.proxy.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.axb.proxy.exception.ServerException;
import com.axb.proxy.req.SmsSendReqDto;
import com.axb.proxy.req.TelBindProxyDto;
import com.axb.proxy.resp.BaseResult;
import com.axb.proxy.resp.CodeMsg;
import com.axb.proxy.resp.SmsSendRespDto;
import com.axb.proxy.resp.TelBindRespDto;
import com.axb.proxy.utils.ApiConstants;
import com.axb.proxy.utils.MessageUtil;
import com.axb.proxy.utils.RestTemplateUtil;
import com.axb.proxy.utils.Xml2JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.axb.proxy.resp.CodeMsg.SUCCESS;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 16:05
 */
@Service
public class AXBService {

    @Value("${axb.url}")
    private String axbUrl;

    @Value("${axb.appKey}")
    private String axbAppKey;

    @Value("${axb.secretKey}")
    private String axbSecretKey;

    @Value("${axb.requestId}")
    private String axbRequestId;

    @Value("${axb.expiration}")
    private String axbExpiration;

    @Value("${axb.anuCode}")
    private String axbAnuCode;

    @Value("${sms.url}")
    private String smsUrl;

    @Value("${sms.spid}")
    private String spid;

    @Value("${sms.password}")
    private String password;

    @Value("${sms.ac}")
    private String ac;

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 通过A、B号码获取X号码
     * */
    public TelBindRespDto telBindAXB(String telA,String telB){
        String ts = df.format(new Date());
        String url = axbUrl + ApiConstants.AXB_BIND_URL;
        TelBindProxyDto telBindProxyDto = new TelBindProxyDto();
        telBindProxyDto.setAnuCode(axbAnuCode);
        telBindProxyDto.setExpiration(axbExpiration);
        telBindProxyDto.setRequestId(axbRequestId);
        telBindProxyDto.setTelA(telA);
        telBindProxyDto.setTelB(telB);
        telBindProxyDto.setSubTs(ts);
        String msgdgt = MessageUtil.md5Msg(JSON.parseObject(JSONObject.toJSONString(telBindProxyDto)), axbSecretKey, axbAppKey, ts);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.set("appkey",axbAppKey);
        httpHeaders.set("ts",ts);
        httpHeaders.set("msgdgt",msgdgt);
        String body = RestTemplateUtil.postForFromData(url, telBindProxyDto, httpHeaders);
        BaseResult baseResult = null;
        if(StringUtils.isNoneEmpty(body)){
             baseResult = JSONObject.parseObject(body, BaseResult.class);
        }
        if(baseResult.getCode().equals(SUCCESS.getCode())){
            throw new ServerException(new CodeMsg(baseResult.getCode(),baseResult.getMessage()));
        }
        return (TelBindRespDto) baseResult.getData();
    }


    /**
     * 短信发送
     * */
    public SmsSendRespDto sendSms(String tel,String content) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(content, "UTF-8");
        SmsSendReqDto smsSendReqDto = new SmsSendReqDto();
        smsSendReqDto.setAc(ac);
        smsSendReqDto.setMobiles(tel);
        smsSendReqDto.setSpid(spid);
        smsSendReqDto.setContent(encode);
        smsSendReqDto.setPassword(password);
        String result = RestTemplateUtil.postForFromData(smsUrl + ApiConstants.SEND_MESSAGE_URL, smsSendReqDto, MediaType.ALL);
        JSONObject jsonObject = JSON.parseObject(Xml2JsonUtil.xml2JSON(result));
        SmsSendRespDto smsSendRespDto = JSONObject.parseObject(jsonObject.getString("returnsms"),SmsSendRespDto.class);
        return  smsSendRespDto;
    }



}
