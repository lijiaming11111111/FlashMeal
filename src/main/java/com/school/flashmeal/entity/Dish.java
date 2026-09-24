package com.school.flashmeal.entity;

import com.school.flashmeal.enums.dish.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Dish {
    private Integer id;

    private String name;

    private BigDecimal price;

    private Long stock;

    private String image;

    private String category;

    private String description;

    private Status status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
