// package com.yutu.interceptor;
//
// import com.yutu.utils.UserHolderUtil;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.StringRedisTemplate;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.util.concurrent.TimeUnit;
//
// import static com.yutu.constants.RedisConstants.LOGIN_USER_KEY;
//
// @Component
// @Slf4j
// public class LoginInterceptor implements HandlerInterceptor {
//
//     @Autowired
//     private StringRedisTemplate srt;
//
//     @Override
//     public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         // 判断是否存在用户
//         if (UserHolderUtil.getUser() == null) {
//             response.setStatus(401);
//             return false;
//         }
//
//         // 发过来请求了，将Redis的key过期时间刷新
//         srt.expire(LOGIN_USER_KEY + UserHolderUtil.getUser().getId(), 10, TimeUnit.DAYS);
//         // 放行
//         return true;
//     }
// }
