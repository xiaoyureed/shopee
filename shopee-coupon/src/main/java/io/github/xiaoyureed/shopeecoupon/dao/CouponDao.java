package io.github.xiaoyureed.shopeecoupon.dao;

import io.github.xiaoyureed.shopeecoupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:04:55
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
