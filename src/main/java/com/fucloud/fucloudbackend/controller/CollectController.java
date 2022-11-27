package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.service.CollectService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.fucloud.fucloudbackend.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/collect")
public class CollectController extends BaseController{

    @Resource
    private CollectService collectService;

    @Resource
    private UmsUserService umsUserService;

    @GetMapping("/set")
    public ResultApi<Object> setCollect(@RequestParam(USER_NAME) String username,
                                        @RequestParam(value = "postId") String postId) {
        UmsUser me = umsUserService.getUserByUsername(username);
        if (ObjectUtils.isEmpty(me)) {
            return ResultApi.failed("User not found");
        } else {
            collectService.setCollect(me, postId);
            return ResultApi.success("Collect success");
        }
    }

    @GetMapping("/cancel")
    public ResultApi<Object> cancelCollect(@RequestParam(USER_NAME) String username,
                                           @RequestParam(value = "postId") String postId) {
        UmsUser me = umsUserService.getUserByUsername(username);
        if (ObjectUtils.isEmpty(me)) {
            return ResultApi.failed("User not found");
        } else {
            collectService.cancelCollect(me, postId);
            return ResultApi.success("Cancel collect success");
        }
    }

    @GetMapping("/isCollected")
public ResultApi<Map<String, String>> isCollected(@RequestParam(USER_NAME) String username,
                                         @RequestParam(value = "postId") String postId) {
        UmsUser me = umsUserService.getUserByUsername(username);
        Map<String, String> map = new HashMap<>();
        if (ObjectUtils.isEmpty(me)) {
            return ResultApi.failed("User not found");
        } else {
            boolean isCollected = collectService.isMyCollect(me, postId);
            map.put("isCollected", String.valueOf(isCollected));
            return ResultApi.success(map);
        }
    }

}
