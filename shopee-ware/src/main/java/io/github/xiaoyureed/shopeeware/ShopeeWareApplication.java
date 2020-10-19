package io.github.xiaoyureed.shopeeware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShopeeWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeWareApplication.class, args);
    }

}
