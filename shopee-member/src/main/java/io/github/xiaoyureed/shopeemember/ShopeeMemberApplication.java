package io.github.xiaoyureed.shopeemember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"io.github.xiaoyureed.shopeemember.feign"})
public class ShopeeMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeMemberApplication.class, args);
    }

}
