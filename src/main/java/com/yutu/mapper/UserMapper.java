package com.yutu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yutu.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyj
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-03-11 13:38:35
 * @Entity entity.User
 */
public interface UserMapper extends BaseMapper<User> {

    User queryOne (Long id);

    User queryOneByUnamePsw (@Param("userName") String userName, @Param("password") String password);
}