package com.fucloud.fucloudbackend.model.vo;

import lombok.Data;

@Data
public class ProfileVO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 别称
     */
    private String alias;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 关注数
     */
    private Long fansCount;

    /**
     * 关注者数
     */
    private Long followerCount;

    /**
     * 文章数
     */
    private Long postCount;

    /**
     * 专栏数
     */
    private Integer columns;

    /**
     * 评论数
     */
    private Integer commentCount;

}
