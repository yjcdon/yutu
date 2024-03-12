// package com.yutu.config;
//
// import com.yutu.interceptor.LoginInterceptor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
// @Configuration
// public class WebMvcConfiguration implements WebMvcConfigurer {
//     @Autowired
//     private LoginInterceptor loginInterceptor;
//
//     // 未登录的用户只能看视频、登录和注册，其他请求都会被拦截
//     // 然后由前端显示要求登录界面
//     @Override
//     public void addInterceptors (InterceptorRegistry registry) {
//         registry.addInterceptor(loginInterceptor)
//                 .addPathPatterns("/**")
//                 .excludePathPatterns(
//                         "/user/login",
//                         "/user/signup",
//                         "/video/look/**" // 这个是看视频的路径，后面跟着的是随机的视频id
//                 );
//     }
// }
