package com.vphum.service.frontend.controller;

import com.vphum.service.repository.dao.User;
import com.vphum.service.frontend.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by darren.chen on 2018/3/14.
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private UserInfoService userInfoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    private static final Logger loggerService = LoggerFactory.getLogger("com-vphum-biz-info");

    public void index(){
        User user = new User();
        user.setName("darren.chen");
        user.setPhone(18822883388L);
        user.setSex("男");
        userInfoService.addUserInfo(user);
    }
}
