package io.github.xiaoyureed.shopeemember.feign;

import io.github.xiaoyureed.shopeecommon.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : xiaoyureed
 * 2020/10/6
 */
@FeignClient("shopee-coupon")
public interface CouponFeignClient {
    @RequestMapping("/shopeecoupon/coupon/feign-test")
    R feignTest();
}
