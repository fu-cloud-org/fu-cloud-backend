package com.fucloud.fucloudbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fucloud.fucloudbackend.model.dto.CommentDTO;
import com.fucloud.fucloudbackend.model.entity.BmsComment;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.CommentVO;

import java.util.List;

public interface BmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param postId
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByPostId(String postId);

    /**
     *
     *
     * @param dto, principal
     * @return {@link BmsComment}
     */
    BmsComment create(CommentDTO dto, UmsUser principal);
}
