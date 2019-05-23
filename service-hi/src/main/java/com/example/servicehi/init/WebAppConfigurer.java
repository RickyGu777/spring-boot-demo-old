//package com.example.servicehi.init;
//
//import com.example.servicehi.handler.IPFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebAppConfigurer implements WebMvcConfigurer {
//
//    @Bean
//    IPFilter ipFilter(){
//        return new IPFilter();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 可添加多个
//        registry.addInterceptor(ipFilter()).addPathPatterns("/**");
//    }
//}