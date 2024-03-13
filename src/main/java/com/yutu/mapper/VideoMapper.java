package com.yutu.mapper;

import com.yutu.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author lyj
* @description 针对表【video】的数据库操作Mapper
* @createDate 2024-03-11 13:38:35
* @Entity entity.Video
*/
public interface VideoMapper extends BaseMapper<Video> {

    List<Video> queryList (Long userId);

    int deleteByIdAndUserId (Long id, Long userId);
}




