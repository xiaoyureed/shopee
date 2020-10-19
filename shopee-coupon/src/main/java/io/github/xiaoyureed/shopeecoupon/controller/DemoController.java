package io.github.xiaoyureed.shopeecoupon.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xiaoyureed
 * 2020/10/19
 */
@RestController
public class DemoController {
//    @Value("${aa.bb}")
    @NacosValue(value = "${aa.bb}", autoRefreshed = true)
    private String bb;

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return new ResponseEntity<>(bb, HttpStatus.OK);
    }
}
