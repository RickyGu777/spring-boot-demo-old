package com.example.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @PostMapping(value = "/demo")
    public Object demo(@RequestBody(required = false) Object... objects) {
        return objects;
    }

    public static void main(String[] args) throws IOException {
        // 读取图片
        BufferedImage bufImage = ImageIO.read(new File("D://left.png"));

        // 获取图片的宽高
        int width = bufImage.getWidth();
        int height = bufImage.getHeight();

        int subWidth = (int) (width * 0.1);
        int beginWidth = width - subWidth;
        int subHeight = (int) (height * 0.8);
        int beginHeight = (height - subHeight) / 2;


        // 读取出图片的所有像素
//        int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);

        // 对图片的像素矩阵进行水平镜像
//        for (int row = 0; row < height; row++) {
//            for (int col = 0; col < width / 2; col++) {
//                int temp = rgbs[row * width + col];
//                rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
//                rgbs[row * width + (width - 1 - col)] = temp;
//            }
//        }
        BufferedImage subimage = bufImage.getSubimage(beginWidth, beginHeight, subWidth, subHeight);

        byte[] jpegs = imageToBytes(subimage, "PNG");

        log.info("jpegs:[{}]", jpegs);
        log.info("jpegs length:[{}]", jpegs.length);


        BufferedImage bufImage2 = ImageIO.read(new File("D://right.png"));
        BufferedImage subimage2 = bufImage2.getSubimage(beginWidth, beginHeight, subWidth, subHeight);
        byte[] jpegs2 = imageToBytes(subimage2, "PNG");

        log.info("jpegs2:[{}]", jpegs2);
        log.info("jpegs2 length:[{}]", jpegs2.length);

        boolean same = true;
        for (int i = 0; i < jpegs2.length; i++) {
            if (jpegs2[i] != jpegs[i]) {
                same = false;
            }
        }

        if (same) {
            log.info("true");
        } else {
            log.info("false");
        }

        // 把水平镜像后的像素矩阵设置回 bufImage
//        bufImage.setRGB(0, 0, width, height, rgbs, 0, width);

        // 把修改过的 bufImage 保存到本地
//        ImageIO.write(subimage, "JPEG", new File("D://bb.jpg"));
//        ImageIO.write(subimage, "JPEG", new File("D://2.jpg"));
    }

    public static byte[] imageToBytes(BufferedImage bImage, String format) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
}