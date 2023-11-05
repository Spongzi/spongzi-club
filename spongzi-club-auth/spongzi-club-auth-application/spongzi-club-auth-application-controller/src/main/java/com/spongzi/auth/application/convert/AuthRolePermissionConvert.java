package com.spongzi.auth.application.convert;

import com.spongzi.auth.application.dto.AuthRolePermissionDTO;
import com.spongzi.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 身份验证用户转换
 *
 * @author spong
 * @date 2023/11/03
 */
@Mapper
public interface AuthRolePermissionConvert {
    AuthRolePermissionConvert INSTANCE = Mappers.getMapper(AuthRolePermissionConvert.class);

    /**
     * 将dto转换为bo
     *
     * @param authRolePermissionDTO 身份验证角色权限dto
     * @return {@link AuthRolePermissionBO}
     */
    AuthRolePermissionBO convertDtoToBo(AuthRolePermissionDTO authRolePermissionDTO);
}
