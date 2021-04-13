package com.axb.proxy.resp;

import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.Data;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 16:11
 */
@Data
public class CodeMsg {

    private String code;

    private String message;

    public CodeMsg(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CodeMsg SUCCESS = new CodeMsg("0","success");

}
