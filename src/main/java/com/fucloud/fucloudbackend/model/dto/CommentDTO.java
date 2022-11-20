package com.fucloud.fucloudbackend.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String postId;
    private String content;

}
