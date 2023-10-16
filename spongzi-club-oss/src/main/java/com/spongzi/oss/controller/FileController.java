package com.spongzi.oss.controller;

import com.spongzi.oss.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test-get-all-buckets")
    public String testGetAllBuckets() {
        try {
            return fileService.getAllBuckets().get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
