package com.util.PrtSc;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * 图片截取
 */
public class ImageUtils {
    public static void cutJPG(InputStream input, OutputStream out, int x,
                              int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(input);
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();

            System.out.println(reader.getWidth(0));
            System.out.println(reader.getHeight(0));
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, "jpg", out);
        } finally {
            imageStream.close();
        }
    }


    public static void cutPNG(InputStream input, OutputStream out, int x,
                              int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(input);
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();

            System.out.println(reader.getWidth(0));
            System.out.println(reader.getHeight(0));

            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, "png", out);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            imageStream.close();
        }
    }

    public static void cutImage(InputStream input, OutputStream out, String type,int x,
                                int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        try {
            String imageType=(null==type||"".equals(type))?"jpg":type;
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(imageType);
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(input);
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, imageType, out);
        } finally {
            imageStream.close();
        }
    }
}
class ImageUtilsTest{
    /**
     * 图片截取
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
//        long l = System.currentTimeMillis();
//        ImageUtils.cutJPG(new FileInputStream("/home/kanlad/Screenshot_20181204-190844"+".jpg"),
//                new FileOutputStream("/home/kanlad/"+l+".jpg"), 1840,760,320,320);
//        l = System.currentTimeMillis();
//        ImageUtils.cutJPG(new FileInputStream("/home/kanlad/Screenshot_20181204-190854"+".jpg"),
//                new FileOutputStream("/home/kanlad/"+l+".jpg"), 1840,760,320,320);
///*
//        ImageUtils.cutPNG(new FileInputStream("/home/kanlad/bootstrap栅格1.png"),
//                new FileOutputStream("/home/kanlad/bootstrap栅格2.png"), 0,0,50,40);
//*/
//    }
}