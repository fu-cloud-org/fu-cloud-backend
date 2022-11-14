package com.fucloud.fucloudbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.dto.ReleasePostDTO;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.PostVO;
import com.fucloud.fucloudbackend.service.BmsPostService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.fucloud.fucloudbackend.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Resource
    private BmsPostService bmsPostService;

    @Resource
    private UmsUserService umsUserService;

    @GetMapping("/list")
    public ResultApi<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = bmsPostService.getPostList(new Page<>(pageNo, pageSize), tab);
        return ResultApi.success(list);
    }

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public ResultApi<BmsPost> release(@RequestParam(value = "userName") String userName
            , @RequestBody ReleasePostDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost post = bmsPostService.release(dto, user);
        return ResultApi.success(post);
    }

}
