package com.axb.proxy.utils;

import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 15:13
 * 消息处理工具类
 */
public class MessageUtil {

    public static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };


    /**
     * MD5加密生成msgdgt
     * */
    public static String md5Msg(JSONObject jsonObject,String secretKey,String appKey,String ts){
        StringBuilder stringBuilder = new StringBuilder(secretKey + appKey + ts);
        Map<String, Object> innerMap = jsonObject.getInnerMap();
        Set<String> strings = innerMap.keySet();
        strings.forEach( s -> {
            stringBuilder.append(s + innerMap.get(s).toString());
        });
        return MD5(stringBuilder.toString());
    }

    /**
     * 32位MD5加密的大写字符串
     *
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
