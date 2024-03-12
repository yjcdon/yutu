package com.yutu.controller;

import com.yutu.entity.Video;
import com.yutu.result.Result;
import com.yutu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/12 10:40:26
     * @Params: 视频id
     * @Return: 视频id对应的视频信息
     * @Description: 查看当前视频
     */
    @GetMapping("/{id}")
    public Result<Video> queryOne (@PathVariable Long id) {
        return Result.success(videoService.queryOne(id));
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/12 10:45:26
     * @Params:
     * @Return:
     * @Description: 视频点赞功能
     */
    @PostMapping("/{id}/like")
    public Result<String> like (@PathVariable Long id) {
        videoService.like(id);
        return Result.success();
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/12 14:07:10
     * @Params:
     * @Return:
     * @Description: 查询所有视频信息
     */
    @GetMapping
    public Result<List<Video>> list () {
        return Result.success(videoService.list());
    }
}
