package com.school.flashmeal.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class ListUsersDTO {
    @NotNull(message = "页码不能为空")
    private Integer pageNum;

    @NotNull(message = "一页的数量不能为空")
    private Integer pageSize;
}
