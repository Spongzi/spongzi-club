package com.spongzi.auth.infra.basic.service;

import com.spongzi.auth.infra.basic.entity.AuthUser;

import java.util.List;

/**
 * (AuthUser)表服务接口
 *
 * @author spongzi
 * @since 2023-11-01 01:25:23
 */
public interface AuthUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Long id);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    Integer insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    Integer update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 按条件查询
     *
     * @param authUser 身份验证用户
     * @return {@link List}<{@link AuthUser}>
     */
    List<AuthUser> queryByCondition(AuthUser authUser);
}
