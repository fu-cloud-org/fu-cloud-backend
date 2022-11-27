package com.fucloud.fucloudbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fucloud.fucloudbackend.model.entity.Collect;
import com.fucloud.fucloudbackend.model.entity.UmsUser;

public interface CollectService extends IService<Collect> {

    void setCollect(UmsUser user, String postId);

    void cancelCollect(UmsUser user, String postId);

    Boolean isMyCollect(UmsUser user, String postId);

}
