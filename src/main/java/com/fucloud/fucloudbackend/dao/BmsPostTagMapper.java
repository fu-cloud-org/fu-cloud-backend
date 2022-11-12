package com.fucloud.fucloudbackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fucloud.fucloudbackend.model.entity.BmsPostTag;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface BmsPostTagMapper extends BaseMapper<BmsPostTag> {

    /**
     * 根据标签获取话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> getPostIdsByTagId(@Param("id") String id);

}
