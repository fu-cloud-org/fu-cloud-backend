package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.model.entity.BmsPromotion;
import com.fucloud.fucloudbackend.service.BmsPromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController {

    @Resource
    private BmsPromotionService bmsPromotionService;

    @GetMapping("/getAll")
    public ResultApi<List<BmsPromotion>> getPromotionList() {
        List<BmsPromotion> bmsPromotionList = bmsPromotionService.list();
        return ResultApi.success(bmsPromotionList);
    }
}
