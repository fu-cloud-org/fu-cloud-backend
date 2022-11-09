package com.fucloud.fucloudbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fucloud.fucloudbackend.model.entity.BmsTip;

public interface BmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
