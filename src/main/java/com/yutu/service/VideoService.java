package com.yutu.service;

import com.yutu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lyj
* @description 针对表【video】的数据库操作Service
* @createDate 2024-03-11 13:38:35
*/
public interface VideoService extends IService<Video> {

    List<Video> queryList ();
}
