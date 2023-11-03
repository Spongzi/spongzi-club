package com.spongzi.auth.domain.service;


import com.spongzi.auth.domain.entity.AuthPermissionBO;

/**
 * 身份验证权限域服务
 *
 * @author spong
 * @date 2023/11/03
 */
public interface AuthPermissionDomainService {

    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);

}
