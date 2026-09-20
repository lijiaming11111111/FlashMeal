package com.school.flashmeal.service.impl;

import com.school.flashmeal.common.BusinessException;
import com.school.flashmeal.entity.User;
import com.school.flashmeal.mapper.UserMapper;
import com.school.flashmeal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
    @Cacheable(value = "user",key = "#id")
    public User getUserById(Integer id){
        System.out.println("==============查询成功===");
        User user = userMapper.selectUserById(id);
        if (user == null){
            throw new BusinessException("查询用户为null");
        }
        return user;
    }

    @Override
    public String createUser(User user) {
        userMapper.insertUser(user);
        return "新增成功";
    }

    @Override
    @CacheEvict(value = "user",key = "#id", beforeInvocation = true)
    public Boolean updateUser(Integer id,User user) {
        user.setId(id);
        return userMapper.updateUser(user);
    }

    @Override
    @CacheEvict(value = "user",key = "#id")
    public Boolean deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }


}
