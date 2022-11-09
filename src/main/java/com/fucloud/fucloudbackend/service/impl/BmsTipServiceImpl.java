package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.BmsTipMapper;
import com.fucloud.fucloudbackend.model.entity.BmsTip;
import com.fucloud.fucloudbackend.service.BmsTipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BmsTipServiceImpl
        extends ServiceImpl<BmsTipMapper, BmsTip>
        implements BmsTipService {

    @Override
    public BmsTip getRandomTip() {
        BmsTip dailyTip = null;
        try {
            dailyTip = this.baseMapper.getRandomTip();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.info("tip转化失败");
        }
        return dailyTip;
    }

}
