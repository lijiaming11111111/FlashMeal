package com.school.flashmeal.mapper;

import com.school.flashmeal.entity.Dish;
import com.school.flashmeal.vo.dish.GetDishByIdVO;
import com.school.flashmeal.vo.dish.PageDishVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {
    @Insert("INSERT INTO dish(name,price,image,category,description," +
            "status,create_time) " +
            "VALUES (#{name},#{price},#{image},#{category}," +
            "#{description},#{status.code},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertDish(Dish dish);

    @Select("SELECT * FROM dish")
    List<PageDishVO> pageDish();

    @Select("SELECT * FROM dish WHERE id = #{id}")
    GetDishByIdVO getDishById(Integer id);

    @Update("update dish SET name=#{name},price=#{price}," +
            "image=#{image},category=#{category}," +
            "description=#{description},status=#{status.code},update_time=#{updateTime} WHERE id = #{id}")
    Boolean updateDish(Dish dish);

    @Delete("DELETE FROM dish WHERE id=#{id}")
    Boolean deleteDish(Integer id);
}
