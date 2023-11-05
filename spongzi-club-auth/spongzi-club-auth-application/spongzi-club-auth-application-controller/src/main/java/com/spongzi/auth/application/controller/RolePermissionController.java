package com.spongzi.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.spongzi.auth.application.convert.AuthRolePermissionConvert;
import com.spongzi.auth.application.dto.AuthRolePermissionDTO;
import com.spongzi.auth.domain.entity.AuthRolePermissionBO;
import com.spongzi.auth.domain.service.AuthRolePermissionDomainService;
import com.spongzi.club.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限controller
 *
 * @author spong
 * @date 2023/11/03
 */
@RestController
@RequestMapping("/rolePermission/")
@Slf4j
public class RolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    /**
     * 新增角色权限关联关系
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto:{}", JSON.toJSONString(authRolePermissionDTO));
            }

            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限id列表不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色不能为空");

            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionConvert.INSTANCE.convertDtoToBo(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增权限失败");
        }
    }
}
