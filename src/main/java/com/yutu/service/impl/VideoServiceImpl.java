package com.yutu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutu.constants.RedisConstants;
import com.yutu.entity.Video;
import com.yutu.mapper.VideoMapper;
import com.yutu.service.VideoService;
import com.yutu.utils.UserHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyj
 * @description 针对表【video】的数据库操作Service实现
 * @createDate 2024-03-11 13:38:35
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
        implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private StringRedisTemplate srt;

    @Override
    public List<Video> queryList () {
        Long userId = UserHolderUtil.getUser().getId();
        return videoMapper.queryList(userId);
    }

    @Override
    public Video queryOne (Long id) {
        return videoMapper.selectById(id);

    }

    @Override
    public void like (Long id) {
        Video video = videoMapper.selectById(id);
        if (video != null) {
            Long userId = UserHolderUtil.getUser().getId();
            String key = RedisConstants.VIDEO_LIKE_USER_IDS + video.getId();
            videoIsLiked(video);

            // Redis的set中是否存在这个id，存在表示对该视频点过赞了
            Boolean isExist = srt.opsForSet().isMember(key, userId);
            if (Boolean.TRUE.equals(isExist)) {
                srt.opsForSet().remove(key, userId);
                video.setLikeCount(video.getLikeCount() - 1);
            } else {
                // 不存在，则点赞数+1
                srt.opsForSet().add(key, userId.toString());
                video.setLikeCount(video.getLikeCount() + 1);
            }

            videoMapper.updateById(video);
        }
    }

    @Override
    public boolean updateCover (Long id) {
        String key = RedisConstants.UPLOAD_VIDEO_COVER_KEY + UserHolderUtil.getUser().getId();
        String filePath = srt.opsForValue().get(key);
        if (filePath.isEmpty()) {
            return false;
        }

        //     修改数据库
        Video video = videoMapper.selectById(id);
        video.setCoverUrl(filePath);
        videoMapper.updateById(video);

        srt.delete(key);
        return true;
    }

    @Override
    public boolean updateVideo (Long id) {
        String key = RedisConstants.UPLOAD_VIDEO_KEY + UserHolderUtil.getUser().getId();
        String filePath = srt.opsForValue().get(key);
        if (filePath.isEmpty()) {
            return false;
        }

        //     修改数据库
        Video video = videoMapper.selectById(id);
        video.setVideoUrl(filePath);
        videoMapper.updateById(video);

        srt.delete(key);
        return true;
    }

    /**
     * @Author: 梁雨佳
     * @Date: 2024/3/12 11:16:19
     * @Params:
     * @Return:
     * @Description: 对于这个用户，判断是否对该视频点赞过
     */
    private void videoIsLiked (Video video) {
        Long userId = UserHolderUtil.getUser().getId();
        String key = RedisConstants.VIDEO_LIKE_USER_IDS + video.getId();

        Boolean isExist = srt.opsForSet().isMember(key, userId);
        video.setIsLike(isExist != null);
    }
}




