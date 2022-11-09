package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.service.BmsBillboardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/billboard")
public class BmsBillboard extends BaseController {

    @Resource
    private BmsBillboardService bmsBillboardService;

    public ResultApi<BmsBillboard>
}
