package io.github.xiaoyureed.shopeeproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeeproduct.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 18:28:59
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

