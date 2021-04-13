package com.axb.proxy.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @Author: HuangJunHao
 * @Date: 2021/4/13 14:33
 */
@Slf4j
public class RestTemplateUtil {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    private static class SingletonRestTemplate {
        static final RestTemplate INSTANCE = new RestTemplate();
    }

    private RestTemplateUtil() {
    }

    public static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }


    /**
     *
     * @param url 地址
     * @param t 请求体
     * @param mediaType 类型
     * @param <T>
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static <T> String postForFromData(String url, T t, MediaType mediaType){
        //初始化请求头
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        //请求实体
        final HttpEntity<String> requestEntity = new HttpEntity(t, headers);
        final Date start = new Date();
        String ret = "";
        log.info("执行方式 :{}, URL -> {}, 参数: {}, 请求时间: {}",
                "POST",
                url,
                t.toString(),
                FORMATTER.format(start));
        try {
            final ResponseEntity<String> entity = RestTemplateUtil.getInstance().postForEntity(url, requestEntity, String.class);
            //返回值
            ret = entity.getBody();
        }catch (Exception e) {
            log.error("执行失败, URL -> {}, 错误: {} 耗时: {} ms",
                    url,
                    e.getMessage(),
                    System.currentTimeMillis() - start.getTime(), e);
            throw e;
        }

        log.info("执行结束, URL -> {}, 结果: {} 耗时: {} ms",
                url,
                ret,
                System.currentTimeMillis() - start.getTime());
        return ret;
    }

    public static <T> String postForFromData(String url, T t, HttpHeaders httpHeaders){
        //请求实体
        final HttpEntity<String> requestEntity = new HttpEntity(t, httpHeaders);
        final Date start = new Date();
        String ret = "";
        log.info("执行方式 :{}, URL -> {}, 参数: {}, 请求时间: {}",
                "POST",
                url,
                t.toString(),
                FORMATTER.format(start));
        try {
            final ResponseEntity<String> entity = RestTemplateUtil.getInstance().postForEntity(url, requestEntity, String.class);
            //返回值
            ret = entity.getBody();
        }catch (Exception e) {
            log.error("执行失败, URL -> {}, 错误: {} 耗时: {} ms",
                    url,
                    e.getMessage(),
                    System.currentTimeMillis() - start.getTime(), e);
            throw e;
        }

        log.info("执行结束, URL -> {}, 结果: {} 耗时: {} ms",
                url,
                ret,
                System.currentTimeMillis() - start.getTime());
        return ret;
    }

    /**
     *
     * @param url
     */
    @SneakyThrows
    public static String get(String url) {

        String body;
        final Date start = new Date();
        log.info("执行方式 :{}, URL -> {}, 请求时间: {}",
                "GET",
                url,
                FORMATTER.format(start));
        try {
            final ResponseEntity<String> entity = RestTemplateUtil.getInstance().getForEntity(url, String.class);
            //返回值
            body = entity.getBody();
        }catch (Exception e) {
            log.error("执行失败, URL -> {}, 错误: {} 耗时: {} ms",
                    url,
                    e.getMessage(),
                    System.currentTimeMillis() - start.getTime(), e);
            throw e;
        }

        log.info("执行结束, URL -> {}, 结果: {} 耗时: {} ms",
                url,
                body,
                System.currentTimeMillis() - start.getTime());
        return body;
    }

    /**
     *
     * @param url
     * @param uriVariables
     * @return
     */
    @SneakyThrows
    public static String get(String url, Object... uriVariables) {

        String body;
        final Date start = new Date();
        log.info(" 执行方式 :{}, URL -> {}, 参数: {}, 请求时间: {}",
                "GET",
                url,
                Arrays.toString(uriVariables),
                FORMATTER.format(start));
        try {
            final ResponseEntity<String> entity = RestTemplateUtil.getInstance().getForEntity(url, String.class, uriVariables);
            //返回值
            body = entity.getBody();
        }catch (Exception e) {
            log.error(" 执行失败, URL -> {}, 错误: {} 耗时: {} ms",
                    url,
                    e.getMessage(),
                    System.currentTimeMillis() - start.getTime(), e);
            throw e;
        }

        log.info("执行结束, URL -> {}, 结果: {} 耗时: {} ms",
                url,
                body,
                System.currentTimeMillis() - start.getTime());
        return body;
    }

    /**
     * get map
     */
    @SneakyThrows
    public static String get(String url,  Map<String, ?> map) {

        String body;
        final Date start = new Date();
        log.info("执行方式 :{}, URL -> {}, 参数: {}, 请求时间: {}",
                "GET",
                url,
                map.toString(),
                FORMATTER.format(start));
        try {
            final ResponseEntity<String> entity = RestTemplateUtil.getInstance().getForEntity(url, String.class, map);
            //返回值
            body = entity.getBody();
        }catch (Exception e) {
            log.error("执行失败, URL -> {}, 错误: {} 耗时: {} ms",
                    url,
                    e.getMessage(),
                    System.currentTimeMillis() - start.getTime(), e);
            throw e;
        }

        log.info("执行结束, URL -> {}, 结果: {} 耗时: {} ms",
                url,
                body,
                System.currentTimeMillis() - start.getTime());
        return body;
    }
}
