package com.spongzi.auth.domain.convert;

import com.spongzi.auth.domain.entity.AuthPermissionBO;
import com.spongzi.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 身份验证权限转换
 *
 * @author spong
 * @date 2023/11/03
 */
@Mapper
public interface AuthPermissionConvert {
    AuthPermissionConvert INSTANCE = Mappers.getMapper(AuthPermissionConvert.class);

    /**
     * 将bo转换为实体
     *
     * @param authPermissionBO 身份验证权限范围
     * @return {@link AuthPermission}
     */
    AuthPermission convertBoToEntity(AuthPermissionBO authPermissionBO);
}
