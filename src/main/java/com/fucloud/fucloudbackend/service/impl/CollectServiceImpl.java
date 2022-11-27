package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsPostMapper;
import com.fucloud.fucloudbackend.dao.CollectMapper;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.Collect;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.service.CollectService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Resource
    private BmsPostMapper bmsPostMapper;

    @Override
    public void setCollect(UmsUser user, String postId) {
        String me = user.getId();
        Collect collect = Collect.builder()
                .userId(me)
                .postId(postId)
                .build();
        this.baseMapper.insert(collect);
        BmsPost post = bmsPostMapper.selectById(postId);
        post.setCollects(post.getCollects() + 1);
        bmsPostMapper.updateById(post);
    }

    @Override
    public void cancelCollect(UmsUser user, String postId) {
        Collect c = this.baseMapper.selectOne(new LambdaQueryWrapper<Collect>()
                .eq(Collect::getUserId, user.getId())
                .eq(Collect::getPostId, postId));
        Assert.notNull(c, "Collect not found");
        BmsPost post = bmsPostMapper.selectById(postId);
        post.setCollects(post.getCollects() - 1);
        this.baseMapper.deleteById(c.getId());
    }

    @Override
    public Boolean isMyCollect(UmsUser user, String postId) {
        Collect c = this.baseMapper.selectOne(new LambdaQueryWrapper<Collect>()
                .eq(Collect::getUserId, user.getId())
                .eq(Collect::getPostId, postId));
        return !ObjectUtils.isEmpty(c);
    }
}
