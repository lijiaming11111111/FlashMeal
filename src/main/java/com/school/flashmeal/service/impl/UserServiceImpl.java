package com.school.flashmeal.service.impl;

import com.school.flashmeal.entity.User;
import com.school.flashmeal.mapper.UserMapper;
import com.school.flashmeal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.selectQuery();
    }

    @Override
    public String createUser(User user) {
        userMapper.insertUser(user);
        return "新增成功";
    }

    @Override
    public Boolean updateUser(Integer id,User user) {
        user.setId(id);
        return userMapper.updateUser(user);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }


}
