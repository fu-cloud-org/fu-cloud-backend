package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.dto.LoginDTO;
import com.fucloud.fucloudbackend.model.dto.RegisterDTO;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UmsUserController extends BaseController {

    @Resource
    private UmsUserService umsUserService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultApi<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        UmsUser user = umsUserService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ResultApi.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ResultApi.success(map);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultApi<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = umsUserService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ResultApi.failed("账号密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ResultApi.success(map, "登录成功");
    }

}
