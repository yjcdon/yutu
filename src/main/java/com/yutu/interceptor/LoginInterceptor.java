package com.yutu.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yutu.entity.User;
import com.yutu.utils.UserHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.yutu.constants.RedisConstants.LOGIN_USER_KEY;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate srt;

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token"); // 请求头中的是：token  login:user:11
        if (StrUtil.isBlank(token)) {
            log.error("未登录1");
            response.setStatus(401);
            return false;
        }

        String userJSONStr = srt.opsForValue().get(token);
        if (StrUtil.isBlank(userJSONStr)) {
            log.error("未登录2");
            response.setStatus(401);
            return false;
        }

        // 将这玩意转为User，保存到ThreadLocal中
        User user = JSONUtil.toBean(userJSONStr, User.class);
        UserHolderUtil.saveUser(user);

        // 发过来请求了，将Redis的key过期时间刷新
        srt.expire(LOGIN_USER_KEY + UserHolderUtil.getUser().getId(), 10, TimeUnit.DAYS);
        // 放行
        return true;
    }
}
