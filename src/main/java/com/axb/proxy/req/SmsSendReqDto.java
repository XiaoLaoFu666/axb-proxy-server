package com.axb.proxy.req;

import lombok.Data;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 21:14
 */
@Data
public class SmsSendReqDto {

    private String spid;

    private String content;

    private String ac;

    private String tel;

    private String mobiles;

    private String password;
}
