package io.github.xiaoyureed.shopeeware.dao;

import io.github.xiaoyureed.shopeeware.entity.PurchaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 * 
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:40:35
 */
@Mapper
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
