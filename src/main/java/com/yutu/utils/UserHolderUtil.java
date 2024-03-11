package com.yutu.utils;

import com.yutu.entity.User;

public class UserHolderUtil {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser (User user) {
        tl.set(user);
    }

    public static User getUser () {
        return tl.get();
    }

    public static void removeUser () {
        tl.remove();
    }
}
