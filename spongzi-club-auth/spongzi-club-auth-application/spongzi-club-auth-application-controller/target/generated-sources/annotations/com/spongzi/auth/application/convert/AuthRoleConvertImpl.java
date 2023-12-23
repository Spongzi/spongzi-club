package com.spongzi.auth.application.convert;

import com.spongzi.auth.application.dto.AuthRoleDTO;
import com.spongzi.auth.domain.entity.AuthRoleBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T14:32:21+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class AuthRoleConvertImpl implements AuthRoleConvert {

    @Override
    public AuthRoleBO convertDtoToBo(AuthRoleDTO authRoleDTO) {
        if ( authRoleDTO == null ) {
            return null;
        }

        AuthRoleBO authRoleBO = new AuthRoleBO();

        return authRoleBO;
    }
}
