package com.fucloud.fucloudbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fucloud.fucloudbackend.common.exception.AssertsApi;
import com.fucloud.fucloudbackend.controller.BmsFollow;
import com.fucloud.fucloudbackend.dao.BmsFollowMapper;
import com.fucloud.fucloudbackend.dao.BmsPostMapper;
import com.fucloud.fucloudbackend.dao.UmsUserMapper;
import com.fucloud.fucloudbackend.jwt.JwtUtil;
import com.fucloud.fucloudbackend.model.dto.LoginDTO;
import com.fucloud.fucloudbackend.model.dto.RegisterDTO;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.ProfileVO;
import com.fucloud.fucloudbackend.service.UmsUserService;
import com.fucloud.fucloudbackend.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsUserServiceImpl
        extends ServiceImpl<UmsUserMapper, UmsUser>
        implements UmsUserService {

    @Autowired
    private BmsPostMapper bmsPostMapper;
    @Autowired
    private BmsFollowMapper bmsFollowMapper;

    @Override
    public UmsUser executeRegister(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsUser::getUsername, dto.getName()).or().eq(UmsUser::getEmail, dto.getEmail());
        UmsUser umsUser = baseMapper.selectOne(wrapper);
        if (!ObjectUtils.isEmpty(umsUser)) {
//            AssertsApi.fail("账号或邮箱已存在！");
            return null;
        }
        UmsUser addUser = UmsUser.builder()
                .username(dto.getName())
                .alias(dto.getName())
                .password(MD5Utils.getPwd(dto.getPass()))
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);

        return addUser;
    }

    @Override
    public UmsUser getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, username));
    }

    @Override
    public String executeLogin(LoginDTO dto) {
        String token = null;
        try {
            UmsUser user = getUserByUsername(dto.getUsername());
            String encodePwd = MD5Utils.getPwd(dto.getPassword());
            if(!encodePwd.equals(user.getPassword()))
            {
                throw new Exception("密码错误");
            }
            token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
        } catch (Exception e) {
            log.warn("用户不存在or密码验证失败=======>"+ dto.getUsername());
        }
        return token;
    }

    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profileVO = new ProfileVO();
        UmsUser umsUser = baseMapper.selectById(id);
        BeanUtils.copyProperties(umsUser, profileVO);

        Long postCount = bmsPostMapper.selectCount(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, id));
        Long fansCount = bmsFollowMapper.selectCount(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, id));
        Long followerCount = bmsFollowMapper.selectCount(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getFollowerId, id));

        profileVO.setPostCount(postCount);
        profileVO.setFansCount(fansCount);
        profileVO.setFollowerCount(followerCount);

        return profileVO;
    }
}
