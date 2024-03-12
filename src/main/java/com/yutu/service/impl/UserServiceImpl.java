package com.yutu.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yutu.constants.RedisConstants;
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
    public String login (String userName, String password) {
        User user = userMapper.queryOneByUnamePsw(userName, password);
        if (user != null) {
            UserHolderUtil.saveUser(user);

            // 把用户信息保存到Redis中,返回token，让前端保存到请求头中
            String tokenKey = LOGIN_USER_KEY + user.getId();
            srt.opsForValue().set(tokenKey, JSONUtil.toJsonStr(user));
            srt.expire(tokenKey, 10, TimeUnit.DAYS);

            return tokenKey;
        }
        return "";
    }

    @Override
    public boolean updateAvatar () {
        // 从Redis中取出filepath
        User user = UserHolderUtil.getUser();
        Long userId = user.getId();
        String key = RedisConstants.UPLOAD_AVATAR_KEY + userId;
        String filePath = srt.opsForValue().get(key);
        if (filePath.isEmpty()) {
            return false;
        }

        // 更新数据库
        user.setAvatarUrl(filePath);
        userMapper.updateById(user);

        // 删除Redis中的缓存
        srt.delete(key);

        return true;
    }
}




