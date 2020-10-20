package io.github.xiaoyureed.shopeeproduct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan({"io.github.xiaoyureed.shopeeproduct.dao"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "io.github.xiaoyureed.shopeeproduct.feign")
public class ShopeeProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeProductApplication.class, args);
    }

}
