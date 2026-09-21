package com.school.flashmeal.service.impl;

import com.school.flashmeal.common.BusinessException;
import com.school.flashmeal.entity.User;
import com.school.flashmeal.mapper.UserMapper;
import com.school.flashmeal.service.UserService;
import com.school.flashmeal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectUsername(username);
        if (user == null){
            throw new BusinessException("用户不存在");
        }
        if (!passwordEncoder.matches(password,user.getPassword())){
            throw new BusinessException("密码错误");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getName());
        return JwtUtil.creatJwt(map);
    }
}
