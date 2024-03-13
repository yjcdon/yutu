package com.yutu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * @TableName video
 */
@TableName(value = "video")
@Data
public class Video implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    /**
     * 视频URL
     */
    private String videoUrl;

    /**
     * 视频封面URL
     */
    private String coverUrl;

    /**
     * 视频title
     */
    private String title;

    /**
     * 视频介绍文案
     */
    private String text;

    /**
     * 视频点赞数
     */
    private Integer likeCount;

    /**
     * 上传视频时间
     */
    private LocalDateTime createTime;

    /*
    是否点赞
     */
    @TableField(exist = false)
    private Boolean isLike;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}