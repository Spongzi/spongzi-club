package com.spongzi.auth.domain.service;


import com.spongzi.auth.domain.entity.AuthPermissionBO;

import java.util.List;

/**
 * 身份验证权限域服务
 *
 * @author spong
 * @date 2023/11/03
 */
public interface AuthPermissionDomainService {

    /**
     * 添加
     *
     * @param authPermissionBO 授权权限博
     * @return {@link Boolean}
     */
    Boolean add(AuthPermissionBO authPermissionBO);

    /**
     * 更新
     *
     * @param authPermissionBO 授权权限博
     * @return {@link Boolean}
     */
    Boolean update(AuthPermissionBO authPermissionBO);

    /**
     * 删除
     *
     * @param authPermissionBO 授权权限博
     * @return {@link Boolean}
     */
    Boolean delete(AuthPermissionBO authPermissionBO);

    /**
     * 获取权限
     *
     * @param username 用户名
     * @return {@link List}<{@link String}>
     */
    List<String> getPermission(String username);
}
