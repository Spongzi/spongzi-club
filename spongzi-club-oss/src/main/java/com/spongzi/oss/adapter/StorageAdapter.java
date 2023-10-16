package com.spongzi.oss.adapter;

import com.spongzi.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 存储服务适配器
 *
 * @author spong
 * @date 2023/10/16
 */
public interface StorageAdapter {

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     * @throws Exception 例外情况
     */
    void createBucket(String bucketName) throws Exception;

    /**
     * 将文件上载到文件
     *
     * @param bucket     水桶
     * @param objectName 对象名称
     * @param uploadFile 将文件上载到文件
     * @throws Exception 例外情况
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName) throws Exception;

    /**
     * 得到所有桶
     *
     * @throws Exception 例外情况
     */
    List<String> getAllBuckets() throws Exception;

    /**
     * 列出当前桶及所有文件
     *
     * @return {@link List}<{@link String}>
     * @throws Exception 例外情况
     */
    List<FileInfo> getAllFile(String bucketName) throws Exception;

    /**
     * 下载
     *
     * @param bucket     水桶
     * @param objectName 对象名称
     * @return {@link InputStream}
     */
    InputStream download(String bucket, String objectName) throws Exception;

    /**
     * 删除存储桶
     *
     * @param bucket 水桶
     * @throws Exception 例外情况
     */
    void deleteBucket(String bucket) throws Exception;

    /**
     * 删除对象
     *
     * @param bucket     水桶
     * @param objectName 对象
     * @throws Exception 例外情况
     */
    void deleteObject(String bucket, String objectName) throws Exception;
}
