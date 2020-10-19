package io.github.xiaoyureed.shopeeorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShopeeOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeOrderApplication.class, args);
    }

}
