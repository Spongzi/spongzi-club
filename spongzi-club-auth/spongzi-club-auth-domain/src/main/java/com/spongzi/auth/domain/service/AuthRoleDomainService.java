package com.spongzi.auth.domain.service;


import com.spongzi.auth.domain.entity.AuthRoleBO;

/**
 * 身份验证角色域服务
 *
 * @author spong
 * @date 2023/11/03
 */
public interface AuthRoleDomainService {

    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);

}
