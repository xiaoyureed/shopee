package io.github.xiaoyureed.shopeeproduct.service;

import io.github.xiaoyureed.shopeeproduct.entity.CategoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author : xiaoyureed
 * 2020/10/22
 */
@SpringBootTest
@Slf4j
public class CategoryServiceTests {

    @Autowired
    private CategoryService service;

    @Test
    public void testListTree() {
        List<CategoryEntity> tree = service.tree();
        Assertions.assertThat(tree).anyMatch(cate -> !CollectionUtils.isEmpty(cate.getChildren()));
    }
}
