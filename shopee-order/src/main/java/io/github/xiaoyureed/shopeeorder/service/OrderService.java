package io.github.xiaoyureed.shopeeorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeeorder.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:37:30
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

