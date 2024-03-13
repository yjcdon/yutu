package com.yutu;

import cn.hutool.core.io.file.FileNameUtil;
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
        System.out.println(FileNameUtil.getName("http://yutu-408.oss-cn-guangzhou.aliyuncs.com/avatar/aa55528879424f6d83267746dcf303fd.png"));

    }

}
