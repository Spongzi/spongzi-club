package com.spongzi.auth.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 身份验证角色dto
 *
 * @author spong
 * @date 2023/11/03
 */
@Data
public class AuthRoleDTO implements Serializable {

    private Long id;
    
    private String roleName;
    
    private String roleKey;

}

