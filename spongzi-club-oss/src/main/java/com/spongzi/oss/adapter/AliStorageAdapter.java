package com.spongzi.oss.adapter;

import com.spongzi.oss.entity.FileInfo;
import com.spongzi.oss.utils.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * 实施存储服务
 *
 * @author spong
 * @date 2023/10/16
 */
@Service(value = "aliStorageAdapter")
public class AliStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    @Override
    public void createBucket(String bucketName) throws Exception {
        minioUtil.createBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        minioUtil.createBucket(bucket);
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, objectName + "/" + uploadFile.getName());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, uploadFile.getName());
        }
    }

    @Override
    public List<String> getAllBuckets() throws Exception {
        LinkedList<String> bucketNames = new LinkedList<>();
        bucketNames.add("aliyun");
        return bucketNames;
    }

    @Override
    public List<FileInfo> getAllFile(String bucketName) throws Exception {
        return minioUtil.getAllFile(bucketName);
    }

    @Override
    public InputStream download(String bucket, String objectName) throws Exception {
        return minioUtil.download(bucket, objectName);
    }

    @Override
    public void deleteBucket(String bucket) throws Exception {
        minioUtil.deleteBucket(bucket);
    }

    @Override
    public void deleteObject(String bucket, String objectName) throws Exception {
        minioUtil.deleteObject(bucket, objectName);
    }
}
