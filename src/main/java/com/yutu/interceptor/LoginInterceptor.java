package com.yutu.interceptor;

import com.yutu.utils.UserHolderUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否存在用户
        if (UserHolderUtil.getUser() == null) {
            response.setStatus(401);
            return false;
        }
        // 放行
        return true;
    }
}
