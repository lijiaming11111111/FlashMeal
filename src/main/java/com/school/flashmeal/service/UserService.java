package com.school.flashmeal.service;

import com.github.pagehelper.PageInfo;
import com.school.flashmeal.dto.user.CreateUserDTO;
import com.school.flashmeal.dto.user.ListUsersDTO;
import com.school.flashmeal.dto.user.LoginUserDTO;
import com.school.flashmeal.dto.user.UpdateUserDTO;
import com.school.flashmeal.vo.user.GetUserByIdVO;
import com.school.flashmeal.vo.user.ListUsersVO;

public interface UserService {
    PageInfo<ListUsersVO> listUsers(ListUsersDTO listUsersDTO);

    String createUser(CreateUserDTO createUserDTO);

    Boolean updateUser(Integer id, UpdateUserDTO updateUserDTO);

    Boolean deleteUser(Integer id);

    GetUserByIdVO getUserById(Integer id);

    String login(LoginUserDTO loginUserDTO);
}
