package com.fucloud.fucloudbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.entity.BmsBillboard;
import com.fucloud.fucloudbackend.service.BmsBillboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/billboard")
public class BmsBillboardController extends BaseController {

    @Resource
    private BmsBillboardService bmsBillboardService;

    @GetMapping("/show")
    public ResultApi<BmsBillboard> getNotices() {
        List<BmsBillboard> list = bmsBillboardService.list(
                new LambdaQueryWrapper<BmsBillboard>().eq(BmsBillboard::isShow, true)
        );
        return ResultApi.success(list.get(list.size() - 1));
    }
}
