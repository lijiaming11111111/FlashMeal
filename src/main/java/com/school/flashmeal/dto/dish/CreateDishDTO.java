package com.school.flashmeal.dto.dish;

import com.school.flashmeal.enums.dish.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateDishDTO {
    @NotBlank(message = "菜品名称不能为空")
    private String name;

    @NotNull(message = "菜品价格不能为空")
    private BigDecimal price;

    @NotBlank(message = "种类不能为空")
    private String category;

    @NotBlank(message = "描述")
    private String description;

    private String image;

    @NotNull(message = "状态不能为空")
    private Status status;
}
