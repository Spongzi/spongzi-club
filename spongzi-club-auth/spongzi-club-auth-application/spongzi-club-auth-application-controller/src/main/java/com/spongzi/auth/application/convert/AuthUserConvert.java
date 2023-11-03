package com.spongzi.auth.application.convert;

import com.spongzi.auth.application.dto.AuthUserDTO;
import com.spongzi.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 身份验证用户转换
 *
 * @author spong
 * @date 2023/11/03
 */
@Mapper
public interface AuthUserConvert {
    AuthUserConvert INSTANCE = Mappers.getMapper(AuthUserConvert.class);

    /**
     * 将dto转换为bo
     *
     * @param authUserDTO 身份验证用户数据到
     * @return {@link AuthUserBO}
     */
    AuthUserBO convertDtoToBo(AuthUserDTO authUserDTO);
}
