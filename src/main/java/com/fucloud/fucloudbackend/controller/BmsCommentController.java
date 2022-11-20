package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.vo.CommentVO;
import com.fucloud.fucloudbackend.service.BmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class BmsCommentController {

    @Autowired
    private BmsCommentService bmsCommentService;

    @GetMapping("commentLists")
    public ResultApi<List<CommentVO>> getCommentsByPostId(@RequestParam(value = "postId") String postId) {
        List<CommentVO> commentsVO = bmsCommentService.getCommentsByPostId(postId);
        return ResultApi.success(commentsVO);
    }

}
