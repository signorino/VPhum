package com.vphum.service.frontend.repository.mapper;

import com.vphum.service.frontend.repository.dao.User;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Long id);
    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}