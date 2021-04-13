package com.axb.proxy.resp;

import lombok.Data;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 16:14
 */
@Data
public class BaseResult<T> {

    private String code;

    private String message;

    private T data;
}
