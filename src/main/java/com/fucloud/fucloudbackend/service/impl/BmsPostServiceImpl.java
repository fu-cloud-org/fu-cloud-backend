package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsPostMapper;
import com.fucloud.fucloudbackend.dao.BmsTagMapper;
import com.fucloud.fucloudbackend.dao.UmsUserMapper;
import com.fucloud.fucloudbackend.model.dto.ReleasePostDTO;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.BmsPostTag;
import com.fucloud.fucloudbackend.model.entity.BmsTag;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.PostVO;
import com.fucloud.fucloudbackend.service.BmsPostService;
import com.fucloud.fucloudbackend.service.BmsPostTagService;
import com.fucloud.fucloudbackend.service.BmsTagService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BmsPostServiceImpl extends
        ServiceImpl<BmsPostMapper, BmsPost>
        implements BmsPostService {

    @Resource
    private BmsTagMapper bmsTagMapper;
    @Resource
    private UmsUserMapper umsUserMapper;
    @Autowired
    @Lazy
    private BmsTagService bmsTagService;
    @Autowired
    private UmsUserService umsUserService;
    @Autowired
    private BmsPostTagService bmsPostTagService;

    private void setPostTags(Page<PostVO> iPage) {
        iPage.getRecords().forEach(post -> {
            List<BmsPostTag> topicTags = bmsPostTagService.selectByPostId(post.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(BmsPostTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                post.setTags(tags);
            }
        });
    }

    @Override
    public Page<PostVO> getPostList(Page<PostVO> page, String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        setPostTags(iPage);
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsPost release(ReleasePostDTO dto, UmsUser principal) {
        BmsPost post1 = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getTitle, dto.getTitle()));
        Assert.isNull(post1, "话题已存在，请修改");

        // 封装
        BmsPost post = BmsPost.builder()
                .summary(dto.getSummary())
                .userId(principal.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(post);

        // 用户积分增加
        int newScore = principal.getScore() + 1;
        umsUserMapper.updateById(principal.setScore(newScore));

        // 标签
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> tags = bmsTagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            bmsPostTagService.createPostTag(post.getId(), tags);
        }

        return post;
    }
}
