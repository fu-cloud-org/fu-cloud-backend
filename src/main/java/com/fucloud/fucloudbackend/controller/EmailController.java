package com.fucloud.fucloudbackend.controller;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import com.fucloud.fucloudbackend.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class EmailController extends BaseController{

    @Autowired
    private EmailService emailService;

    @RequestMapping("getCode")
    @ResponseBody
    public ResultApi<String> getVerifiedCode(@RequestParam("to") String to){
        String verifiedCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的验证码为：" + verifiedCode;
        emailService.sendSimpleMail(to, "Welcome to join fu-cloud", message);
        return ResultApi.success(verifiedCode);
    }

}
