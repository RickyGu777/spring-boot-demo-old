package com.util.PrtSc;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: KanClad
 * Date: 2018/12/6
 * Time: 15:54
 */
public class KeyBordTest extends JFrame implements KeyListener {

    private static final long serialVersionUID = -4850832093718125631L;
    private List<BufferedImage> checkList = null;
    private BufferedImage quyu1; // 原始图案
    private BufferedImage quyu2; // 运行时图案
    private BufferedImage bianzu1;
    private BufferedImage bianzu2;
    private BufferedImage qidian1;
    private BufferedImage qidian2;
    private BufferedImage kaishi1;
    private BufferedImage kaishi2;
    private BufferedImage pointH1;
    private BufferedImage pointH2;
    private BufferedImage pointI1;
    private BufferedImage pointI2;

    public static void main(String[] args) {
        KeyBordTest ket = new KeyBordTest();
    }

    public KeyBordTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("KeyEventTest");
        this.setSize(300, 300);
        this.setVisible(true);
        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /*@Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 10) {
            System.err.println("回车---开始点击");
            List<PointXY> points = getPoint("7-6","1");
            for (PointXY point : points) {
                click(point);
            }
        } else if (e.getKeyCode() == 49){
            System.err.println("1---区域");
            basePicture(quyu1,350,50,366,287);
        } else if (e.getKeyCode() == 50){
            System.err.println("2---编组");
            basePicture(bianzu1,200,200,1648,885);
        } else if (e.getKeyCode() == 51){
            System.err.println("3---开始");
            basePicture(qidian1,200,200,1648,885);
        } else if (e.getKeyCode() == 52){
            System.err.println("4---A起点");
            basePicture(qidian1,300,200,464,540);
        } else if (e.getKeyCode() == 53){
            System.err.println("5---开始战斗");
            basePicture(kaishi1,200,170,1652,902);
        } else if (e.getKeyCode() == 54){
            System.err.println("6---战斗结束");
            basePicture(kaishi1,200,50,451,124);
        } else if (e.getKeyCode() == 55){
            System.err.println("7---H点");
            basePicture(pointH1,50,50,957,492);
        } else if (e.getKeyCode() == 56){
            System.err.println("8---I点");
            basePicture(pointI1,50,50,957,492);
        } else if (e.getKeyCode() == 57){
            System.err.println("9---选择分支");
        }
    }*/

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    private void basePicture(BufferedImage bufferedImageTemp, int width, int height, int x, int y) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        BufferedImage bi = robot.createScreenCapture(new Rectangle(Screen.getScreenWidth(), Screen.getScreenHeight()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "bmp", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("bmp");
        ImageReader reader = readers.next();
        ImageInputStream imageStream = null;
        try {
            imageStream = ImageIO.createImageInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.setInput(imageStream, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x - (width/2), y - (height/2), width, height);
        param.setSourceRegion(rect);
        try {
            bufferedImageTemp = reader.read(0, param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImageTemp, "bmp", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream temp = new ByteArrayInputStream(os.toByteArray());
        String fileMD5 = getFileMD5(temp);
        System.err.println(fileMD5);
    }

    /**
     * 判断两个截图是否相同
     * @param inputStream
     * @return
     */
    private static String getFileMD5(InputStream inputStream) {
        if (inputStream == null){
            return null;
        }
        MessageDigest digest = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    private List<PointXY> getPoint(String mapName,String type){
        List<PointXY> list = new ArrayList<>();
        list.add(new PointXY(1648, 885,2)); // 编组
        list.add(new PointXY(1648, 885,3)); // 开始
/*
        PointXY fightBegin = new PointXY(1652, 902,35); // 开始战斗
        PointXY fightEnd = new PointXY(451, 124,3); // 开始战斗
        if ("7-6".equals(mapName)) {
            if ("1".equals(type)) {
                list.add(new PointXY(464, 540,3)); // 选择 A 点
                list.add(fightBegin);
                list.add(new PointXY(1653, 745,3));// E
                list.add(fightBegin);
                list.add(new PointXY(1392, 741,3));// G
                list.add(fightBegin);
                list.add(fightEnd);
//                list.add(null);// 等待轮盘
//                list.add(new PointXY(1188, 543,10));// I
//                list.add(point);
            }
        }
        list.add(new PointXY(451, 124,30));
*/
        return list;
    }

    private void click(PointXY point) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //鼠标移动到某一点
        robot.mouseMove(point.x, point.y);
        //模拟鼠标按下左键
        robot.mousePress(InputEvent.BUTTON1_MASK);
        //模拟鼠标松开左键
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}

class PointXY{
    int x;
    int y;
    long time;

    public PointXY(int x, int y, long time) {
        this.x = x;
        this.y = y;
        this.time = time * 1000;
    }
}