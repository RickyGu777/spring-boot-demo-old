package com.example.servicehi.scheduled;

import com.example.servicehi.common.SendMail;
import com.example.servicehi.entity.IpAddress;
import com.example.servicehi.service.IpAddressService;
import com.util.CmdUtil;
import com.util.IPAddressUtil;
import com.util.MacAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

@Slf4j
@Component
public class ScheduledService {
    @Autowired
    private IpAddressService ipAddressService;

    @Autowired
    private SendMail sendMail;

    /**
     * 每半个小时查询一次外网IP,如果外网IP改变，则发送IP地址至QQ邮箱
     *
     * @throws IOException
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void getIP() throws IOException, MessagingException {
        log.info("=====>>>>>使用cron  {}", System.currentTimeMillis());
        String v4IP = IPAddressUtil.getV4IP();
        String localMac = MacAddressUtil.getLocalMac();

        IpAddress ipAddress = new IpAddress();
        ipAddress.setMac(localMac);

        ipAddress = ipAddressService.selectMac(ipAddress);

        if (!ipAddress.getIp().equals(v4IP)) {
            sendMail.send("IP地址变更", "变更MAC为:" + localMac + ";变更前IP为:" + ipAddress.getIp() + ";变更后IP为:" + v4IP);
        }
    }

    /**
     * 每5分钟更新一次CPU温度，超过65则报警并关闭系统，暂时只支持树莓派，还未在其他系统上测试命令
     *
     * @throws InterruptedException
     */
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void getTemp() throws InterruptedException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "1");
        Properties props = System.getProperties();
        String property = props.getProperty("os.name");
        if ("Linux".equals(property)) {
            Double exec = Double.parseDouble(CmdUtil.exec("/opt/vc/bin/vcgencmd measure_temp").replace("temp=", "").replace("'C", "").replace("\n", ""));
            if (exec > 65) {
                sendMail.send("CPU温度过高", "当前CPU温度为" + exec + "，执行关机处理。当前服务器时间" + new Date());
                try {
                    CmdUtil.exec("sudo shutdown now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    sendMail.send("CPU温度过高，执行关机处理失败", "当前CPU温度为" + exec + "；当前服务器时间" + new Date());
                }
            }
        }
    }
}
