package com.spongzi.oss.service;

import com.spongzi.oss.adapter.StorageAdapter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件服务文件服务
 *
 * @author spong
 * @date 2023/10/16
 */
@Service
public class FileService {
    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    @SneakyThrows
    public List<String> getAllBuckets() {
        return storageAdapter.getAllBuckets();
    }

    /**
     * 获取URL
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return {@link String}
     * @throws Exception 例外情况
     */
    public String getUrl(String bucketName, String objectName) throws Exception {
        return storageAdapter.getUrl(bucketName, objectName);
    }

    /**
     * 将文件上载到文件
     *
     * @param uploadFile 将文件上载到文件
     * @param bucket     水桶
     * @param objectName 对象名称
     * @throws Exception 例外情况
     */
    public String uploadFile(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        storageAdapter.uploadFile(uploadFile, bucket, objectName);
        return storageAdapter.getUrl(bucket, objectName);
    }
}
