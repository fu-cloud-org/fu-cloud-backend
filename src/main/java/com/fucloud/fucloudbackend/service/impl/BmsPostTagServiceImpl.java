package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsPostTagMapper;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.BmsPostTag;
import com.fucloud.fucloudbackend.service.BmsPostTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BmsPostTagServiceImpl
        extends ServiceImpl<BmsPostTagMapper, BmsPostTag>
        implements BmsPostTagService {

    @Override
    public Set<String> selectPostIdByTagId(String id) {
        return this.baseMapper.getPostIdsByTagId(id);
    }

    @Override
    public List<BmsPostTag> selectByPostId(String postId) {
        QueryWrapper<BmsPostTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsPostTag::getPostId, postId);
        return this.baseMapper.selectList(wrapper);
    }
}
