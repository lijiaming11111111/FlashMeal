package com.school.flashmeal.vo.dish;

import com.school.flashmeal.enums.dish.Status;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class PageDishVO {
    private Integer id;

    private String name;

    private BigDecimal price;

    private MultipartFile image;

    private String category;

    private String description;

    private Status status;
}
