package com.yutu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yutu.mapper")
@SpringBootApplication
public class YutuApplication {

    public static void main (String[] args) {
        SpringApplication.run(YutuApplication.class, args);
    }

}
