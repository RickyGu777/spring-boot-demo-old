package com.example.servicehi.init;

import com.example.servicehi.common.SendMail;
import com.example.servicehi.entity.Auth;
import com.example.servicehi.entity.IpAddress;
import com.example.servicehi.service.AuthService;
import com.example.servicehi.service.IpAddressService;
import com.example.servicehi.util.IPAddressUtil;
import com.example.servicehi.util.MacAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.*;

@Slf4j
public class Init implements InitializingBean {
    @Autowired
    private IpAddressService<IpAddress> ipAddressService;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private AuthService<Auth> authService;

    @Value("${delete_old_auth}")
    private boolean delete_old_auth;

    @Value("${searchIPWebSite}")
    private String searchIPWebSite;

    @Override
    public void afterPropertiesSet()  {
        log.info("start init");
        log.info("start init");
        log.info("start init");
        log.info("start init");
        log.info("start init");
        log.info("start init");
//        sendIP();
//        initAuth();
    }

    private void sendIP() throws IOException {
        String v4IP = IPAddressUtil.getInstance(searchIPWebSite).getV4IP();
        String localMac = MacAddressUtil.getLocalMac();
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIp(v4IP);
        ipAddress.setCreateDate(new Date());
        ipAddress.setMac(localMac);
        ipAddressService.deleteByMac(ipAddress);
        ipAddressService.insert(ipAddress);
        log.info("to send");
        sendMail.send("[项目初始化IP地址查询]", "项目初始化外网IP为:" + v4IP + ";MAC地址为:" + localMac + ";");
    }

    private void initAuth() {
        List<Auth> authList = authService.selectAll();

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<Auth> auths = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Auth auth = new Auth();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                auth.setUrl(url);
            }
            auth.setClassName(method.getMethod().getDeclaringClass().getName()); // 类名
            auth.setMethod(method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                auth.setType(requestMethod.toString());
            }
            auth.setClassGroup(method.getBean().toString());
            auth.setAuthType("1");
            auths.add(auth);
        }

        List<Auth> tempAuthList = new ArrayList<>();

        if (delete_old_auth) {
            for (Auth auth : authList) { // 循环旧权限列表
                boolean contains = auths.contains(auth); // 将旧权限和获取的最新权限比较，旧权限不存在则删除
                if (contains) { // 为true表示旧权限存在于新权限中
                    tempAuthList.add(auth); // 将最新获取的权限中重复的权限保存到temp列表中
                    continue;
                } else { // 为false表示旧权限不存在于新权限中，删除旧权限
                    authService.delete(auth);
                }
            }
            auths.removeAll(tempAuthList); // 移除重复的权限
        }

        // 循环处理过的最新权限列表，新增权限
        for (Auth auth : auths) {
            authService.insert(auth);
        }
    }
}