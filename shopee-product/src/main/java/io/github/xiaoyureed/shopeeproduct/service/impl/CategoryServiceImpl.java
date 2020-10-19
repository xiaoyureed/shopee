package io.github.xiaoyureed.shopeeproduct.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.xiaoyureed.shopeecommon.utils.PageUtils;
import io.github.xiaoyureed.shopeecommon.utils.Query;

import io.github.xiaoyureed.shopeeproduct.dao.CategoryDao;
import io.github.xiaoyureed.shopeeproduct.entity.CategoryEntity;
import io.github.xiaoyureed.shopeeproduct.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> tree() {
        List<CategoryEntity> cates = baseMapper.selectList(null);
        return cates.stream()
                .filter(cate -> cate.getParentCid() == 0)
                .peek(cate -> fillChildren(cate, cates))
                .sorted(Comparator.comparingLong(CategoryEntity::getCatId))
                .collect(Collectors.toList());
    }

    private void fillChildren(CategoryEntity parent, List<CategoryEntity> cates) {
        List<CategoryEntity> children = cates.stream()
                .filter(cate -> cate.getParentCid().equals(parent.getCatId()))
                .sorted(Comparator.comparingLong(CategoryEntity::getCatId))
                .collect(Collectors.toList());
        if (children.size() > 0) {
            children.forEach(cate -> fillChildren(cate, children));
            parent.setChildren(children);
        }
    }


}