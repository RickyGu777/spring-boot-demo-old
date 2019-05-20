package com.example.servicehi.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.servicehi.common.CodeMsg;
import com.example.servicehi.common.Config;
import com.example.servicehi.dao.UploadImgDao;
import com.example.servicehi.entity.ShareTicketImg;
import com.example.servicehi.entity.UploadImg;
import com.example.servicehi.entity.dto.BaiduOCRDto;
import com.example.servicehi.handler.GlobalException;
import com.example.servicehi.service.ShareTicketImgService;
import com.example.servicehi.service.UploadImgService;
import com.example.servicehi.util.Baidu.BaiduTool;
import com.example.servicehi.util.HttpRequest;
import com.example.servicehi.util.SaveAndPostImg;
import com.example.servicehi.util.UUIDUtil;
import com.example.servicehi.util.ZixingCodeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UploadImgServiceImpl<T extends UploadImg> implements UploadImgService<T> {
    @Autowired
    private UploadImgDao<T> uploadImgDao;

    @Autowired
    private Config config;

    @Autowired
    private ShareTicketImgService<ShareTicketImg> shareTicketImgService;

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(t.getCreateDate());
        uploadImgDao.insert(t);
    }

    @Override
    public List<T> selectImageInfoByOriginalName(T t) {
        return uploadImgDao.selectImageInfoByOriginalName(t);
    }

    @Override
    public T selectImageInfoByRandomName(T t) {
        return uploadImgDao.selectImageInfoByRandomName(t);
    }

    @Override
    public void updateTitle(T t) {
        t.setModiDate(new Date());
        uploadImgDao.updateTitle(t);
    }

    @Override
    public PageInfo selectPictureWall(T t) {
        PageHelper.startPage(t.getPage(), t.getSize());
        return new PageInfo(uploadImgDao.selectPictureWall(t));
    }

    @Override
    public HashMap uploadImage(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUIDUtil.createUUID() + "." + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        String compress = SaveAndPostImg.compress(multipartFile, config.getFilePath(), fileName);
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
        try {
            insert((T) uploadImg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.IMAGE_CONTROLLER_UPLOAD_IMAGE_ERROR);
        }

        if (SystemUtils.IS_OS_WINDOWS) {
            SaveAndPostImg.sendImage(config.getFilePath() + uploadImg.getRandomName());
        }
        return hashMap;
    }

    @Override
    public String getQRCode(MultipartFile multipartFile) throws IOException {
        // 百度OCR识别
        BASE64Encoder encoder = new BASE64Encoder();
        String imgData = encoder.encode(multipartFile.getBytes()).replace("\r\n", "");
        imgData = URLEncoder.encode(imgData, "UTF-8");
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        String param = "?language_type=CHN_ENG&access_token=" + BaiduTool.getAuth() + "&image=" + imgData;
        BaiduOCRDto baiduOCRDto = JSON.parseObject(HttpRequest.baiduOCRPost(url, param), BaiduOCRDto.class);

        // 将上传的图片保存至图床，并保存数据到数据库
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUIDUtil.createUUID() + "." + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        String compress = SaveAndPostImg.compress(multipartFile, config.getFilePath(), fileName);
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
        insert((T) uploadImg);

        // 识别二维码
        String filePath = config.getFilePath() + fileName;
        String qrCode = ZixingCodeUtil.decodeQRCodeImage(filePath, null).replace("\uD83D\uDCF1", "");

        // 保存优惠券数据
        ShareTicketImg shareTicketImg = new ShareTicketImg();
        shareTicketImg.setUploadImgUUID(uploadImg.getUuid());
        shareTicketImg.setQRCode(qrCode);
        String[] split = qrCode.split("-----------------\n" + "长按復·制这段描述，");
        String[] split1 = split[0].split("\n");
        BigDecimal beforeMoney = new BigDecimal(split1[0].replace("【在售价】￥", ""));
        BigDecimal afterMoney = new BigDecimal(split1[1].replace("【券后价】￥", ""));
        shareTicketImg.setBeforeMoney(beforeMoney);
        shareTicketImg.setAfterMoney(afterMoney);
        String taobaoCode = split[1].replace("，打开【taobao】即可抢购", "");
        shareTicketImg.setTaobaoCode(taobaoCode);

        shareTicketImg.setTitle(baiduOCRDto.getWordsResult().get(0).getWords());


        String cutFileName = UUIDUtil.createUUID() + "." + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        // 裁剪图片，将二维码和粉象图标删除
        log.info("cutImg before");
//        BufferedImage bufferedImage = cutImg(multipartFile);
        File inFile = new File(config.getFilePath()+fileName);
        Image src = Toolkit.getDefaultToolkit().getImage(inFile.getPath());
        BufferedImage bufferedImage = toBufferedImage(src);
        BufferedImage subimage = bufferedImage.getSubimage(0, 100, 750, 850);
        String cutImgcompress = SaveAndPostImg.compressToCut(subimage, config.getFilePath(), cutFileName);

        Map cutImgcompressMap = JSON.parseObject(cutImgcompress, Map.class);

        HashMap<String, Object> cutHashMap = new HashMap<>();
        UploadImg cutImgUpload = new UploadImg();
        cutImgUpload.setOriginalName(originalFilename);
        cutImgUpload.setIsDel("0");
        cutImgUpload.setRandomName(cutFileName);
        if ("success".equals(cutImgcompressMap.get("code").toString())) {
            String responseUrl = ((Map) cutImgcompressMap.get("data")).get("url").toString();
            cutImgUpload.setResponseUrl(responseUrl);
            cutHashMap.put("img", ((Map) cutImgcompressMap.get("data")).get("url"));
            cutHashMap.put("code", 0);
        } else if ("error".equals(cutImgcompressMap.get("code").toString())) {
            cutHashMap.put("msg", cutImgcompressMap.get("msg"));
            cutHashMap.put("code", 1);
        }
        cutImgUpload.setTitle(cutImgUpload.getRandomName());
        if (SystemUtils.IS_OS_LINUX) {
            cutImgUpload.setImagePath('.' + config.getLinuxPath() + cutImgUpload.getRandomName());
        }
        insert((T) cutImgUpload);

        shareTicketImg.setCutUploadImgUUID(cutImgUpload.getUuid());
        shareTicketImgService.insert(shareTicketImg);

        if (SystemUtils.IS_OS_WINDOWS) {
            SaveAndPostImg.sendImage(config.getFilePath() + uploadImg.getRandomName());
            SaveAndPostImg.sendImage(config.getFilePath() + cutImgUpload.getRandomName());
        }
        return qrCode;
    }

    private BufferedImage toBufferedImage(Image image) {
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
    }}
