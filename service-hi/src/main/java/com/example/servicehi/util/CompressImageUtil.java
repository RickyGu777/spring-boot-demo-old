package com.example.servicehi.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class CompressImageUtil {
    public static void compress(MultipartFile multipartFile) {
        String origFilename = multipartFile.getOriginalFilename(); // 图片名
        File dest = new File("D:/images/" + System.currentTimeMillis() + origFilename); // 保存位置
        try {
            // 先尝试压缩并保存图片
            Thumbnails.of(multipartFile.getInputStream()).scale(1f).outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            try {
                // 失败了再用springmvc自带的方式
                multipartFile.transferTo(dest);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
