package com.fucloud.fucloudbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.vo.PostVO;
import com.fucloud.fucloudbackend.service.BmsPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/search")
public class SearchController extends BaseController{

    @Resource
    private BmsPostService bmsPostService;

    @GetMapping
    public ResultApi<Page<PostVO>> searchList(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {

        Page<PostVO> page = bmsPostService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ResultApi.success(page);

    }

}
