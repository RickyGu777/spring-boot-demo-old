package com.example.servicehi.scheduled;

import com.example.servicehi.common.SendMail;
import com.example.servicehi.entity.IpAddress;
import com.example.servicehi.service.IpAddressService;
import com.util.CmdUtil;
import com.util.IPAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
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
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIp(v4IP);

        ipAddress = ipAddressService.selectIP(ipAddress);

        if (ipAddress == null) {
            sendMail.send("IP地址变更", "外网IP为:" + v4IP);
        }
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void getTemp() throws InterruptedException {
        Properties props=System.getProperties();
        String property = props.getProperty("os.name");
        if ("Linux".equals(property)) {
            Double exec = Double.parseDouble(CmdUtil.exec("/opt/vc/bin/vcgencmd measure_temp").replace("temp=", "").replace("'C", "").replace("\n", ""));
            if (exec > 65) {
                sendMail.send("CPU温度过高", "当前CPU温度为" + exec + "，执行关机处理。当前服务器时间" + new Date());
                CmdUtil.exec("sudo shutdown now");
            }
        }
    }
}
