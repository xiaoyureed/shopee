package io.github.xiaoyureed.shopeeproduct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan({"io.github.xiaoyureed.shopeeproduct.dao"})
@EnableDiscoveryClient
public class ShopeeProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeProductApplication.class, args);
    }

}
