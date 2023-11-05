package com.spongzi.auth.domain.convert;

import com.spongzi.auth.domain.entity.AuthRolePermissionBO;
import com.spongzi.auth.infra.basic.entity.AuthRolePermission;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-05T21:47:55+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class AuthRolePermissionConvertImpl implements AuthRolePermissionConvert {

    @Override
    public AuthRolePermission convertBoToEntity(AuthRolePermissionBO authRolePermissionBO) {
        if ( authRolePermissionBO == null ) {
            return null;
        }

        AuthRolePermission authRolePermission = new AuthRolePermission();

        authRolePermission.setId( authRolePermissionBO.getId() );
        authRolePermission.setRoleId( authRolePermissionBO.getRoleId() );
        authRolePermission.setPermissionId( authRolePermissionBO.getPermissionId() );

        return authRolePermission;
    }
}
