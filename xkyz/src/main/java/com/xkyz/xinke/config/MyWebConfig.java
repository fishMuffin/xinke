//package com.xkyz.xinke.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//public class MyWebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //获取文件的真实路径 work_project代表项目工程名 需要更改
//
//        String os = System.getProperty("os.name");
//        String path2 = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\avatar\\";
//        if (os.toLowerCase().startsWith("win")) {
//            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploads\\";
//            registry.addResourceHandler("/uploads/**").
//                    addResourceLocations("file:" + path);
//            registry.addResourceHandler("/images/avatar/**")
//                    .addResourceLocations("file:" + path2);
//        } else {//linux和mac系统 可以根据逻辑再做处理
//            ;
//
//            registry.addResourceHandler("/uploads/**").
//                    addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
//                            + "uploads" + System.getProperty("file.separator"));
//
//            registry.addResourceHandler("/images/avatar/**").
//                    addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "images"
//                            + System.getProperty("file.separator") + "avatar" + System.getProperty("file.separator"));
//
//        }
//
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//    }
//}