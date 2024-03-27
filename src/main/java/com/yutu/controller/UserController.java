package com.yutu.controller;

import com.yutu.entity.User;
import com.yutu.entity.Video;
import com.yutu.result.Result;
import com.yutu.service.UserService;
import com.yutu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/11 18:04:03
     * @Params:
     * @Return:
     * @Description: 用户注册
     */
    @PostMapping("/signup")
    public Result<String> signup (@RequestBody User user) {
        boolean success = userService.signup(user);
        return Result.success(success ? "注册成功！" : "注册失败！");
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/11 18:27:49
     * @Params: 用户名、密码
     * @Return:
     * @Description: 用户登录接口
     */
    @PostMapping("/login")
    public Result<String> login (String userName, String password) {
        // 返回的这个玩意，前端保存到请求头中：login:user:11
        String tokenKey = userService.login(userName, password);
        return Result.success(tokenKey);
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/12 10:37:23
     * @Params:
     * @Return:
     * @Description: 用户退出登录
     */
    @PostMapping("/logout")
    public Result<String> logout () {
        userService.logout();
        return Result.success("退出登录！");
    }


    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/11 17:26:22
     * @Params:
     * @Return:
     * @Description: 用户进入主页，展示个人信息和已发布的视频
     */
    @GetMapping("/me")
    public Result<User> me () {
        return Result.success(userService.queryOne());
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/11 17:59:42
     * @Params:
     * @Return:
     * @Description: 用户点击某个按钮，进入页面查看自己发布的所有视频
     */
    @GetMapping("/me/videos")
    public Result<List<Video>> myVideos () {
        return Result.success(videoService.queryList());
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/13 14:43:55
     * @Params: 该用户的实体类
     * @Return:
     * @Description: 更新用户信息
     */
    @PostMapping("/me")
    public Result<String> updateUser (@RequestBody User user) {
        boolean b = userService.updateUser(user);
        return b ? Result.success("更新成功！") : Result.error("更新失败！");
    }

}
