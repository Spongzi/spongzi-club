package com.spongzi.auth.infra.basic.service;

import com.spongzi.auth.infra.basic.entity.AuthPermission;

import java.util.List;

/**
 * (AuthPermission)表服务接口
 *
 * @author spongzi
 * @since 2023-11-03 00:45:50
 */
public interface AuthPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermission queryById(Long id);


    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    int insert(AuthPermission authPermission);

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    int update(AuthPermission authPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 按角色列表查询
     *
     * @param permissionIdList 权限ID列表
     * @return {@link List}<{@link AuthPermission}>
     */
    List<AuthPermission> queryByRoleList(List<Long> permissionIdList);
}
