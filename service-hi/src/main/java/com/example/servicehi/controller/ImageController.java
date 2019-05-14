package com.example.servicehi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.servicehi.common.Config;
import com.example.servicehi.entity.ShareTicketImg;
import com.example.servicehi.entity.UploadImg;
import com.example.servicehi.service.ShareTicketImgService;
import com.example.servicehi.service.UploadImgService;
import com.example.servicehi.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RequestMapping(value = "/Image")
@RestController
@AllArgsConstructor
@Slf4j
public class ImageController {
    private final UploadImgService<UploadImg> uploadImgService;

    private final ShareTicketImgService<ShareTicketImg> shareTicketImgService;

    private final Config config;

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public Map uploadImage(@RequestParam("img") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUIDUtil.createUUID() + "." + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        String compress = SaveAndPostImg.compress(multipartFile, SystemUtils.IS_OS_LINUX ? config.getLinux() : config.getWindows(), fileName);
        Map map = JSON.parseObject(compress, Map.class);

        HashMap<String, Object> hashMap = new HashMap<>();
        UploadImg uploadImg = new UploadImg();
        uploadImg.setOriginalName(originalFilename);
        uploadImg.setIsDel("0");
        uploadImg.setRandomName(fileName);
        if ("success".equals(map.get("code").toString())) {
            String responseUrl = ((Map) map.get("data")).get("url").toString();
            uploadImg.setResponseUrl(responseUrl);
            hashMap.put("img", ((Map) map.get("data")).get("url"));
            hashMap.put("code", 0);
        } else if ("error".equals(map.get("code").toString())) {
            hashMap.put("msg", map.get("msg"));
            hashMap.put("code", 1);
        }
        uploadImg.setTitle(uploadImg.getRandomName());
        if (SystemUtils.IS_OS_LINUX) {
            uploadImg.setImagePath('.' + config.getLinuxPath() + uploadImg.getRandomName());
        }
        uploadImgService.insert(uploadImg);

        if (SystemUtils.IS_OS_WINDOWS) {
            SaveAndPostImg.sendImage(SystemUtils.IS_OS_LINUX ? config.getLinux() : config.getWindows() + File.separator + uploadImg.getRandomName());
        }
        return hashMap;
    }

    /**
     * 修改上传文件的标题
     *
     * @param uploadImg
     * @return
     */
    @PostMapping(value = "/updateTitle")
    public ResponseUtil updateTitle(@RequestBody UploadImg uploadImg) {
        uploadImgService.updateTitle(uploadImg);
        return new ResponseUtil();
    }

    /**
     * 识别微信二维码
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/QRCode")
    public ResponseUtil QRCode(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUIDUtil.createUUID() + "." + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        String compress = SaveAndPostImg.compress(multipartFile, SystemUtils.IS_OS_LINUX ? config.getLinux() : config.getWindows(), fileName);
        Map map = JSON.parseObject(compress, Map.class);

        HashMap<String, Object> hashMap = new HashMap<>();
        UploadImg uploadImg = new UploadImg();
        uploadImg.setOriginalName(originalFilename);
        uploadImg.setIsDel("0");
        uploadImg.setRandomName(fileName);
        if ("success".equals(map.get("code").toString())) {
            String responseUrl = ((Map) map.get("data")).get("url").toString();
            uploadImg.setResponseUrl(responseUrl);
            hashMap.put("img", ((Map) map.get("data")).get("url"));
            hashMap.put("code", 0);
        } else if ("error".equals(map.get("code").toString())) {
            hashMap.put("msg", map.get("msg"));
            hashMap.put("code", 1);
        }
        uploadImg.setTitle(uploadImg.getRandomName());
        if (SystemUtils.IS_OS_LINUX) {
            uploadImg.setImagePath('.' + config.getLinuxPath() + uploadImg.getRandomName());
        }
        uploadImgService.insert(uploadImg);

        String filePath = SystemUtils.IS_OS_LINUX ? config.getLinux() : config.getWindows() + File.separator + fileName;
        log.info("fileName:[{}]", fileName);
        log.info("filePath:[{}]", filePath);
        String qrCode = ZixingCodeUtil.decodeQRCodeImage(filePath, null).replace("\uD83D\uDCF1", "");
        ShareTicketImg shareTicketImg = new ShareTicketImg();
        shareTicketImg.setUploadImgUUID(uploadImg.getUuid());
        shareTicketImg.setQRCode(qrCode);
        shareTicketImgService.insert(shareTicketImg);

        if (SystemUtils.IS_OS_WINDOWS) {
            SaveAndPostImg.sendImage(SystemUtils.IS_OS_LINUX ? config.getLinux() : config.getWindows() + File.separator + uploadImg.getRandomName());
        }
        return new ResponseUtil(qrCode);
    }

    @PostMapping(value = "/getImageWall")
    public ResponseUtil getImageWall(@RequestBody UploadImg uploadImg) {
        return new ResponseUtil(uploadImgService.selectPictureWall(uploadImg).getList());
    }

    @PostMapping(value = "/backUpImage")
    public ResponseUtil backUpImage(@RequestParam("img") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        log.info("originalFilename=" + originalFilename);
        UploadImg uploadImg = new UploadImg();
        uploadImg.setRandomName(originalFilename);
        uploadImg = uploadImgService.selectImageInfoByRandomName(uploadImg);
        String randomName = uploadImg.getRandomName();
        File dest = new File(config.getLinux() + File.separator + randomName);
        multipartFile.transferTo(dest);
        return new ResponseUtil();
    }

    @ResponseBody
    @GetMapping("/download")
    public void downloadFiles(HttpServletRequest request, HttpServletResponse response) {
        /*
         *  test
         * */
        List<String> list = new ArrayList<>();
        list.add("D:\\images\\1ad2968effc444609ad8a32a36c4e081.jpg");
        list.add("D:\\images\\6a0153cc5e5b472f92e65a16267202a1.jpg");
        list.add("D:\\images\\59de506db0344dad90160c4759d97331.jpg");
        list.add("D:\\images\\418a1f7b1a1d472ab91f41fc99b02d24.jpg");
        list.add("D:\\images\\806f06a93a9342cfa9f42a85478270a0.jpg");
        list.add("D:\\images\\83008dc1c5914e8ba323c5fb2c356209.jpg");
        list.add("D:\\images\\afaf918e3e874b19bdd8735afa38538c.jpg");
        list.add("D:\\images\\cf12a7e1e46e48989dbcda1dd638d925.jpg");
        list.add("D:\\images\\e7d792fd213a4063a75b8887d3122438.jpg");

        //响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream;charset=utf-8");// 设置response内容的类型

        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        String downloadName = "test.zip";
        String agent = request.getHeader("USER-AGENT");
        ZipOutputStream zipos = null;
        //循环将文件写入压缩流
        DataOutputStream os = null;
        try {
            if (agent.contains("MSIE") || agent.contains("Trident")) {
                downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
            } else {
                downloadName = new String(downloadName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + downloadName + "\"");

            //设置压缩流：直接写入response，实现边压缩边下载
            zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED); //设置压缩方法

            for (int i = 0; i < list.size(); i++) {

                InputStream is = null;
                try {
                    File file = new File(list.get(i));
                    if (file.exists()) {
                        //添加ZipEntry，并ZipEntry中写入文件流
                        //这里，加上i是防止要下载的文件有重名的导致下载失败
                        zipos.putNextEntry(new ZipEntry(i + "_" + file.getName()));
                        os = new DataOutputStream(zipos);
                        is = new FileInputStream(file);
                        byte[] b = new byte[1024];
                        int length;
                        while ((length = is.read(b)) != -1) {
                            os.write(b, 0, length);
                        }
                    }
                } finally {
                    if (null != is) {
                        is.close();
                    }
                    zipos.closeEntry();
                }

            }
            if (null != os) {
                os.flush();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != os) {
                    os.close();
                }
                if (null != zipos) {
                    zipos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}