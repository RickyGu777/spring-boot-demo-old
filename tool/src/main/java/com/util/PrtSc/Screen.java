package com.util.PrtSc;

/**
 * 获取系统分辨率
 */
public class Screen {
    private static int screenWidth;
    private static int screenHeight;

    public static int getScreenWidth() {

        setScreenWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        Screen.screenWidth = screenWidth;
    }

    public static int getScreenHeight() {
        setScreenHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        return screenHeight;
    }

    public static void setScreenHeight(int screenHeight) {
        Screen.screenHeight = screenHeight;
    }
}

//class ScreenTest {
//    public static void main(String[] args) {
//        int screenWidth=Screen.getScreenWidth();
//        int screenHeight=Screen.getScreenHeight();
//        System.out.println("屏幕宽为："+screenWidth+"---屏幕高为："+screenHeight);
//    }
//}