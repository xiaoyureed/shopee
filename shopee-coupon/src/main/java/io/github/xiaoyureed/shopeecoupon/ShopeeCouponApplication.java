package io.github.xiaoyureed.shopeecoupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShopeeCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeCouponApplication.class, args);
    }

}
