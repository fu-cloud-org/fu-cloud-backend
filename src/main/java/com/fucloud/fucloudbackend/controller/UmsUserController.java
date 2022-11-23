package com.fucloud.fucloudbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fucloud.fucloudbackend.common.Constants;
import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.dao.UmsUserMapper;
import com.fucloud.fucloudbackend.model.dto.LoginDTO;
import com.fucloud.fucloudbackend.model.dto.RegisterDTO;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.ProfileVO;
import com.fucloud.fucloudbackend.service.BmsPostService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.fucloud.fucloudbackend.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/user")
public class UmsUserController extends BaseController {

    @Resource
    private UmsUserService umsUserService;

    @Resource
    private BmsPostService bmsPostService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultApi<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        UmsUser user = umsUserService.executeRegister(dto);
//        if (ObjectUtils.isEmpty(user)) {
//            return ResultApi.failed("账号注册失败");
//        }
        if (user == null) {
            return ResultApi.failed("用户名已存在或邮箱已被注册");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ResultApi.success(map);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultApi<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = umsUserService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ResultApi.failed("账号或密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ResultApi.success(map, "登录成功");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResultApi<UmsUser> getUser(@RequestHeader(value = USER_NAME) String userName) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        return ResultApi.success(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultApi<Object> logOut() {
        return ResultApi.success(null, "退出登陆成功");
    }

    @RequestMapping(value = "/updateAvatar", method = RequestMethod.POST)
    public Object updateAvatar(@RequestParam(value="userName") String userName,
                               @RequestParam("file") MultipartFile avatarFile){
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        String filePath = Constants.PROJECT_PATH +System.getProperty("file.separator")
                + "src" + System.getProperty("file.separator") + "main" + System.getProperty("file.separator") +
                "resources" + System.getProperty("file.separator") + "static" + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "avatar" + System.getProperty("file.separator") + userName;
        File file1 = new File(filePath);
        if(!file1.exists())
            file1.mkdirs();
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/cover/" + userName + '/' + fileName;
        try {
            avatarFile.transferTo(dest);
            return ResultApi.success("更新成功");
        } catch (IOException e){
            return ResultApi.failed("更新失败");
        }
    }


    @GetMapping("/profile")
    public ResultApi<ProfileVO> getProfile(@RequestParam("userId") String userId) {
        ProfileVO profileVO = umsUserService.getUserProfile(userId);
        return ResultApi.success(profileVO);
    }

    @Resource
    private UmsUserMapper umsUserMapper;

    @GetMapping("/{username}")
    public ResultApi<Map<String, Object>> getUserByName(@PathVariable("username") String username,
                                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        UmsUser userByName = umsUserService.getUserByUsername(username);
        UmsUser userById = umsUserMapper.selectById(username);
        UmsUser user = ObjectUtils.isEmpty(userById) ? userByName : userById;
        Assert.notNull(user, "用户不存在");
        Page<BmsPost> page = bmsPostService.page(new Page<>(pageNo, size),
                new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, user.getId()));
        map.put("user", user);
        map.put("posts", page);
        return ResultApi.success(map);
    }

}
