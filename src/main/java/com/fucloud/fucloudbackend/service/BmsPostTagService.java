package com.fucloud.fucloudbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fucloud.fucloudbackend.model.entity.BmsPostTag;
import com.fucloud.fucloudbackend.model.entity.BmsTag;

import java.util.List;
import java.util.Set;

public interface BmsPostTagService extends IService<BmsPostTag> {

    /**
     * 获取Topic Tag 关联记录
     *
     * @param postId
     * @return
     */
    List<BmsPostTag> selectByPostId(String postId);

    /**
     * 获取标签换脸话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> selectPostIdByTagId(String id);

    /**
     * 创建中间关系
     *
     * @param id
     * @param tags
     * @return
     */
    void createPostTag(String id, List<BmsTag> tags);

}
