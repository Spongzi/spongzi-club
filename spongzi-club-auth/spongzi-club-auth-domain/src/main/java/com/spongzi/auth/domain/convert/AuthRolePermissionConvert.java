package com.spongzi.auth.domain.convert;

import com.spongzi.auth.domain.entity.AuthRolePermissionBO;
import com.spongzi.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 身份验证权限转换
 *
 * @author spong
 * @date 2023/11/03
 */
@Mapper
public interface AuthRolePermissionConvert {
    AuthRolePermissionConvert INSTANCE = Mappers.getMapper(AuthRolePermissionConvert.class);

    /**
     * 将bo转换为实体
     *
     * @param authRolePermissionBO 身份验证角色权限bo
     * @return {@link AuthRolePermission}
     */
    AuthRolePermission convertBoToEntity(AuthRolePermissionBO authRolePermissionBO);
}
