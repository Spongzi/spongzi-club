package com.spongzi.subject.domain.service.impl;

import com.spongzi.subject.domain.convert.SubjectCategoryConvert;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.domain.service.SubjectCategoryDomainService;
import com.spongzi.subject.infra.basic.entity.SubjectCategory;
import com.spongzi.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 主题类别域服务实施
 *
 * @author spong
 * @date 2023/10/06
 */
@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;


    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategoryService.insert(subjectCategory);
    }
}
