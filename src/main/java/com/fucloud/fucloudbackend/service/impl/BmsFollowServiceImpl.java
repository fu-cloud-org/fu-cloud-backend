package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.model.entity.BmsFollow;
import com.fucloud.fucloudbackend.dao.BmsFollowMapper;
import com.fucloud.fucloudbackend.service.BmsFollowService;
import org.springframework.stereotype.Service;

@Service
public class BmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements BmsFollowService { }
