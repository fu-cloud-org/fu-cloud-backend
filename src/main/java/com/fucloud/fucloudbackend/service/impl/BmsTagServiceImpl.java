package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsTagMapper;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.BmsTag;
import com.fucloud.fucloudbackend.service.BmsPostService;
import com.fucloud.fucloudbackend.service.BmsPostTagService;
import com.fucloud.fucloudbackend.service.BmsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BmsTagServiceImpl
        extends ServiceImpl<BmsTagMapper, BmsTag>
        implements BmsTagService {

    @Autowired
    private BmsPostTagService bmsPostTagService;

    @Autowired
    private BmsPostService bmsPostService;


    @Override
    public Page<BmsPost> selectPostsByTagId(Page<BmsPost> postPage, String id) {
        // 获取关联的帖子ID
        Set<String> ids = bmsPostTagService.selectPostIdByTagId(id);
        LambdaQueryWrapper<BmsPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(BmsPost::getId, ids);

        return bmsPostService.page(postPage, wrapper);
    }
}
