package com.util.PrtSc;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseTest {
    public static void main(String[] args) {
//        获取鼠标位置
        while(true){
            Point point = MouseInfo.getPointerInfo().getLocation();
            System.out.println(point);
            try { Thread.sleep(100); } catch (InterruptedException e) { }
        }
        //        PointerInfo pinfo = MouseInfo.getPointerInfo();
//        Point p = pinfo.getLocation();
//        double mx = p.getX();
//        double my = p.getY();
//        //初始化robot
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
//
////鼠标移动到某一点
//        robot.mouseMove(1, 2);
////模拟鼠标按下左键
//        robot.mousePress(InputEvent.BUTTON1_MASK);
////模拟鼠标松开左键
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//
////InputEvent.BUTTON2_MASK表示鼠标中键
////InputEvent.BUTTON3_MASK表示鼠标右键
////robot还可以模拟键盘点击，如有需要请自行百度

    }
}