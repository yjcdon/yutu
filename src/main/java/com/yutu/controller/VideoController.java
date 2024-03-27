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
     * @Date: 2024/3/13 13:26:04
     * @Params:
     * @Return:
     * @Description: 用户新增视频
     */
    @PostMapping
    public Result<String> save (@RequestBody Video video) {
        boolean b = videoService.saveVideo(video);
        return b ? Result.success("新增成功！") : Result.error("新增失败！");
    }

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
    @GetMapping("/all")
    public Result<List<Video>> list () {
        return Result.success(videoService.list());
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/12 22:12:19
     * @Params:
     * @Return:
     * @Description: 用户更新视频信息
     */
    @PutMapping
    public Result<String> updateVideo (@RequestBody Video video) {
        boolean b = videoService.updateVideo(video);
        return b ? Result.success("更新成功！") : Result.error("更新失败！");
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/12 22:29:41
     * @Params: 视频id
     * @Return:
     * @Description: 用户删除自己发布的视频
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteVideo (@PathVariable Long id) {
        boolean b = videoService.removeVideo(id);
        return b ? Result.success("删除成功！") : Result.error("删除失败！");
    }
}
