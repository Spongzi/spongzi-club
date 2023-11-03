package com.spongzi.auth.application.convert;

import com.spongzi.auth.application.dto.AuthRoleDTO;
import com.spongzi.auth.domain.entity.AuthRoleBO;
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
     * 将dto转换为bo
     *
     * @param authRoleDTO 身份验证角色dto
     * @return {@link AuthRoleBO}
     */
    AuthRoleBO convertDtoToBo(AuthRoleDTO authRoleDTO);
}
