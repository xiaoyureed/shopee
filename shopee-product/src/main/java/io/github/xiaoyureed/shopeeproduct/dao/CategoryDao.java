package io.github.xiaoyureed.shopeeproduct.dao;

import io.github.xiaoyureed.shopeeproduct.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 18:28:59
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
