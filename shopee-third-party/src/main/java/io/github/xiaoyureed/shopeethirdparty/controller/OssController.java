package io.github.xiaoyureed.shopeethirdparty.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xiaoyureed
 * 2020/10/20
 */
@RestController
public class OssController {

    /**
     * user request a signature from our server , and then user upload file to oss server
     */
    @RequestMapping("/oss/policy")
    public void policy() {
        //TODO server side signature

    }
}
