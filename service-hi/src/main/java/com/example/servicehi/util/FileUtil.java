package com.example.servicehi.util;

import java.io.File;

public class FileUtil {

    /**
     * 判断父文件夹是否存在，如果不存在则创建
     *
     * @param file
     */
    public static void checkParentFile(File file) {
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }
}