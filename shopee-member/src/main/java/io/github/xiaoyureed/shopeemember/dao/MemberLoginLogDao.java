package io.github.xiaoyureed.shopeemember.dao;

import io.github.xiaoyureed.shopeemember.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author xiaoyu
 * @email 775000738@qq.com
 * @date 2020-10-06 19:32:30
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
