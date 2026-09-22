package com.school.flashmeal.controller;

import com.github.pagehelper.PageInfo;
import com.school.flashmeal.common.Result;
import com.school.flashmeal.dto.dish.CreateDishDTO;
import com.school.flashmeal.dto.dish.UpdateDishDTO;
import com.school.flashmeal.service.DishService;
import com.school.flashmeal.vo.dish.GetDishByIdVO;
import com.school.flashmeal.vo.dish.PageDishVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public Result<String> createDish(@RequestBody @Valid CreateDishDTO createDishDTO){
        return Result.success(dishService.createDish(createDishDTO));
    }

    @GetMapping
    public Result<PageInfo<PageDishVO>> pageDish(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){
        return Result.success(dishService.pageDish(pageNum,pageSize));
    }

    @GetMapping("/{id}")
    public Result<GetDishByIdVO> getDishById(@PathVariable Integer id){
        return Result.success(dishService.getDishById(id));
    }

    @PutMapping("/{id}")
    public Result<Boolean> updateDish(@PathVariable Integer id,@RequestBody @Valid UpdateDishDTO updateDishDTO){
        return Result.success(dishService.updateDish(id,updateDishDTO));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteDish(@PathVariable Integer id){
        return Result.success(dishService.deleteDish(id));
    }
}
