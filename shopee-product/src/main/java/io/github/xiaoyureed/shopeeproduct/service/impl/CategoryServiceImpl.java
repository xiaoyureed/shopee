package io.github.xiaoyureed.shopeeproduct.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeecommon.Query;

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
        List<CategoryEntity> all = baseMapper.selectList(null);
        return all.stream()
                .filter(cate -> cate.getParentCid() == 0)
                .peek(cate -> cate.setChildren(resolveChildren(cate, all)))
                .sorted(Comparator.comparingLong(CategoryEntity::getCatId))
                .collect(Collectors.toList());
    }

    private List<CategoryEntity> resolveChildren(CategoryEntity parent, List<CategoryEntity> all) {
        return all.stream()
                // filter out sub menu
                .filter(ca -> ca.getParentCid().equals(parent.getCatId()))
                // fill children for sub menu
                .peek(ca -> ca.setChildren(resolveChildren(ca, all)))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> cateIds) {
        // todo Check whether the menu is used in other place

        baseMapper.deleteBatchIds(cateIds);
    }

    @Override
    public Long[] findCatelogPath(Long categoryId) {
        LinkedList<Long> result = new LinkedList<>();

        appendParent(categoryId, result);

        return  result.toArray(new Long[result.size()]);
    }

    private void appendParent(Long categoryId, LinkedList<Long> result) {
        result.addFirst(categoryId);
        CategoryEntity cate = this.getById(categoryId);
        Long parentCid = cate.getParentCid();
        if (parentCid != 0) {
            appendParent(parentCid, result);
        }

    }


//    private void fillChildren(CategoryEntity parent, List<CategoryEntity> all) {
//        List<CategoryEntity> children = all.stream()
//                // filter parent's child
//                .filter(cate -> cate.getParentCid().equals(parent.getCatId()))
//                // sort
//                .sorted(Comparator.comparingLong(CategoryEntity::getCatId))
//                .collect(Collectors.toList());
//        if (children.size() > 0) {
//            children.forEach(cate -> fillChildren(cate, children));
//            // fill children for parent
//            parent.setChildren(children);
//        }
//    }


}