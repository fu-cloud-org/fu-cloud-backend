package com.fucloud.fucloudbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.vo.PostVO;
import com.fucloud.fucloudbackend.service.BmsPostService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Resource
    private BmsPostService bmsPostService;

    @GetMapping("/list")
    public ResultApi<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = bmsPostService.getPostList(new Page<>(pageNo, pageSize), tab);
        return ResultApi.success(list);
    }

}
