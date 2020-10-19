package io.github.xiaoyureed.shopeethirdparty;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class ShopeeThirdPartyApplicationTests {

    @Autowired
    private OSS ossClient;

    @Test
    void testOss() throws FileNotFoundException {
        // upload
        ossClient.putObject("", "", new FileInputStream(""));

        //download
        ossClient.getObject(new GetObjectRequest("", ""), new File(""));
    }

}
