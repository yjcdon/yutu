package com.yutu.service;

import com.yutu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lyj
* @description 针对表【user】的数据库操作Service
* @createDate 2024-03-11 13:38:35
*/
public interface UserService extends IService<User> {

    User queryOne ();

    boolean signup (String username, String password);

    String login (String username, String password);

    // boolean updateAvatar ();

    void logout ();

    boolean updateUser (User user);
}
