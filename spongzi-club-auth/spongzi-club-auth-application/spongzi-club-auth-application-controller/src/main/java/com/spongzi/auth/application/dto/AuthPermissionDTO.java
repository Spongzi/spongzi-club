package com.spongzi.auth.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 身份验证权限数据到
 * 权限dto
 *
 * @author spong
 * @date 2023/11/03
 */
@Data
public class AuthPermissionDTO implements Serializable {

    private Long id;
    
    private String name;
    
    private Long parentId;
    
    private Integer type;
    
    private String menuUrl;
    
    private Integer status;
    
    private Integer show;
    
    private String icon;
    
    private String permissionKey;
}

