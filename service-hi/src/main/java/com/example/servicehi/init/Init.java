package com.example.servicehi.init;

import com.alibaba.fastjson.JSON;
import com.example.servicehi.common.Config;
import com.example.servicehi.common.SendMail;
import com.example.servicehi.entity.Auth;
import com.example.servicehi.entity.DbConfig;
import com.example.servicehi.entity.IpAddress;
import com.example.servicehi.service.AuthService;
import com.example.servicehi.service.DbConfigService;
import com.example.servicehi.service.IpAddressService;
import com.example.servicehi.util.*;
import com.example.servicehi.util.Baidu.BaiduTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Init implements InitializingBean {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IpAddressService<IpAddress> ipAddressService;

    @Autowired
    private DbConfigService<DbConfig> dbConfigService;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private AuthService<Auth> authService;

    @Autowired
    private Config config;

    @Value("${delete_old_auth}")
    private boolean delete_old_auth;

    @Value("${searchIPWebSite}")
    private String searchIPWebSite;

    @Override
    public void afterPropertiesSet() throws IOException {
//        List<File> fileSort = getFileSort("D:\\images");
//        for (File file : fileSort) {
//            System.out.println(file.getName());
//        }
//        sendIP();
//        initAuth();
        selectAllConfig();
        config.setFilePath(SystemUtils.IS_OS_LINUX ? config.getLinux() : config.getWindows());
//        createAccessToken();
//        if (SystemUtils.IS_OS_WINDOWS) {
//            readQQFilePath();
//            scannerFile();
//        }
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
//        sendMail.send("[项目初始化IP地址查询]", "项目初始化外网IP为:" + v4IP + ";MAC地址为:" + localMac + ";");
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

    /**
     * 查询所有的配置项，放入缓存，配置项code做为key
     */
    private void selectAllConfig() {
        List<DbConfig> dbConfigs = dbConfigService.selectAllConfig();
        for (DbConfig dbConfig : dbConfigs) {
            redisTemplate.opsForValue().set(dbConfig.getCode(), JSON.toJSONString(dbConfig));
        }
    }

    private void createAccessToken() {
        if (redisTemplate.opsForValue().get("baiduAccessToken") == null) {
            redisTemplate.opsForValue().set("baiduAccessToken", BaiduTool.getAuth());
            redisTemplate.expire("baiduAccessToken", 7200, TimeUnit.SECONDS);
        } else {
            log.info("baiduAccessToken:[{}]", redisTemplate.opsForValue().get("baiduAccessToken").toString());
        }
    }

    /**
     * 获取目录下所有文件(按时间排序)
     *
     * @param path
     * @return
     */
    public List<File> getFileSort(String path) {

        List<File> list = getFiles(path, new ArrayList());

        if (list != null && list.size() > 0) {
            Collections.sort(list, (file, newFile) -> {
                if (file.lastModified() < newFile.lastModified()) {
                    return 1;
                } else if (file.lastModified() == newFile.lastModified()) {
                    return 0;
                } else {
                    return -1;
                }
            });
        }
        return list;
    }

    /**
     * 获取目录下所有文件
     *
     * @param realpath
     * @param files
     * @return
     */
    public List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    if (file.getName().indexOf(".jpg") != -1) {
                        files.add(file);
                    }
                }
            }
        }
        return files;
    }

    /**
     * 读取指定目录下的文档内容，并为配置设置值
     */
    private void readQQFilePath() {
        File file = new File(config.getReadQQFilePath());
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
            String s;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(result.toString());
        config.setQQFilePath(result.toString().replace("\r\n", ""));
        log.info(config.getFilePath());
    }

    /**
     * 扫描文件并上传
     *
     * @throws IOException
     */
    public void scannerFile() throws IOException {
        List<File> backFileList = new ArrayList<>();
        // 先扫描本地的QQ图片文件夹，扫描所有.jpg格式结尾的文件，并按时间倒序排序文件
        List<File> fileSort = getFileSort(config.getQQFilePath());
        // 然后上传识别
        int i = 0;
        for (File file : fileSort) {
            if (i == 0) {
                // 读取图片数据，用长宽作为参数过滤一些表情图片
                Image src = Toolkit.getDefaultToolkit().getImage(file.getPath());
                BufferedImage image = toBufferedImage(src);
                int width = image.getWidth();
                int height = image.getHeight();

                if (width > 500 && height > 900) {
                    ResponseUtil responseUtil = SaveAndPostImg.uploadFileWithHttpMime(config.getQQFilePath() + File.separator + file.getName());
                    // 根据返回值判断是否识别成功
                    if (responseUtil.getCode() != 0) {
                        // 如果未识别成功的，先将未成功的文件路径放入一个List存放，循环结束后，将这些图片复制到统一文件夹
                        backFileList.add(file);
                    }
                }
                i++;
            }
        }
        Calendar instance = Calendar.getInstance();

        // 复制图片文件
        for (File file : backFileList) {
            String backUpFilePath = config.getBackUpFilePath() + instance.get(Calendar.YEAR) + "-" + (instance.get(Calendar.MONTH) + 1) + "-" + instance.get(Calendar.DATE) + File.separator + file.getName();
            File outPutFile = new File(backUpFilePath);
            FileUtil.checkParentFile(outPutFile);
            copyFile(file, outPutFile);
        }
    }

    /**
     * 读取图片
     *
     * @param image
     * @return
     */
    private static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {
        }
        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        Graphics g = bimage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }
}