package io.github.xiaoyureed.shopeecoupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeecoupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:04:55
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

