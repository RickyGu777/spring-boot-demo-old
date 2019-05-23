//package com.example.servicehi.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Calendar;
//
//@Slf4j
//@Component
//public class IPFilter extends HandlerInterceptorAdapter {
//    @Autowired
//    private RedisTemplate<String, Integer> redisTemplate;
//
//    public IPFilter() {
//        super();
//        log.info("IPFilter");
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (StringUtils.isEmpty(request.getHeader("REQUEST-TYPE"))) {
//            String realIP = request.getHeader("X-Real-IP");
//
//            Calendar instance = Calendar.getInstance();
//            Integer integer = redisTemplate.opsForValue().get(realIP + instance.get(Calendar.YEAR) + instance.get(Calendar.MONTH) + instance.get(Calendar.DATE));
//            if (integer == null) {
//                redisTemplate.opsForValue().set("IP-" + realIP + "-" + instance.get(Calendar.YEAR) + instance.get(Calendar.MONTH) + instance.get(Calendar.DATE), 1);
//            } else {
//                redisTemplate.opsForValue().set("IP-" + realIP + "-" + instance.get(Calendar.YEAR) + instance.get(Calendar.MONTH) + instance.get(Calendar.DATE), integer + 1);
//            }
//        }
//        return super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("postHandle");
//        super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        super.afterCompletion(request, response, handler, ex);
//    }
//
//    @Override
//    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        super.afterConcurrentHandlingStarted(request, response, handler);
//    }
//}