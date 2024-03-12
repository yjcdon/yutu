package com.yutu.controller;

import cn.hutool.core.lang.UUID;
import com.yutu.constants.RedisConstants;
import com.yutu.result.Result;
import com.yutu.utils.AliOssUtil;
import com.yutu.utils.UserHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private StringRedisTemplate srt;

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/11 16:24:06
     * @Params: 用户上传的文件
     * @Return:
     * @Description: 让用户上传文件的接口
     */

    @PostMapping("/avatar")
    public Result<String> uploadAvatar (MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));// 截取原始文件名后缀
            String objectName = "avatar/" + UUID.randomUUID().toString(true) + extension;// 构建新文件名称
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);

            String key = RedisConstants.UPLOAD_AVATAR_KEY + UserHolderUtil.getUser().getId();
            srt.opsForValue().set(key, filePath);

            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：", e);
        }
        return Result.error("上传文件失败！");
    }

    @PostMapping("/cover")
    public Result<String> uploadCover (MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));// 截取原始文件名后缀
            String objectName = "cover/" + UUID.randomUUID().toString(true) + extension;// 构建新文件名称
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);

            String key = RedisConstants.UPLOAD_VIDEO_COVER_KEY + UserHolderUtil.getUser().getId();
            srt.opsForValue().set(key, filePath);

            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：", e);
        }
        return Result.error("上传文件失败！");
    }

    @PostMapping("/video")
    public Result<String> uploadVideo (MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));// 截取原始文件名后缀
            String objectName = "video/" + UUID.randomUUID().toString(true) + extension;// 构建新文件名称
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);

            String key = RedisConstants.UPLOAD_VIDEO_KEY + UserHolderUtil.getUser().getId();
            srt.opsForValue().set(key, filePath);

            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：", e);
        }
        return Result.error("上传文件失败！");
    }
}