package com.spongzi.auth.application.convert;

import com.spongzi.auth.application.dto.AuthRolePermissionDTO;
import com.spongzi.auth.domain.entity.AuthRolePermissionBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T14:32:21+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class AuthRolePermissionConvertImpl implements AuthRolePermissionConvert {

    @Override
    public AuthRolePermissionBO convertDtoToBo(AuthRolePermissionDTO authRolePermissionDTO) {
        if ( authRolePermissionDTO == null ) {
            return null;
        }

        AuthRolePermissionBO authRolePermissionBO = new AuthRolePermissionBO();

        return authRolePermissionBO;
    }
}
