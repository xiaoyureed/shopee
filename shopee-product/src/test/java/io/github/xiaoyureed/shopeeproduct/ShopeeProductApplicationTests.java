package io.github.xiaoyureed.shopeeproduct;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xiaoyureed.shopeeproduct.entity.BrandEntity;
import io.github.xiaoyureed.shopeeproduct.service.BrandService;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ShopeeProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Test
    void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setName("xxx");
//        brandService.save(brandEntity);

        List<BrandEntity> brands = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 7L));
        System.out.println(brands);

        BrandEntity updateCondition = new BrandEntity();
        updateCondition.setBrandId(7L);
        updateCondition.setName("yyy");
        brandService.updateById(updateCondition);
        BrandEntity updated = brandService.getById(7L);
        System.out.println(updated);

        brandService.removeById(7L);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRedis() throws JsonProcessingException {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();

        DD dd = new DD();
        dd.setId(1L);
        dd.setName("dd");
        dd.setPrice(BigDecimal.valueOf(11.1));
        valueOps.set("dd", objectMapper.writeValueAsString(dd));

        String ddStr = "{\"id\":1,\"name\":\"dd\",\"price\":11.1}";

        Assertions.assertThat(valueOps.get("dd")).isEqualTo(ddStr);
    }

    @Data
    static class DD {
        private Long id;
        private String name;
        private BigDecimal price;
    }

}
