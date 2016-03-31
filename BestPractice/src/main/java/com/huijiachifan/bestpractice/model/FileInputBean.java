/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.model;

import com.huijiachifan.bestpractice.util.ExceptionUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 封装文件上传参数使用的JavaBean
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月25日
 *
 * @author 李旺成    liwangcheng@jiashuangkuaizi.com
 * @version 1.0
 */

public class FileInputBean {

    private FileInfo fileInfo;
    private FileInfo[] fileInfoArr;
    private Map<String, String> params;
    private Map<String, String> headers;

    public FileInputBean() {}

    public FileInputBean(String serverField, String fileName, File file, Map<String, String> headers, Map<String, String> params) {
        this.fileInfo = new FileInfo(serverField, fileName, file);
        this.headers = headers;
        this.params = params;
    }

    /**
     * 返回示例用的文件上传JavaBean
     * @param fileName
     * @param file
     * @return
     */
    public static FileInputBean getTestFileInputBean(String fileName, File file) {
        if (file == null || !file.exists()) {
            ExceptionUtil.illegalArgument("文件不存在");
        }
        FileInputBean fileInputBean = new FileInputBean();
        fileInputBean.fileInfo = new FileInfo("mFile", fileName, file);
        fileInputBean.params = new HashMap<>();
        fileInputBean.params.put("username", "张鸿洋");
        fileInputBean.params.put("password", "123");
        fileInputBean.headers = new HashMap<>();
        fileInputBean.headers.put("APP-Key", "APP-Secret222");
        fileInputBean.headers.put("APP-Secret", "APP-Secret111");
        return fileInputBean;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public FileInfo[] getFileInfoArr() {
        return fileInfoArr;
    }

    public void setFileInfoArr(FileInfo[] fileInfoArr) {
        this.fileInfoArr = fileInfoArr;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public static final class FileInfo {

        private String serverField;                             // 服务器端接收时要求使用的参数名
        private String fileName;
        private File file;

        public FileInfo() {}

        public FileInfo(String serverField, String fileName, File file) {
            this.serverField = serverField;
            this.fileName = fileName;
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getServerField() {
            return serverField;
        }

        public void setServerField(String serverField) {
            this.serverField = serverField;
        }
    }
}
