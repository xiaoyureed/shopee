package io.github.xiaoyureed.shopeeproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeeproduct.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 18:28:59
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

