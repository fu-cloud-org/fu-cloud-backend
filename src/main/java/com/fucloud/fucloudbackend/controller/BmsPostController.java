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
import com.vdurmont.emoji.EmojiParser;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @GetMapping()
    public ResultApi<Map<String, Object>> view(@RequestParam("id") String id){
        Map<String, Object> map = bmsPostService.postView(id);
        return ResultApi.success(map);
    }

    @GetMapping("/recommend")
    public ResultApi<List<BmsPost>> getRecommend(@RequestParam("postId") String id) {
        List<BmsPost> post = bmsPostService.getRecommend(id);
        return ResultApi.success(post);
    }

    @PostMapping("/update")
    public ResultApi<BmsPost> update(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody BmsPost post) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        Assert.isTrue(umsUser.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        bmsPostService.updateById(post);
        return ResultApi.success(post);
    }

    @DeleteMapping("/delete/{id}")
    public ResultApi<String> delete(@RequestHeader(value = USER_NAME) String userName, @PathVariable("id") String id) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        BmsPost byId = bmsPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(umsUser.getId()), "你为什么可以删除别人的话题？？？");
        bmsPostService.removeById(id);
        return ResultApi.success(null,"删除成功");
    }

    @GetMapping("/myPost/{id}")
    public ResultApi<Set<PostVO>> getMyPost(@PathVariable String id) {
        return ResultApi.success(bmsPostService.getMyPost(id));
    }

}
