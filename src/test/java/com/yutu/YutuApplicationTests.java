package com.yutu;

import com.yutu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YutuApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void test () {
        System.out.println(userService.list());
    }

}
