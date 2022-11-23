package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.dao.UmsUserMapper;
import com.fucloud.fucloudbackend.model.entity.BmsFollow;
import com.fucloud.fucloudbackend.dao.BmsFollowMapper;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.FansAndFollowersVO;
import com.fucloud.fucloudbackend.service.BmsFollowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class BmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements BmsFollowService {

    @Resource
    private UmsUserMapper umsUserMapper;

    @Override
    public Set<FansAndFollowersVO> getFans(String id) {
        Set<FansAndFollowersVO> fansVO = new HashSet<>();
        this.baseMapper.selectList(
                new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, id)
        ).forEach(fan -> {
            UmsUser fanUser = umsUserMapper.selectById(fan.getFollowerId());
            fansVO.add(FansAndFollowersVO.builder()
                    .id(fanUser.getId())
                    .username(fanUser.getUsername())
                    .avatar(fanUser.getAvatar())
                    .alias(fanUser.getAlias())
                    .dep(fanUser.getDep())
                    .sign(fanUser.getSign())
                    .build());
        });
        return fansVO;
    }

    @Override
    public Set<FansAndFollowersVO> getFollowers(String id) {
        Set<FansAndFollowersVO> followersVO = new HashSet<>();
        this.baseMapper.selectList(
                new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getFollowerId, id)
        ).forEach(follower -> {
            UmsUser followerUser = umsUserMapper.selectById(follower.getParentId());
            followersVO.add(FansAndFollowersVO.builder()
                    .id(followerUser.getId())
                    .username(followerUser.getUsername())
                    .avatar(followerUser.getAvatar())
                    .alias(followerUser.getAlias())
                    .dep(followerUser.getDep())
                    .sign(followerUser.getSign())
                    .build());
        });
        return followersVO;
    }
}
