package com.fucloud.fucloudbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.controller.BmsFollow;
import com.fucloud.fucloudbackend.dao.BmsFollowMapper;
import org.springframework.stereotype.Service;

@Service
public class BmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements BmsFollowService { }
