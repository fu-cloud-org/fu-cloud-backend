package com.fucloud.fucloudbackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fucloud.fucloudbackend.model.entity.BmsTip;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsTipMapper extends BaseMapper<BmsTip> {
    BmsTip getRandomTip();
}
