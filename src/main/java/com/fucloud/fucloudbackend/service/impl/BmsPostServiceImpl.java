package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsPostMapper;
import com.fucloud.fucloudbackend.dao.BmsTagMapper;
import com.fucloud.fucloudbackend.dao.UmsUserMapper;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.BmsPostTag;
import com.fucloud.fucloudbackend.model.entity.BmsTag;
import com.fucloud.fucloudbackend.model.vo.PostVO;
import com.fucloud.fucloudbackend.service.BmsPostService;
import com.fucloud.fucloudbackend.service.BmsPostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BmsPostServiceImpl extends
        ServiceImpl<BmsPostMapper, BmsPost>
        implements BmsPostService {

    @Resource
    private BmsTagMapper bmsTagMapper;
//    @Resource
//    private UmsUserMapper umsUserMapper;
//    @Autowired
//    @Lazy
//    private BmsTagService umsTagService;
//    @Autowired
//    private UmsUserService umsUserService;
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
}
