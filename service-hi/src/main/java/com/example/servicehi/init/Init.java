package com.example.servicehi.init;

import com.example.servicehi.common.SendMail;
import com.example.servicehi.entity.IpAddress;
import com.example.servicehi.service.IpAddressService;
import com.example.servicehi.util.IPAddressUtil;
import com.example.servicehi.util.MacAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class Init implements InitializingBean {
    @Autowired
    private IpAddressService<IpAddress> ipAddressService;

    @Autowired
    private SendMail sendMail;

    @Override
    public void afterPropertiesSet() throws Exception {
        sendIP();
    }

    private void sendIP() throws IOException {
//        String v4IP = IPAddressUtil.getV4IP();
//        String localMac = MacAddressUtil.getLocalMac();
//        IpAddress ipAddress = new IpAddress();
//        ipAddress.setIp(v4IP);
//        ipAddress.setCreateDate(new Date());
//        ipAddress.setMac(localMac);
//        ipAddressService.deleteByMac(ipAddress);
//        ipAddressService.insert(ipAddress);
//
//        sendMail.send("项目初始化IP地址查询", "项目初始化外网IP为:" + v4IP + ";MAC地址为:" + localMac + ";");
    }
}