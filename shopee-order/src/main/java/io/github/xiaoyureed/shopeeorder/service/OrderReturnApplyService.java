package io.github.xiaoyureed.shopeeorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeeorder.entity.OrderReturnApplyEntity;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:37:30
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

