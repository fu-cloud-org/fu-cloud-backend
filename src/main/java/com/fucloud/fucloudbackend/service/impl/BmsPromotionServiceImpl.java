package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsPromotionMapper;
import com.fucloud.fucloudbackend.model.entity.BmsPromotion;
import com.fucloud.fucloudbackend.service.BmsPromotionService;
import org.springframework.stereotype.Service;

@Service
public class BmsPromotionServiceImpl extends ServiceImpl<BmsPromotionMapper, BmsPromotion> implements BmsPromotionService { }
