package com.school.flashmeal.service;

import com.school.flashmeal.entity.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    String createUser(User user);

    Boolean updateUser(Integer id, User user);

    Boolean deleteUser(Integer id);

    User getUserById(Integer id);
}
