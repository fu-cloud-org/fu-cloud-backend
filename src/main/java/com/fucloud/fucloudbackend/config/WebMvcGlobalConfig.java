package com.fucloud.fucloudbackend.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcGlobalConfig implements WebMvcConfigurer {

    /**
     * 跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //设置放行哪些原始域
                .allowedOriginPatterns("*")
                //放行哪些请求方式
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                //.allowedMethods("*") //或者放行全部
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //暴露哪些原始请求头部信息
                .exposedHeaders("*");
//                .allowedOrigins("http://120.76.202.109");
    }

    // static resources path

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        String PROJECT_PATH = System.getProperty("user.dir");
//
//        // cover
//        registry.addResourceHandler("/img/cover/**")
//                .addResourceLocations("file:" + PROJECT_PATH + "/src/main/resources/static/img/cover/");
//
//        // avatar
//        registry.addResourceHandler("/img/avatar/**")
//                .addResourceLocations("file:" + PROJECT_PATH + "/src/main/resources/static/img/avatar/");
//    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String path = new ApplicationHome(getClass()).getSource().getParentFile().toString() + "/static/";
//        registry.addResourceHandler("/static/**").addResourceLocations("file:" + path + "/img/cover/");
//        registry.addResourceHandler("/static/**").addResourceLocations("file:" + path + "/img/avatar/");
//    }
}
