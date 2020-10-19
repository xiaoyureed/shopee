package io.github.xiaoyureed.shopeeproduct;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.github.xiaoyureed.shopeeproduct.entity.BrandEntity;
import io.github.xiaoyureed.shopeeproduct.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
