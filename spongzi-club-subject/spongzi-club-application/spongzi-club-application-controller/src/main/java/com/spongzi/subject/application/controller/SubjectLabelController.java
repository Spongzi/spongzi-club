package com.spongzi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.spongzi.subject.application.convert.SubjectCategoryConvert;
import com.spongzi.subject.application.convert.SubjectLabelConvert;
import com.spongzi.subject.application.dto.SubjectCategoryDTO;
import com.spongzi.subject.application.dto.SubjectLabelDTO;
import com.spongzi.subject.common.entity.Result;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.domain.entity.SubjectLabelBO;
import com.spongzi.subject.domain.service.SubjectLabelDomainService;
import com.spongzi.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 标签Controller
 *
 * @author spong
 * @date 2023/10/07
 */
@Slf4j
@RestController
@RequestMapping("/subject/label")
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkNotNull(subjectLabelDTO.getLabelName(), "标签的名称不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelConvert.INSTANCE
                    .convertDtoToBo(subjectLabelDTO);
            subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
