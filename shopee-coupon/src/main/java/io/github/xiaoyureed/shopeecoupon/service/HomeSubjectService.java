package io.github.xiaoyureed.shopeecoupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeecoupon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:04:55
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

