package com.spongzi.auth.domain.service;

import com.spongzi.auth.domain.entity.AuthUserBO;

/**
 * 身份验证用户域服务
 *
 * @author spong
 * @date 2023/11/03
 */
public interface AuthUserDomainService {
    /**
     * 用户注册
     *
     * @param authUserBO 身份验证用户bo
     * @return {@link Boolean}
     */
    Boolean register(AuthUserBO authUserBO);

    /**
     * 更新
     *
     * @param authUserBO 身份验证用户bo
     * @return {@link Boolean}
     */
    Boolean update(AuthUserBO authUserBO);

    /**
     * 删除
     *
     * @param authUserBO 身份验证用户bo
     * @return {@link Boolean}
     */
    Boolean delete(AuthUserBO authUserBO);
}
