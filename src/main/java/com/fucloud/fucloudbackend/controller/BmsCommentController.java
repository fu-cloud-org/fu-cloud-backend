package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.dto.CommentDTO;
import com.fucloud.fucloudbackend.model.entity.BmsComment;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.CommentVO;
import com.fucloud.fucloudbackend.service.BmsCommentService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fucloud.fucloudbackend.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/comment")
public class BmsCommentController {

    @Autowired
    private BmsCommentService bmsCommentService;

    @Autowired
    private UmsUserService umsUserService;

    @GetMapping("/commentLists")
    public ResultApi<List<CommentVO>> getCommentsByPostId(@RequestParam(value = "postId") String postId) {
        List<CommentVO> commentsVO = bmsCommentService.getCommentsByPostId(postId);
        return ResultApi.success(commentsVO);
    }

    @PostMapping("/create")
    public ResultApi<BmsComment> createComment(@RequestHeader(value = USER_NAME) String username,
                                               @RequestBody CommentDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            return ResultApi.failed("User not found");
        } else  {
            BmsComment comment = bmsCommentService.create(dto, user);
            return ResultApi.success(comment);
        }

    }

}
