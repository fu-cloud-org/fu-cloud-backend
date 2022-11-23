package com.fucloud.fucloudbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fucloud.fucloudbackend.model.entity.BmsFollow;
import com.fucloud.fucloudbackend.model.vo.FansAndFollowersVO;

import java.util.Set;

public interface BmsFollowService extends IService<BmsFollow> {

    Set<FansAndFollowersVO> getFans(String id);

    Set<FansAndFollowersVO> getFollowers(String id);

}
