package com.school.flashmeal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.flashmeal.dto.dish.CreateDishDTO;
import com.school.flashmeal.dto.dish.UpdateDishDTO;
import com.school.flashmeal.entity.Dish;
import com.school.flashmeal.mapper.DishMapper;
import com.school.flashmeal.service.DishService;
import com.school.flashmeal.vo.dish.GetDishByIdVO;
import com.school.flashmeal.vo.dish.PageDishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Override
    @Transactional  //必须得整个业务完成才算完成 例：有两个sql一个成功一个失败则算作全部失败
    public String createDish(CreateDishDTO createDishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(createDishDTO,dish);
        dish.setCreateTime(LocalDateTime.now());
        dishMapper.insertDish(dish);
        return "新增成功";
    }

    @Override
    public PageInfo<PageDishVO> pageDish(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PageDishVO> pages=dishMapper.pageDish();
        return new PageInfo<>(pages);
    }

    @Override
    public GetDishByIdVO getDishById(Integer id) {
        return dishMapper.getDishById(id);
    }

    @Override
    @Transactional
    public Boolean updateDish(Integer id,UpdateDishDTO updateDishDTO) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setUpdateTime(LocalDateTime.now());
        BeanUtils.copyProperties(updateDishDTO,dish);
        return dishMapper.updateDish(dish);
    }

    @Override
    @Transactional
    public Boolean deleteDish(Integer id) {
        return dishMapper.deleteDish(id);
    }
}
