package com.spongzi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.spongzi.club.common.entity.Result;
import com.spongzi.subject.application.convert.SubjectLabelConvert;
import com.spongzi.subject.application.dto.SubjectLabelDTO;
import com.spongzi.subject.domain.entity.SubjectLabelBO;
import com.spongzi.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkNotNull(subjectLabelDTO.getLabelName(), "标签的名称不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelConvert.INSTANCE
                    .convertDtoToBo(subjectLabelDTO);
            Boolean add = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(add);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkNotNull(subjectLabelDTO.getId(), "当前修改标签id不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelConvert.INSTANCE
                    .convertDtoToBo(subjectLabelDTO);
            Boolean update = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(update);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkNotNull(subjectLabelDTO.getId(), "当前要修改的标签id不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelConvert.INSTANCE
                    .convertDtoToBo(subjectLabelDTO);
            Boolean update = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(update);
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete.error: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/query-label-by-categoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "当前修改标签id不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelConvert.INSTANCE
                    .convertDtoToBo(subjectLabelDTO);
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);

            List<SubjectLabelDTO> subjectLabelDTOList = SubjectLabelConvert.INSTANCE.convertBoListToDtoList(subjectLabelBOList);

            return Result.ok(subjectLabelDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error: {}", e.getMessage(), e);
            return Result.fail("查询分类下标签失败");
        }
    }
}
