package io.github.xiaoyureed.shopeeproduct.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.xiaoyureed.shopeecommon.bean.PageUtils;
import io.github.xiaoyureed.shopeecommon.Query;

import io.github.xiaoyureed.shopeeproduct.dao.BrandDao;
import io.github.xiaoyureed.shopeeproduct.entity.BrandEntity;
import io.github.xiaoyureed.shopeeproduct.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");

        QueryWrapper<BrandEntity> q = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            q.eq("band_id", key).or().like("name", key);
        }


        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                q
        );

        return new PageUtils(page);
    }

}