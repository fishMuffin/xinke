package com.xkyz.xinke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("com.xkyz.xinke.mapper")//扫描接口，注意要使用通用Mapper包的注解
@ComponentScan(basePackages = {"com.xkyz.xinke.controller","com.xkyz.xinke.service","com.xkyz.xinke.config"})
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

}
