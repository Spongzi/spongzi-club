package com.spongzi.subject.application.controller;

import com.spongzi.subject.application.convert.SubjectCategoryConvert;
import com.spongzi.subject.application.dto.SubjectCategoryDTO;
import com.spongzi.subject.common.entity.Result;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.domain.service.SubjectCategoryDomainService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 刷题分类Controller
 *
 * @author spong
 * @date 2023/10/06
 */
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryConvert.INSTANCE
                    .convertBoToCategory(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.fail();
        }
    }

}
