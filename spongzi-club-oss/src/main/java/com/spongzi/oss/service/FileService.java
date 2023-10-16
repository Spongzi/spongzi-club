package com.spongzi.oss.service;

import com.spongzi.oss.adapter.StorageAdapter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

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
}
