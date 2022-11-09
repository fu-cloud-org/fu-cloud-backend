package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsBillboardMapper;
import com.fucloud.fucloudbackend.model.entity.BmsBillboard;
import com.fucloud.fucloudbackend.service.BmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class BmsBillboardServiceImpl
        extends ServiceImpl<BmsBillboardMapper, BmsBillboard>
        implements BmsBillboardService { }
