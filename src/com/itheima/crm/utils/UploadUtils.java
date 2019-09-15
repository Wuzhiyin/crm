package com.itheima.crm.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 */
public class UploadUtils {
    /**
     * 解决目录下文件名重复的问题
     * @param fileName
     * @return
     */
    public static String getUuidName(String fileName){
        int idx = fileName.lastIndexOf(".");
        String extions = fileName.substring(idx);
        return UUID.randomUUID().toString().replace("-","")+extions;
    }

    /**
     * 目录分离的方法
     * @param uuidFileName
     * @return
     */
    public static String getPath(String uuidFileName){
        int code1 = uuidFileName.hashCode();
        int d1 = code1 & 0XF;//作为一级目录
        int code2 = code1>>>4;
        int d2 = code2 &0XF;
        return "/"+d1+"/"+d2;
    }

    public static void main(String[] args) {
        System.out.println(getUuidName("aa.txt"));
    }
}
