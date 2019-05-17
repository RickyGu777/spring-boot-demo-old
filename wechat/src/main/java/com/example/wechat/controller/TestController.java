package com.example.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @PostMapping(value = "/demo")
    public Object demo(@RequestBody(required = false) Object... objects) {
        return objects;
    }

    public static void main(String[] args) throws IOException {
        File inFile = new File("D://75de39c92eb7d69ffd90fe6778286987.dat");
        File outFile = new File("d:\\解密后的图片.jpg");

        FileInputStream input = new FileInputStream(inFile);
        FileOutputStream output = new FileOutputStream(outFile);

        int content = 0;
        while ((content = input.read()) != -1) {
            output.write(content ^ 0x51);
        }

        output.close();
        input.close();
//        File file = new File("D://20190514094531.jpg");
//        Image src = Toolkit.getDefaultToolkit().getImage(file.getPath());
//        BufferedImage rightImage = toBufferedImage(src);
//        int rightHeight = rightImage.getHeight();

//        int subRightHeight = rightHeight * 8 / 10; // 要裁剪的高度
//        int beginRightHeight = (rightHeight - subRightHeight) / 2; // 裁剪开始高度

//        log.info(beginRightHeight + "");

//        BufferedImage rightImageSubimage = rightImage.getSubimage(570, 989, 150, 150);

//        byte[] rightImageBytes = imageToBytes(rightImageSubimage, "PNG");
//        ImageIO.write(rightImageSubimage, "JPEG", new File("D://bb.jpg"));
//        file = new File("D://left.png");
//        src = Toolkit.getDefaultToolkit().getImage(file.getPath());
//        BufferedImage leftImage = toBufferedImage(src);
//
//        int leftImageWidth = leftImage.getWidth();
//        int leftImageHeight = leftImage.getHeight();
//
//        for (int i = leftImageWidth - 20; i > leftImageWidth / 2; i--) {
//            long startTime = System.nanoTime();   //获取开始时间
//            boolean over = false;
//            for (int i1 = 0; i1 < leftImageHeight - subRightHeight; i1++) {
//                BufferedImage leftImageSubimage = leftImage.getSubimage(i, i1, 20, subRightHeight);
//                byte[] leftImageBytes = imageToBytes(leftImageSubimage, "PNG");
//                over = Arrays.equals(rightImageBytes, leftImageBytes);
//                if (over) {
//                    ImageIO.write(rightImageSubimage, "PNG", new File("D://rignt_sub.png"));
//                    ImageIO.write(leftImageSubimage, "PNG", new File("D://left_sub.png"));
//                    long endTime = System.nanoTime(); //获取结束时间
//                    log.info("i:[{}],i1:[{}],costTime[{}]", i, i1, endTime - startTime);
//                    break;
//                }
//            }
//            if (over) {
//                break;
//            }
//        }
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

    public static BufferedImage toBufferedImage(Image image) {
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
     * @param file      源文件(图片)
     * @param waterFile 水印文件(图片)
     * @param x         距离右下角的X偏移量
     * @param y         距离右下角的Y偏移量
     * @param alpha     透明度, 选择值从0.0~1.0: 完全透明~完全不透明
     * @return BufferedImage
     * @throws IOException
     * @Title: 构造图片
     * @Description: 生成水印并返回java.awt.image.BufferedImage
     */
    public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
        // 获取底图
        BufferedImage buffImg = ImageIO.read(file);
        // 获取层图
        BufferedImage waterImg = ImageIO.read(waterFile);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        int waterImgWidth = waterImg.getWidth();// 获取层图的宽度
        int waterImgHeight = waterImg.getHeight();// 获取层图的高度
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        return buffImg;
    }

    /**
     * 输出水印图片
     *
     * @param buffImg  图像加水印之后的BufferedImage对象
     * @param savePath 图像加水印之后的保存路径
     */
    private static void generateWaterFile(BufferedImage buffImg, String savePath) {
        int temp = savePath.lastIndexOf(".") + 1;
        try {
            ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     *
     * @param args
     * @throws IOException
     *             IO异常直接抛出了
     * @author bls
     */
//    public static void main(String[] args) throws IOException {
//        String sourceFilePath = "D://1438494338165.jpg";
//        String waterFilePath = "D://left.png";
//        String saveFilePath = "D://left_sub1111.png";
////        NewImageUtils newImageUtils = new NewImageUtils();
//        // 构建叠加层
//        BufferedImage buffImg = watermark(new File(sourceFilePath), new File(waterFilePath), 0, 0, 1.0f);
//        // 输出水印图片
//        generateWaterFile(buffImg, saveFilePath);
//    }
}