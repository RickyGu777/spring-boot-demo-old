package com.example.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @PostMapping(value = "/demo")
    public Object demo(@RequestBody(required = false) Object ...objects){
        return objects;
    }

    public static void main(String[] args) throws IOException {
        // 读取图片
        BufferedImage bufImage = ImageIO.read(new File("D://1438494338165.jpg"));

        // 获取图片的宽高
//        final int width = bufImage.getWidth();
//        final int height = bufImage.getHeight();

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
        BufferedImage subimage = bufImage.getSubimage(0, 0, 500, 250);

        // 把水平镜像后的像素矩阵设置回 bufImage
//        bufImage.setRGB(0, 0, width, height, rgbs, 0, width);

        // 把修改过的 bufImage 保存到本地
        ImageIO.write(subimage, "JPEG", new File("D://bb.jpg"));
    }
}