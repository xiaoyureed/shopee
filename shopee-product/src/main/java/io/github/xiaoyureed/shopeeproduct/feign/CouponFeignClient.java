package io.github.xiaoyureed.shopeeproduct.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author : xiaoyureed
 * 2020/10/20
 */
@FeignClient("shopee-coupon")
public interface CouponFeignClient {
    @PostMapping("")
    void xxx();
}

