package com.spongzi.auth.application.convert;

import com.spongzi.auth.application.dto.AuthPermissionDTO;
import com.spongzi.auth.domain.entity.AuthPermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 身份验证用户转换
 *
 * @author spong
 * @date 2023/11/03
 */
@Mapper
public interface AuthPermissionConvert {
    AuthPermissionConvert INSTANCE = Mappers.getMapper(AuthPermissionConvert.class);

    /**
     * 将dto转换为bo
     *
     * @param authPermissionDTO 身份验证权限数据到
     * @return {@link AuthPermissionBO}
     */
    AuthPermissionBO convertDtoToBo(AuthPermissionDTO authPermissionDTO);
}
