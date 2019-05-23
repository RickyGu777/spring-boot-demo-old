//package com.example.servicehi.aop;
//
//
//import com.alibaba.fastjson.JSON;
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Field;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Aspect
//@Configuration//定义一个切面
//@Slf4j
//public class LogRecordAspect {
//    // 定义切点Pointcut
//    @Pointcut("execution(* com.example.servicehi.controller..*.*(..))")
//    public void excudeService() {
//    }
//
//    @Around("excudeService()")
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//
//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        String uri = request.getRequestURI();
//        String queryString = request.getQueryString();
//        Object[] args = pjp.getArgs();
//        String params = "";
//        //获取请求参数集合并进行遍历拼接
//        if (args.length > 0) {
//            if ("POST".equals(method)) {
//                Object object = args[0];
//                Map map = getKeyAndValue(object);
//                params = JSON.toJSONString(map);
//            } else if ("GET".equals(method)) {
//                params = queryString;
//            }
//        }
//
//        log.info("请求开始===url:[{}]", url);
//        log.info("请求开始===uri:[{}]", uri);
//        log.info("请求开始===method:[{}]", method);
//        log.info("请求开始===params:[{}]", params);
//        log.info("请求开始===times:[{}]", new Date());
//
//        // result的值就是被拦截方法的返回值
//        Object result = pjp.proceed();
//        Gson gson = new Gson();
//        log.info("请求结束===返回值:" + gson.toJson(result));
//        return result;
//    }
//
//    public static Map<String, Object> getKeyAndValue(Object obj) {
//        Map<String, Object> map = new HashMap<>();
//        if (obj == null) {
//            return map;
//        }
//        // 得到类对象
//        Class userCla = obj.getClass();
//        /* 得到类中的所有属性集合 */
//        Field[] fs = userCla.getDeclaredFields();
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            f.setAccessible(true); // 设置些属性是可以访问的
//            Object val;
//            try {
//                val = f.get(obj);
//                // 得到此属性的值
//                map.put(f.getName(), val);// 设置键值
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return map;
//    }
//}