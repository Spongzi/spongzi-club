package com.spongzi.auth.domain.convert;

import com.spongzi.auth.domain.entity.AuthRoleBO;
import com.spongzi.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 身份验证用户转换
 *
 * @author spong
 * @date 2023/11/03
 */
@Mapper
public interface AuthRoleConvert {
    AuthRoleConvert INSTANCE = Mappers.getMapper(AuthRoleConvert.class);

    /**
     * 将bo转换为实体
     *
     * @param authRoleBO 身份验证角色bo
     * @return {@link AuthRole}
     */
    AuthRole convertBoToEntity(AuthRoleBO authRoleBO);
}
