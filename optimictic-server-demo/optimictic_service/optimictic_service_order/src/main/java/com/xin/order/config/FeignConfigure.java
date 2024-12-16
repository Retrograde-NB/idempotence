package com.xin.order.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义feign超时时间、重试次数
 * 默认超时为10秒，不会进行重试。
 */
@Configuration
public class FeignConfigure {

    //超时时间，时间单位毫秒
    public static int connectTimeOutMillis = 5000;
    public static int readTimeOutMillis = 5000;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

    //自定义重试次数
    @Bean
    public Retryer feignRetryer(){
        Retryer retryer = new Retryer.Default(100, 1000, 4);
        return retryer;
    }
}
