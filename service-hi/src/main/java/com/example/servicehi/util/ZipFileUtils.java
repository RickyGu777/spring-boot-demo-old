package com.example.servicehi.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZipFileUtils {

    private static ZipOutputStream outputStream;

    /**
     * 1.创建时间戳文件夹和ZIP文件
     * 2.创建Zip输出流
     *
     * @param folderPath
     */
    public static void init(String folderPath) {
        File excelFolder = new File(folderPath);
        excelFolder.mkdir();
        String zipFileName = folderPath + "/" + folderPath.substring(folderPath.lastIndexOf("/") + 1) + ".zip";
        File zipFile = new File(zipFileName);
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                log.error("创建文件" + zipFileName + "失败");
                e.printStackTrace();
            }
        }
        FileOutputStream fous;
        try {
            fous = new FileOutputStream(zipFile);
            outputStream = new ZipOutputStream(fous);
        } catch (FileNotFoundException e) {
            log.error(zipFileName + "不存在!");
            e.printStackTrace();
        }
    }

    /**
     * 把文件夹中的所有文件写入到ZIP输出流
     *
     * @param file
     */
    public static void writeToZipFile(String folderPath) {
        writeToZipFile(new File(folderPath));
    }

    /**
     * 把单个文件写入到ZIP输出流
     *
     * @param file
     */
    public static void writeToZipFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    log.debug("压缩文件:" + file.getName());
                    ZipEntry entry = new ZipEntry(file.getName());
                    outputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int length;
                    byte[] buffer = new byte[4096];
                    while ((length = bis.read(buffer)) != -1) {
                        log.debug("向压缩文件中写入数据大小：{}", length);
                        outputStream.write(buffer, 0, length);
                    }
                } catch (IOException e) {
                    log.error("把Excel文件写入压缩文件中出现异常：", e);
                    e.printStackTrace();
                } finally {
                    // 关闭创建的流对象
                    try {
                        bis.close();
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                writeToZipFile(file.listFiles());
            }
        } else {
            log.error(file.getName() + "文件不存在");
        }
    }

    /**
     * 把多个文件写入到ZIP输出流
     *
     * @param files
     */
    public static void writeToZipFile(File[] files) {
        int length = files.length;
        for (int i = 0; i < length; i++) {
            writeToZipFile(files[i]);
        }
    }

    /**
     * 下载ZIP文件
     *
     * @param response
     * @param folderPath
     */
    public static void downloadZipFile(HttpServletResponse response, String folderPath) {
        String zipFileName = folderPath.substring(folderPath.lastIndexOf("/") + 1) + ".zip";
        String zipFilePath = folderPath + "/" + zipFileName;
        log.debug("下载压缩文件：{}", zipFilePath);
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "must-revalidate, no-transform");
        response.setDateHeader("Expires", 0L);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipFileName + "");

        // 获取服务其上生成的的ZIP文件
        File zipFile = new File(zipFilePath);
        // 将此文件流写入到response输出流中
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream ops = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(zipFile);
            bis = new BufferedInputStream(fis);
            ops = response.getOutputStream();
            bos = new BufferedOutputStream(ops);
            byte[] buffer = new byte[4096];
            int length = -1;
            log.debug("压缩文件大小：{}", bis.available());
            while ((length = bis.read(buffer)) != -1) {
                log.debug("读取的数据大小：{}", length);
                bos.write(buffer, 0, length);
            }
            bos.flush();
            ops.flush();
        } catch (FileNotFoundException e) {
            log.error("下载的压缩文件不存在：{}", zipFilePath);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("下载压缩文件{}时出现异常：", zipFilePath, e);
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                ops.close();
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        }
        file.delete();
    }

    /**
     * 删除一个文件夹下面的所有文件
     *
     * @param folderPath
     */
    public static void deleteFile(String folderPath) {
        File file = new File(folderPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                f.delete();
            }
        }
        file.delete();
    }

    /**
     * 关闭ZIP输出流
     */
    public static void close() {
        if (outputStream != null) {
            try {
                log.info("close");
                outputStream.closeEntry();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}