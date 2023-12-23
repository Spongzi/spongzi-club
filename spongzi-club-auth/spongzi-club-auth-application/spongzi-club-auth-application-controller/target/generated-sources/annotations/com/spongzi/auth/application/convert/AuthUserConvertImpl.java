package com.spongzi.auth.application.convert;

import com.spongzi.auth.domain.entity.AuthUserBO;
import com.spongzi.auth.entity.AuthUserDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-05T14:32:21+0800",
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

    @Override
    public AuthUserDTO convertBoToDto(AuthUserBO authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        AuthUserDTO authUserDTO = new AuthUserDTO();

        authUserDTO.setId( authUserBO.getId() );
        authUserDTO.setUserName( authUserBO.getUserName() );
        authUserDTO.setNickName( authUserBO.getNickName() );
        authUserDTO.setEmail( authUserBO.getEmail() );
        authUserDTO.setPhone( authUserBO.getPhone() );
        authUserDTO.setPassword( authUserBO.getPassword() );
        authUserDTO.setSex( authUserBO.getSex() );
        authUserDTO.setAvatar( authUserBO.getAvatar() );
        authUserDTO.setStatus( authUserBO.getStatus() );
        authUserDTO.setIntroduce( authUserBO.getIntroduce() );
        authUserDTO.setExtJson( authUserBO.getExtJson() );
        authUserDTO.setCreatedBy( authUserBO.getCreatedBy() );
        authUserDTO.setCreatedTime( authUserBO.getCreatedTime() );
        authUserDTO.setUpdateBy( authUserBO.getUpdateBy() );
        authUserDTO.setUpdateTime( authUserBO.getUpdateTime() );
        authUserDTO.setIsDeleted( authUserBO.getIsDeleted() );

        return authUserDTO;
    }
}
