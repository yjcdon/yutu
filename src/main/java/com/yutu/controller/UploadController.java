package com.yutu.controller;

import cn.hutool.core.lang.UUID;
import com.aliyun.oss.OSSClient;
import com.yutu.result.Result;
import com.yutu.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/11 16:24:06
     * @Params: 用户上传的文件
     * @Return:
     * @Description: 让用户上传文件的接口
     */

    @PostMapping
    public Result<String> upload (MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));// 截取原始文件名后缀
            // 判断文件是否存在，存在就把原文件删除
            String objectName = UUID.randomUUID().toString(true) + extension;// 构建新文件名称
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：", e);
        }
        return Result.error("上传文件失败！");
    }
}