package com.example.servicehi.util;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomUtil {
    /**
     * 根据传入的参数
     * 生成随机数+随机英文字幕的组合
     * 例：1VRO97DH
     *
     * @param num
     * @return
     */
    public static String createCodeAndNumber(int num) {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) {
                // 产生字母，random.nextInt( 26 )+nextInt,65是大写，90是小写
                // int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65:90 ;//这是随机获取大写或小写
                code += (char) (random.nextInt(26) + 65);//我写的是只是随机获取大写
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                code += String.valueOf(random.nextInt(10));
            }
        }
        return code;
    }

    public static String createNumber(int num) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(Math.random() * (Math.pow(10D, (double) num)));
    }
}
