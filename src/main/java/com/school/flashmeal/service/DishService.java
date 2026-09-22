package com.school.flashmeal.service;

import com.github.pagehelper.PageInfo;
import com.school.flashmeal.dto.dish.CreateDishDTO;
import com.school.flashmeal.dto.dish.UpdateDishDTO;
import com.school.flashmeal.vo.dish.GetDishByIdVO;
import com.school.flashmeal.vo.dish.PageDishVO;



public interface DishService {
    String createDish(CreateDishDTO createDishDTO);

    PageInfo<PageDishVO> pageDish(Integer pageNum, Integer pageSize);

    GetDishByIdVO getDishById(Integer id);

    Boolean updateDish(Integer id,UpdateDishDTO updateDishDTO);

    Boolean deleteDish(Integer id);
}
