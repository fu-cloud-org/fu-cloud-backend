package com.fucloud.fucloudbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fucloud.fucloudbackend.model.entity.BmsPost;
import com.fucloud.fucloudbackend.model.entity.BmsTag;

public interface BmsTagService extends IService<BmsTag> {

    /**
     * 获取标签关联话题
     *
     * @param postPage
     * @param id
     * @return
     */
    Page<BmsPost> selectPostsByTagId(Page<BmsPost> postPage, String id);

}
