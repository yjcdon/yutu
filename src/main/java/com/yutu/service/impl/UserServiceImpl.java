package com.yutu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutu.entity.User;
import com.yutu.mapper.UserMapper;
import com.yutu.service.UserService;
import com.yutu.utils.UserHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author lyj
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-03-11 13:38:35
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryOne () {
        Long userId = UserHolderUtil.getUser().getId();
        return userMapper.queryOne(userId);
    }

    @Override
    public boolean signup (String username, String password) {
        if (username.length() <= 20 && password.length() <= 20) {
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            // 设置默认头像
            user.setAvatarUrl("https://d-ssl.dtstatic.com/uploads/blog/202207/28/20220728161450_2a0c9.thumb.300_0.jpg_webp");
            user.setCreateTime(LocalDateTime.now());

            int success = userMapper.insert(user);
            return success > 0;
        }
        return false;
    }

    @Override
    public boolean login (String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username).eq(User::getPassword, password);
        User user = userMapper.selectOne(queryWrapper);
        return user != null;
    }
}




