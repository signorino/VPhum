package com.vphum.service.frontend.service;

import com.vphum.service.repository.UserRepository;
import com.vphum.service.repository.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by darren.chen on 2018/3/19.
 */
@Service
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;

    public Integer addUserInfo(User user){
        return  userRepository.insertSelective(user);
    }

}
