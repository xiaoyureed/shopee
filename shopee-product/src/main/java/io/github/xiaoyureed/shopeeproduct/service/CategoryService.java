package io.github.xiaoyureed.shopeeproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeeproduct.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 18:28:59
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> tree();

    void removeMenuByIds(List<Long> cateIds);

    /**
     * find full category path for specific categoryId , eg: [grand/parent/son]
     * @param categoryId
     * @return
     */
    Long[] findCatelogPath(Long categoryId);
}

