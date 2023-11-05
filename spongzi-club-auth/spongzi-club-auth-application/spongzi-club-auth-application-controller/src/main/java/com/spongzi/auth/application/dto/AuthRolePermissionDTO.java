package com.spongzi.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 身份验证角色权限dto
 *
 * @author spongzi
 * @date 2023/11/05
 * @since 2023-11-05 20:33:26
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = 691293362492371353L;

    private Long id;

    private Long roleId;

    private Long permissionId;

    private List<Long> permissionIdList;
}

