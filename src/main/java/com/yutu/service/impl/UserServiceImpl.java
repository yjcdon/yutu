package com.yutu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutu.entity.User;
import com.yutu.service.UserService;
import com.yutu.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author lyj
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-03-11 13:38:35
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




