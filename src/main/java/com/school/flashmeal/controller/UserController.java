package com.school.flashmeal.controller;

import com.github.pagehelper.PageInfo;
import com.school.flashmeal.common.Result;
import com.school.flashmeal.dto.user.CreateUserDTO;
import com.school.flashmeal.dto.user.ListUsersDTO;
import com.school.flashmeal.dto.user.LoginUserDTO;
import com.school.flashmeal.dto.user.UpdateUserDTO;
import com.school.flashmeal.service.UserService;
import com.school.flashmeal.vo.user.GetUserByIdVO;
import com.school.flashmeal.vo.user.ListUsersVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Result<PageInfo<ListUsersVO>> listUsers(@Valid ListUsersDTO listUsersDTO){
        PageInfo<ListUsersVO> pageInfo = userService.listUsers(listUsersDTO);
        return Result.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Result<GetUserByIdVO> getUserById(@PathVariable Integer id){
        return Result.success(userService.getUserById(id));
    }

    @PostMapping
    public Result<String> createUser(@RequestBody @Valid CreateUserDTO createUserDTO){
        return Result.success("新增用户成功",userService.createUser(createUserDTO));
    }

    @PutMapping("/{id}")
    public Result<Boolean> updateUser(@PathVariable Integer id, @RequestBody @Valid UpdateUserDTO updateUserDTO){
        return Result.success(userService.updateUser(id,updateUserDTO));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteUser(@PathVariable Integer id){
        return Result.success(userService.deleteUser(id));
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid LoginUserDTO loginUserDTO){
        return Result.success(userService.login(loginUserDTO));
    }
}
