package com.fucloud.fucloudbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.BmsTag;
import com.fucloud.fucloudbackend.service.BmsTagService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class BmsTagController extends BaseController {

    @Resource
    private BmsTagService bmsTagService;

    @GetMapping("/{name}")
    public ResultApi<Map<String, Object>> getPostsByTag(
            @PathVariable("name") String tagName,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size){

        Map<String, Object> map = new HashMap<>(16);
        LambdaQueryWrapper<BmsTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BmsTag::getName, tagName);
        BmsTag tag = bmsTagService.getOne(wrapper);
        Assert.notNull(tag, "标签不存在或已被管理员移除");
        Page<BmsPost> posts = bmsTagService.selectPostsByTagId(new Page<>(page, size), tag.getId());
        // other hot tags
        Page<BmsTag> hotTags = bmsTagService.page(new Page<>(1, 10),
                new LambdaQueryWrapper<BmsTag>()
                        .notIn(BmsTag::getName, tagName)
                        .orderByDesc(BmsTag::getPostCount));
        map.put("posts", posts);
        map.put("hotTags", hotTags);
        return ResultApi.success(map);
    }

}
