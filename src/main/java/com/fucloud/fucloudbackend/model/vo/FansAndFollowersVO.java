package com.fucloud.fucloudbackend.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FansAndFollowersVO {

    private String id;
    private String username;
    private String alias;
    private String avatar;
    private String sign;
    private String dep;
    private Boolean isMyFollowed;

}
