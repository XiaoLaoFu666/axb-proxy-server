package com.axb.proxy.controller;

import com.axb.proxy.resp.SmsSendRespDto;
import com.axb.proxy.resp.TelBindRespDto;
import com.axb.proxy.service.AXBService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 17:29
 */
@RestController
@RequestMapping("/v1")
public class AXBController {

    @Resource
    private AXBService axbService;

    @ApiOperation(httpMethod = "GET",response = TelBindRespDto.class,value = "AXB号码获取")
    @GetMapping("/getTelX")
    public TelBindRespDto getTel(@RequestParam String telA,@RequestParam String telB){
        return axbService.telBindAXB(telA,telB);
    }


    @ApiOperation(httpMethod = "POST",response = SmsSendRespDto.class,value = "发送短信")
    @GetMapping("/sendSms")
    public SmsSendRespDto sendSms(@RequestParam String tel, @RequestParam String content) throws UnsupportedEncodingException {
        return axbService.sendSms(tel,content);
    }


}
