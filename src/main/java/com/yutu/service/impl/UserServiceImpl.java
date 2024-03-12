package com.yutu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutu.entity.User;
import com.yutu.mapper.UserMapper;
import com.yutu.service.UserService;
import com.yutu.utils.UserHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.yutu.constants.RedisConstants.LOGIN_USER_KEY;

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

    @Autowired
    private StringRedisTemplate srt;

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
            user.setAvatarUrl("https://img95.699pic.com/photo/40250/0502.jpg_wh300.jpg");
            user.setCreateTime(LocalDateTime.now());

            int success = userMapper.insert(user);
            return success > 0;
        }
        return false;
    }

    @Override
    public boolean login (String userName, String password) {
        User user = userMapper.queryOneByUnamePsw(userName, password);
        if (user != null) {
            UserHolderUtil.saveUser(user);

            // 把用户信息保存到Redis中
            String key = LOGIN_USER_KEY + user.getId();
            srt.opsForValue().set(key, "1");
            srt.expire(key, 10, TimeUnit.DAYS);

            return true;
        }
        return false;
    }
}




