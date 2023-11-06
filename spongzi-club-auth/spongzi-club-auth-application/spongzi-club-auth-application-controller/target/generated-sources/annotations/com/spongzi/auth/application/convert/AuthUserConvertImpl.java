package com.spongzi.auth.application.convert;

import com.spongzi.auth.application.dto.AuthUserDTO;
import com.spongzi.auth.domain.entity.AuthUserBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T09:50:53+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_381 (Oracle Corporation)"
)
public class AuthUserConvertImpl implements AuthUserConvert {

    @Override
    public AuthUserBO convertDtoToBo(AuthUserDTO authUserDTO) {
        if ( authUserDTO == null ) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();

        authUserBO.setId( authUserDTO.getId() );
        authUserBO.setUserName( authUserDTO.getUserName() );
        authUserBO.setNickName( authUserDTO.getNickName() );
        authUserBO.setEmail( authUserDTO.getEmail() );
        authUserBO.setPhone( authUserDTO.getPhone() );
        authUserBO.setPassword( authUserDTO.getPassword() );
        authUserBO.setSex( authUserDTO.getSex() );
        authUserBO.setAvatar( authUserDTO.getAvatar() );
        authUserBO.setStatus( authUserDTO.getStatus() );
        authUserBO.setIntroduce( authUserDTO.getIntroduce() );
        authUserBO.setExtJson( authUserDTO.getExtJson() );
        authUserBO.setCreatedBy( authUserDTO.getCreatedBy() );
        authUserBO.setCreatedTime( authUserDTO.getCreatedTime() );
        authUserBO.setUpdateBy( authUserDTO.getUpdateBy() );
        authUserBO.setUpdateTime( authUserDTO.getUpdateTime() );
        authUserBO.setIsDeleted( authUserDTO.getIsDeleted() );

        return authUserBO;
    }
}
