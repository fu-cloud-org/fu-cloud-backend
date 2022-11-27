package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.dao.BmsPostMapper;
import com.fucloud.fucloudbackend.model.dto.CommentDTO;
import com.fucloud.fucloudbackend.model.entity.BmsComment;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.CommentVO;
import com.fucloud.fucloudbackend.service.BmsCommentService;
import com.fucloud.fucloudbackend.service.BmsPostService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public ResultApi<BmsComment> createComment(@RequestParam(value = "userId") String userId,
                                               @RequestBody CommentDTO dto) {
        UmsUser user = umsUserService.getById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return ResultApi.failed("User not found");
        } else  {
            BmsComment comment = bmsCommentService.create(dto, user);
            return ResultApi.success(comment);
        }

    }

    @Resource
    public BmsPostMapper bmsPostMapper;

    @DeleteMapping("/delete/{id}")
    public ResultApi<String> delete(@RequestParam(value = "username") String username,
                                    @PathVariable String id) {
        UmsUser user = umsUserService.getUserByUsername(username);
        BmsComment comment = bmsCommentService.getById(id);
        Assert.notNull(comment, "Comment not found");
        Assert.isTrue(comment.getUserId().equals(user.getId()), "You are not the author");
        bmsCommentService.removeById(id);
        BmsPost post = bmsPostMapper.selectById(comment.getPostId());
        post.setComments(post.getComments() - 1);
        bmsPostMapper.updateById(post);

        return ResultApi.success("Delete comment successfully");
    }

}
