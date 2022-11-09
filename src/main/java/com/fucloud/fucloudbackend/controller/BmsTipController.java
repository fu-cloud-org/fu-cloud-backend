package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.entity.BmsTip;
import com.fucloud.fucloudbackend.service.BmsTipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tip")
public class BmsTipController extends BaseController{

    @Resource
    private BmsTipService bmsTipService;

    @GetMapping("/daily")
    public ResultApi<BmsTip> getRandomTip() {
        BmsTip tip = bmsTipService.getRandomTip();
        return ResultApi.success(tip);
    }

}
