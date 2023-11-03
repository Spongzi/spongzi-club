package com.spongzi.auth.domain.convert;

import com.spongzi.auth.domain.entity.AuthUserBO;
import com.spongzi.auth.infra.basic.entity.AuthUser;
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
     * 将bo转换为实体
     *
     * @param authUserBO 身份验证用户bo
     * @return {@link AuthUser}
     */
    AuthUser convertBoToEntity(AuthUserBO authUserBO);
}
