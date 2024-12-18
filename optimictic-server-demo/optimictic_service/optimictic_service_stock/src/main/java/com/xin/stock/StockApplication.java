package com.xin.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.xin.stock.dao"})
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class,args);
    }
}
