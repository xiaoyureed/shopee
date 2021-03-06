package io.github.xiaoyureed.shopeecoupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeecoupon.entity.CouponHistoryEntity;

import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:04:55
 */
public interface CouponHistoryService extends IService<CouponHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

