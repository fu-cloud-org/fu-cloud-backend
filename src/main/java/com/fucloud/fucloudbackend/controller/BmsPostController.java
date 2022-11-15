package com.fucloud.fucloudbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fucloud.fucloudbackend.common.Constants;
import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.dto.ReleasePostDTO;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.PostVO;
import com.fucloud.fucloudbackend.service.BmsPostService;
import com.fucloud.fucloudbackend.service.UmsUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;

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

    @RequestMapping(value = "/uploadCover", method = RequestMethod.POST)
    public Object uploadCover(@RequestParam(value = "userName") String userName
            , @RequestParam("file") MultipartFile coverFile) {
        String fileName = System.currentTimeMillis() + coverFile.getOriginalFilename();
        String filePath = Constants.PROJECT_PATH +System.getProperty("file.separator")
                + "src" + System.getProperty("file.separator") + "main" + System.getProperty("file.separator") +
                "resources" + System.getProperty("file.separator") + "static" + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "cover" + System.getProperty("file.separator") + userName;
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/cover/" + userName + '/' + fileName;
        try {
            coverFile.transferTo(dest);
            return ResultApi.success(imgPath);
        } catch (IOException e) {
            return ResultApi.failed("上传失败");
        }

    }

}