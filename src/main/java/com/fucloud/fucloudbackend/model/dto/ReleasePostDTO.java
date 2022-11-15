package com.fucloud.fucloudbackend.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReleasePostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 简介
     */
    private String summary = "不写简介的帖子可不是一篇好帖子欧";

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 封面
     */
    private String cover = "/img/cover/fu-cloud-org.png";

}
