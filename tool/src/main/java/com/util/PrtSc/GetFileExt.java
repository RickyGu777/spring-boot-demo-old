package com.util.PrtSc;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 判断图片类型
 */
public class GetFileExt {
    private static final String JPG_HEX = "ff";
    private static final String PNG_HEX = "89";
    private static final String JPG_EXT = "jpg";
    private static final String PNG_EXT = "png";

    public static String getFileExt(String filePath) {
        FileInputStream fis = null;
        String extension = FilenameUtils.getExtension(filePath);
        try {
            fis = new FileInputStream(new File(filePath));
            byte[] bs = new byte[1];
            fis.read(bs);
            String type = Integer.toHexString(bs[0]&0xFF);
            if(JPG_HEX.equals(type))
                extension = JPG_EXT;
            if(PNG_HEX.equals(type))
                extension = PNG_EXT;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return extension;
    }
}

class GetFileExtTest{

    /**
     * 判断图片类型
     */
    public static void main(String[] args) {
        String fileExt = GetFileExt.getFileExt("/home/kanlad/Screenshot_20181204-190844.jpg");
        System.out.println(fileExt);
    }
}