package com.fucloud.fucloudbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.common.exception.AssertsApi;
import com.fucloud.fucloudbackend.model.entity.BmsFollow;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.service.BmsFollowService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.fucloud.fucloudbackend.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/follow")
public class BmsFollowController extends BaseController {

    @Resource
    private BmsFollowService bmsFollowService;

    @Resource
    private UmsUserService umsUserService;

    @GetMapping("/subscribe/{userId}")
    public ResultApi<Object> handleFollow(@RequestHeader(value = USER_NAME) String userName
            , @PathVariable("userId") String parentId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        if (parentId.equals(umsUser.getId())) {
            AssertsApi.fail("您脸皮太厚了，怎么可以关注自己呢 😮");
        }
        BmsFollow one = bmsFollowService.getOne(
                new LambdaQueryWrapper<BmsFollow>()
                        .eq(BmsFollow::getParentId, parentId)
                        .eq(BmsFollow::getFollowerId, umsUser.getId()));
        if (!ObjectUtils.isEmpty(one)) {
            AssertsApi.fail("您已关注该用户");
        }

        BmsFollow follow = new BmsFollow();
        follow.setParentId(parentId);
        follow.setFollowerId(umsUser.getId());
        bmsFollowService.save(follow);
        return ResultApi.success(null, "关注成功");
    }

    @GetMapping("/unsubscribe/{userId}")
    public ResultApi<Object> handleUnFollow(@RequestHeader(value = USER_NAME) String userName
            , @PathVariable("userId") String parentId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        BmsFollow one = bmsFollowService.getOne(
                new LambdaQueryWrapper<BmsFollow>()
                        .eq(BmsFollow::getParentId, parentId)
                        .eq(BmsFollow::getFollowerId, umsUser.getId()));
        if (ObjectUtils.isEmpty(one)) {
            AssertsApi.fail("您还未关注该用户");
        }
        bmsFollowService.remove(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId)
                .eq(BmsFollow::getFollowerId, umsUser.getId()));
        return ResultApi.success(null, "取关成功");
    }

    @GetMapping("/validate/{postUserId}")
    public ResultApi<Map<String, Object>> isFollow(@RequestHeader(value = USER_NAME) String userName
            , @PathVariable("postUserId") String postUserId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(umsUser)) {
            BmsFollow one = bmsFollowService.getOne(new LambdaQueryWrapper<BmsFollow>()
                    .eq(BmsFollow::getParentId, postUserId)
                    .eq(BmsFollow::getFollowerId, umsUser.getId()));
            if (!ObjectUtils.isEmpty(one)) {
                map.put("hasFollow", true);
            }
        }
        return ResultApi.success(map);
    }

}
