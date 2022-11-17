package com.fucloud.fucloudbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fucloud.fucloudbackend.model.dto.ReleasePostDTO;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.UmsUser;
import com.fucloud.fucloudbackend.model.vo.PostVO;

import java.util.Map;

public interface BmsPostService extends IService<BmsPost> {

    /**
     * 获取首页话题列表
     *
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> getPostList(Page<PostVO> page, String tab);

    /**
     * 发布
     *
     * @param dto
     * @param principal
     * @return
     */
    BmsPost release(ReleasePostDTO dto, UmsUser principal);

    /**
     * 查看话题详情
     *
     * @param id
     * @return
     */

    Map<String, Object> postView(String id);

}
