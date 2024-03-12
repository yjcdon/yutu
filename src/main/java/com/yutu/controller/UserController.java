package com.yutu.controller;

import com.yutu.entity.User;
import com.yutu.entity.Video;
import com.yutu.result.Result;
import com.yutu.service.UserService;
import com.yutu.service.VideoService;
import com.yutu.utils.UserHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<String> signup (String userName, String password) {
        boolean success = userService.signup(userName, password);
        return Result.success(success ? "注册成功！" : "注册失败！");

    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/11 18:27:49
     * @Params:
     * @Return:
     * @Description: 用户登录接口
     */
    @PostMapping("/login")
    public Result<String> login (String userName, String password) {
        boolean success = userService.login(userName, password);
        return Result.success(success ? "登录成功！" : "登录失败！");
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
        UserHolderUtil.removeUser();
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

}
