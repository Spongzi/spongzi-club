package com.spongzi.auth.infra.basic.service;

import com.spongzi.auth.infra.basic.entity.AuthRolePermission;

import java.util.List;

/**
 * (AuthRolePermission)表服务接口
 *
 * @author spongzi
 * @since 2023-11-05 20:33:26
 */
public interface AuthRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission insert(AuthRolePermission authRolePermission);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量插入
     *
     * @param rolePermissionList 角色权限列表
     */
    int batchInsert(List<AuthRolePermission> rolePermissionList);
}
