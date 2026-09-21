package com.school.flashmeal.mapper;

import com.school.flashmeal.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * FROM `user` ")
    List<User> selectQuery();

    @Insert("INSERT INTO `user`(name,password) VALUES (#{name},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertUser(User user);

    @Update("UPDATE `user` SET name=#{name} WHERE id=#{id}")
    Boolean updateUser(User user);

    @Delete("DELETE FROM `user` where id=#{id}")
    Boolean deleteUser(Integer id);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User selectUserById(Integer id);

    @Select("SELECT * FROM user WHERE name=#{userName}")
    User selectUsername(String userName);
}
