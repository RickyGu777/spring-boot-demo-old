package com.util.PrtSc;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;

/**
 * 尝试使用Java截图
 */
public class RobotExp {
    private static final int QU_YU_X = 957;
    private static final int QU_YU_Y = 492;

//    public static void main(String[] args) {
//        try {
//            Robot robot = new Robot();
//            //根据指定的区域抓取屏幕的指定区域，1300是长度，800是宽度。
//            BufferedImage bi = robot.createScreenCapture(new Rectangle(Screen.getScreenWidth(), Screen.getScreenHeight()));
//
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            ImageIO.write(bi, "bmp", os);
//            InputStream is = new ByteArrayInputStream(os.toByteArray());
//
//            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("bmp");
//            ImageReader reader = readers.next();
//            ImageInputStream imageStream = ImageIO.createImageInputStream(is);
//            reader.setInput(imageStream, true);
//            ImageReadParam param = reader.getDefaultReadParam();
//            Thread.sleep(2500);
//            int width = 50;
//            int height = 50;
//            Rectangle rect = new Rectangle(QU_YU_X - (width/2), QU_YU_Y - (height/2), width, height);
//            param.setSourceRegion(rect);
//            BufferedImage bi2 = reader.read(0, param);
//            long l = System.currentTimeMillis();
//            ImageIO.write(bi2, "bmp", new FileOutputStream("D:\\诺亚幻想\\基础图片\\QUYU"+l+".bmp")); // 编组
//
////            while (true) {
////                Thread.sleep(1000);
////                Point point = MouseInfo.getPointerInfo().getLocation();
////                System.out.println(point.x-160);
////                System.out.println(point.y-160);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}