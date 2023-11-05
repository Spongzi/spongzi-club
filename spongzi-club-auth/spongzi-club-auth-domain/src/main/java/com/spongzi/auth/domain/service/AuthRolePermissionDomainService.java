package com.spongzi.auth.domain.service;


import com.spongzi.auth.domain.entity.AuthRolePermissionBO;

/**
 * 身份验证角色权限域服务
 *
 * @author spong
 * @date 2023/11/03
 */
public interface AuthRolePermissionDomainService {

    /**
     * 添加
     *
     * @param authRolePermissionBO 身份验证角色权限bo
     * @return {@link Boolean}
     */
    Boolean add(AuthRolePermissionBO authRolePermissionBO);

}
