package com.spongzi.oss.controller;

import com.spongzi.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件控制器文件控制器
 *
 * @author spong
 * @date 2023/10/14
 */
@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @Value("${storage.service.type}")
    private String storageType;

    @GetMapping("/test-get-all-buckets")
    public String testGetAllBuckets() {
        try {
            return fileService.getAllBuckets().get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取URL
     *
     * @param bucketName 存储桶名称
     * @param objectName 对象名称
     * @return {@link String}
     * @throws Exception 例外情况
     */
    @GetMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     *
     * @param uploadFile 将文件上载到文件
     * @param bucket     水桶
     * @param objectName 对象名称
     * @return {@link String}
     * @throws Exception 例外情况
     */
    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        return fileService.uploadFile(uploadFile, bucket, objectName);
    }

    @GetMapping("/test-nacos")
    public String testNacos() {
        return storageType;
    }
}
