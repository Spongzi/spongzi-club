package com.spongzi.auth.domain.service.impl;

import com.spongzi.auth.domain.entity.AuthRolePermissionBO;
import com.spongzi.auth.domain.service.AuthRolePermissionDomainService;
import com.spongzi.auth.infra.basic.entity.AuthRolePermission;
import com.spongzi.auth.infra.basic.service.AuthRolePermissionService;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 身份验证角色权限域服务实施
 *
 * @author spong
 * @date 2023/11/05
 */
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            rolePermissionList.add(authRolePermission);
        });
        int insert = authRolePermissionService.batchInsert(rolePermissionList);
        return insert > 0;
    }
}
