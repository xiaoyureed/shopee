package io.github.xiaoyureed.shopeemember.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.xiaoyureed.shopeecommon.utils.PageUtils;
import io.github.xiaoyureed.shopeemember.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:32:30
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

