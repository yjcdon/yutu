package com.yutu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutu.entity.Video;
import com.yutu.service.VideoService;
import com.yutu.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author lyj
* @description 针对表【video】的数据库操作Service实现
* @createDate 2024-03-11 13:38:35
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




