package io.github.xiaoyureed.shopeeware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeeware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:40:35
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

