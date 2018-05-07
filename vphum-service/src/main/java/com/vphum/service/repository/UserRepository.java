package com.vphum.service.repository;

import com.vphum.service.repository.dao.User;
import com.vphum.service.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by darren.chen on 2018/3/19.
 */
@Service
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public int insertSelective(User user){
        return userMapper.insert(user);
    }
}
