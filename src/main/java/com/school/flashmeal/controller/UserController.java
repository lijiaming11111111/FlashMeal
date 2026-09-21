package com.school.flashmeal.controller;

import com.school.flashmeal.common.BusinessException;
import com.school.flashmeal.common.Result;
import com.school.flashmeal.entity.User;
import com.school.flashmeal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Result<List<User>> listUsers(){
        List<User> users = userService.listUsers();
        if (users==null || users.isEmpty()){
           throw new BusinessException("查询数据为null");
        }
        return Result.success(users);
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Integer id){

        return Result.success(userService.getUserById(id));
    }

    @PostMapping
    public Result<String> createUser(@RequestBody User user){
        return Result.success(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public Result<Boolean> updateUser(@PathVariable Integer id,@RequestBody User user){
        return Result.success(userService.updateUser(id,user));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteUser(@PathVariable Integer id){
        return Result.success(userService.deleteUser(id));
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){
        return Result.success(userService.login(user.getName(),user.getPassword()));
    }
}
