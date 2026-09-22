package com.school.flashmeal.enums.dish;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    GROUNDING(1,"上架"),

    SOLD_OUT(2,"下架");

    @JsonValue
    @EnumValue
    private final Integer code;

    private final String desc;


}
