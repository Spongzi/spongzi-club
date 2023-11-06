package com.spongzi.auth.application.convert;

import com.spongzi.auth.application.dto.AuthPermissionDTO;
import com.spongzi.auth.domain.entity.AuthPermissionBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T09:50:53+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class AuthPermissionConvertImpl implements AuthPermissionConvert {

    @Override
    public AuthPermissionBO convertDtoToBo(AuthPermissionDTO authPermissionDTO) {
        if ( authPermissionDTO == null ) {
            return null;
        }

        AuthPermissionBO authPermissionBO = new AuthPermissionBO();

        authPermissionBO.setId( authPermissionDTO.getId() );
        authPermissionBO.setName( authPermissionDTO.getName() );
        authPermissionBO.setParentId( authPermissionDTO.getParentId() );
        authPermissionBO.setType( authPermissionDTO.getType() );
        authPermissionBO.setMenuUrl( authPermissionDTO.getMenuUrl() );
        authPermissionBO.setStatus( authPermissionDTO.getStatus() );
        authPermissionBO.setShow( authPermissionDTO.getShow() );
        authPermissionBO.setIcon( authPermissionDTO.getIcon() );
        authPermissionBO.setPermissionKey( authPermissionDTO.getPermissionKey() );

        return authPermissionBO;
    }
}
