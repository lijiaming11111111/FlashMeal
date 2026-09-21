package com.school.flashmeal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.flashmeal.common.BusinessException;
import com.school.flashmeal.dto.user.CreateUserDTO;
import com.school.flashmeal.dto.user.ListUsersDTO;
import com.school.flashmeal.dto.user.LoginUserDTO;
import com.school.flashmeal.dto.user.UpdateUserDTO;
import com.school.flashmeal.entity.User;
import com.school.flashmeal.mapper.UserMapper;
import com.school.flashmeal.service.UserService;
import com.school.flashmeal.util.JwtUtil;
import com.school.flashmeal.vo.user.GetUserByIdVO;
import com.school.flashmeal.vo.user.ListUsersVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public PageInfo<ListUsersVO> listUsers(ListUsersDTO listUsersDTO) {
        PageHelper.startPage(listUsersDTO.getPageNum(), listUsersDTO.getPageSize());
        List<ListUsersVO> users = userMapper.selectQuery();
        return new PageInfo<>(users);
    }

    @Override
    @Cacheable(value = "user",key = "#id")
    public GetUserByIdVO getUserById(Integer id){
        System.out.println("==============查询成功===");
        GetUserByIdVO getUserByIdVO = userMapper.selectUserByName(id);
        if (getUserByIdVO == null){
            throw new BusinessException("查询用户为null");
        }
        return getUserByIdVO;
    }

    @Override
    public String createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        BeanUtils.copyProperties(createUserDTO, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
        return String.valueOf(user.getId());
    }

    @Override
    @CacheEvict(value = "user",key = "#id", beforeInvocation = true)
    public Boolean updateUser(Integer id, UpdateUserDTO updateUserDTO) {
        User user = userMapper.selectUserById(id);
        if (user == null){
            throw new BusinessException("查询用户为null");
        }
        user.setName(updateUserDTO.getName());
        user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
        return userMapper.updateUser(user);
    }

    @Override
    @CacheEvict(value = "user",key = "#id")
    public Boolean deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public String login(LoginUserDTO loginUserDTO) {
        User user = userMapper.selectUsername(loginUserDTO.getName());
        if (user == null){
            throw new BusinessException("用户不存在");
        }
        if (!passwordEncoder.matches(loginUserDTO.getPassword(),user.getPassword())){
            throw new BusinessException("密码错误");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getName());
        return JwtUtil.creatJwt(map);
    }
}
