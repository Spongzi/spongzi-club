package com.spongzi.subject.application.controller;

import com.spongzi.subject.infra.basic.entity.SubjectCategory;
import com.spongzi.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 刷题controller
 *
 * @author spong
 * @date 2023/10/06
 */
@RestController
public class SubjectController {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @GetMapping("/test")
    public String test() {
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }
}
