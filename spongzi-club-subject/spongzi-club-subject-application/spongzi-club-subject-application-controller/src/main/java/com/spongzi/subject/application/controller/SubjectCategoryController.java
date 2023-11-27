package com.spongzi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.spongzi.club.common.entity.Result;
import com.spongzi.subject.application.convert.SubjectCategoryConvert;
import com.spongzi.subject.application.convert.SubjectLabelConvert;
import com.spongzi.subject.application.dto.SubjectCategoryDTO;
import com.spongzi.subject.application.dto.SubjectLabelDTO;
import com.spongzi.subject.domain.entity.SubjectCategoryBO;
import com.spongzi.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 刷题分类Controller
 *
 * @author spong
 * @date 2023/10/06
 */
@Slf4j
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<String> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto: {}", JSON.toJSONString(subjectCategoryDTO));
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()), "分类的名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类父级id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryConvert.INSTANCE
                    .convertDtoToBo(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryPrimaryCategory.dto: {}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryConvert.INSTANCE
                    .convertDtoToBo(subjectCategoryDTO);
            subjectCategoryBO.setParentId(0L);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryConvert.INSTANCE.convertBoListToDtoList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error: {}", e.getMessage(), e);
            return Result.fail();
        }
    }

    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryByPrimary.dto: {}", JSON.toJSONString(subjectCategoryDTO));
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryConvert.INSTANCE
                    .convertDtoToBo(subjectCategoryDTO);

            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryConvert.INSTANCE.convertBoListToDtoList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error: {}", e.getMessage(), e);
            return Result.fail();
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryConvert.INSTANCE.convertDtoToBo(subjectCategoryDTO);
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.update.dto: {}", JSON.toJSONString(subjectCategoryDTO));
            }
            Boolean result = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error: {}", e.getMessage(), e);
            return Result.fail("更新分类失败");
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(SubjectCategoryDTO subjectCategoryDTO) {
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryConvert.INSTANCE.convertDtoToBo(subjectCategoryDTO);
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.delete.dto: {}", JSON.toJSONString(subjectCategoryDTO));
            }
            Boolean result = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error: {}", e.getMessage(), e);
            return Result.fail("删除分类失败");
        }
    }

    /**
     * 查询分类及标签，并且封装成为树形结构
     *
     * @param subjectCategoryDTO 主题类别dto
     * @return {@link Result}<{@link List}<{@link SubjectCategoryDTO}>>
     */
    @PostMapping("/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryAndLabel.dto: {}", JSON.toJSONString(subjectCategoryDTO));
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryConvert.INSTANCE
                    .convertDtoToBo(subjectCategoryDTO);

            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategoryAndLabel(subjectCategoryBO);
            List<SubjectCategoryDTO> dtoList = new LinkedList<>();
            subjectCategoryBOList.forEach(bo -> {
                SubjectCategoryDTO dto = SubjectCategoryConvert
                        .INSTANCE
                        .convertBoToDto(bo);
                List<SubjectLabelDTO> labelDTOList = SubjectLabelConvert
                        .INSTANCE
                        .convertBoListToDtoList(bo.getLabelBOList());
                dto.setLabelDTOList(labelDTOList);
                dtoList.add(dto);
            });
            
            return Result.ok(dtoList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error: {}", e.getMessage(), e);
            return Result.fail();
        }
    }
}
