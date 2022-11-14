package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsPostTagMapper;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.BmsPostTag;
import com.fucloud.fucloudbackend.model.entity.BmsTag;
import com.fucloud.fucloudbackend.service.BmsPostTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public void createPostTag(String id, List<BmsTag> tags) {
        // 先删除topicId对应的所有记录
        this.baseMapper.delete(new LambdaQueryWrapper<BmsPostTag>().eq(BmsPostTag::getPostId, id));

        // 循环保存对应关联
        tags.forEach(tag -> {
            BmsPostTag postTag = new BmsPostTag();
            postTag.setPostId(id);
            postTag.setTagId(tag.getId());
            this.baseMapper.insert(postTag);
        });

    }
}
