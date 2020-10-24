package io.github.xiaoyureed.shopeeproduct.service.impl;

import io.github.xiaoyureed.shopeeproduct.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeecommon.Query;

import io.github.xiaoyureed.shopeeproduct.dao.AttrGroupDao;
import io.github.xiaoyureed.shopeeproduct.entity.AttrGroupEntity;
import io.github.xiaoyureed.shopeeproduct.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        if (catelogId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), new QueryWrapper<>());
            return new PageUtils(page);
        }
        // select * from attr_group where catelog_id = #{catelogId}
        //and (attr_group_id = #{key} or attr_group_name like %#{key}% )
        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId);
        if (StringUtils.isNotBlank(key)) {
            // 错误
//            queryWrapper.eq("attr_group_id", key)
//                    .or().like("attr_group_name", key);// like, likeL, likeR
            // 必须要用 and 连接
            queryWrapper.and(q -> q.eq("attr_group_id", key)
                    .or().like("attr_group_name", key));
        }
        return new PageUtils(this.page(new Query<AttrGroupEntity>().getPage(params), queryWrapper));
    }

    @Autowired
    private CategoryService categoryService;

    @Override
    public AttrGroupEntity getByIdWithCatePath(Long attrGroupId) {
        AttrGroupEntity result = this.getById(attrGroupId);
        result.setCatelogPath(categoryService.findCatelogPath(result.getCategoryId()));
        return result;
    }

}