package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsCommentMapper;
import com.fucloud.fucloudbackend.model.dto.CommentDTO;
import com.fucloud.fucloudbackend.model.entity.BmsComment;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.CommentVO;
import com.fucloud.fucloudbackend.service.BmsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BmsCommentServiceImpl
        extends ServiceImpl<BmsCommentMapper, BmsComment> implements BmsCommentService {

    @Override
    public List<CommentVO> getCommentsByPostId(String postId) {
        List<CommentVO> listBmsComment = new ArrayList<>();
        try {
            listBmsComment = this.baseMapper.getCommentsByPostId(postId);
        } catch (Exception e) {
            log.info("getCommentsByPostId error: {}", e.getMessage());
        }
        return listBmsComment;
    }



    @Override
    public BmsComment create(CommentDTO dto, UmsUser principal) {
        BmsComment comment = BmsComment.builder()
                .userId(principal.getId())
                .postId(dto.getPostId())
                .content(dto.getContent())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(comment);
        return comment;
    }
}
