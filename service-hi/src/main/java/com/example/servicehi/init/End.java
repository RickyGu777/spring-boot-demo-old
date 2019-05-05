package com.example.servicehi.init;

import com.example.servicehi.common.SendMail;
import com.example.servicehi.entity.IpAddress;
import com.example.servicehi.service.IpAddressService;
import com.example.servicehi.util.IPAddressUtil;
import com.example.servicehi.util.MacAddressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class End implements ExitCodeGenerator {
    @Value("${searchIPWebSite}")
    private String searchIPWebSite;

    @Autowired
    private IpAddressService<IpAddress> ipAddressService;

    @Autowired
    private SendMail sendMail;

    @Override
    public int getExitCode() {
        try {
            sendIP();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
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

        sendMail.send("项目结束时IP地址",
                "项目结束时外网IP为:" + v4IP +
                        ";MAC地址为:" + localMac +
                        ";结束时间:" + new Date());
    }
}