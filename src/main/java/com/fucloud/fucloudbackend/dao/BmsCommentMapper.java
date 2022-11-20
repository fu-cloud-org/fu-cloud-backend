package com.fucloud.fucloudbackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fucloud.fucloudbackend.model.entity.BmsComment;
import com.fucloud.fucloudbackend.model.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BmsCommentMapper extends BaseMapper<BmsComment> {

    /**
     * getCommentsByPostId
     *
     * @param postId
     * @return
     */
    List<CommentVO> getCommentsByPostId(@Param("postId") String postId);

}
