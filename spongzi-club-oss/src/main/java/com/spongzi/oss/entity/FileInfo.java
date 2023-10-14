package com.spongzi.oss.entity;

/**
 * 文件信息文件
 *
 * @author spong
 * @date 2023/10/14
 */
public class FileInfo {

    /**
     * 文件名文件名
     */
    private String fileName;

    /**
     * 目录标志目录
     */
    private Boolean directoryFlag;

    /**
     * ETag
     */
    private String etag;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getDirectoryFlag() {
        return directoryFlag;
    }

    public void setDirectoryFlag(Boolean directoryFlag) {
        this.directoryFlag = directoryFlag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
