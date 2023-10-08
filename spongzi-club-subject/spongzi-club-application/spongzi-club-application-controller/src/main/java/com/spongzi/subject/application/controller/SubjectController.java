package com.spongzi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.spongzi.subject.application.convert.SubjectAnswerConvert;
import com.spongzi.subject.application.convert.SubjectCategoryConvert;
import com.spongzi.subject.application.convert.SubjectInfoConvert;
import com.spongzi.subject.application.dto.SubjectCategoryDTO;
import com.spongzi.subject.application.dto.SubjectInfoDTO;
import com.spongzi.subject.common.entity.Result;
import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.domain.service.SubjectInfoDomainService;
import com.spongzi.subject.infra.basic.entity.SubjectCategory;
import com.spongzi.subject.infra.basic.entity.SubjectInfo;
import com.spongzi.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题controller
 *
 * @author spong
 * @date 2023/10/06
 */
@Slf4j
@RestController
@RequestMapping("subject")
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.add.dto: {}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkArgument(!StringUtils.isBlank(subjectInfoDTO.getSubjectName()), "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectAnswer(), "题目答案不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()), "分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()), "标签id不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoConvert.INSTANCE
                    .convertDtoToBo(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerConvert.INSTANCE
                    .convertDtoToBo(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOList);
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
