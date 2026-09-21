package com.school.flashmeal.Interceptor;

import com.school.flashmeal.common.BusinessException;
import com.school.flashmeal.common.UnauthorizedException;
import com.school.flashmeal.context.BaseContext;
import com.school.flashmeal.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token ==null || token.isEmpty()){
            throw new UnauthorizedException("未登录");
        }
        try {
            Claims claims =JwtUtil.parseJwt(token);
            BaseContext.setCurrentUserId(claims.get("id", Integer.class));
        }catch (Exception e){
            throw new UnauthorizedException("登录过期");
        }
        return true;
    }
}
