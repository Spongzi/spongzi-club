package com.spongzi.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.google.gson.Gson;
import com.spongzi.auth.common.enums.AuthUserStatusEnum;
import com.spongzi.auth.domain.constants.AuthConstant;
import com.spongzi.auth.domain.convert.AuthUserConvert;
import com.spongzi.auth.domain.entity.AuthUserBO;
import com.spongzi.auth.domain.service.AuthUserDomainService;
import com.spongzi.auth.infra.basic.entity.*;
import com.spongzi.auth.infra.basic.service.*;
import com.spongzi.club.common.enums.IsDeletedEnum;
import com.spongzi.club.common.redis.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 身份验证用户域服务实施
 *
 * @author spong
 * @date 2023/11/03
 */
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    private final String salt = "spongzi";

    private final String AUTH_PERMISSION_PREFIX = "auth.permission";

    private final String AUTH_ROLE_PREFIX = "auth.role";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertBoToEntity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // 建立一个初步的角色关联，要把当前用户的角色和权限都刷到redis中
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);

        // 添加缓存
        String roleKey = redisUtil.buildKey(AUTH_ROLE_PREFIX, authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(roleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        List<AuthRolePermission> rolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);

        List<Long> permissionIdList = rolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());

        // 根据RoleId查权限
        List<AuthPermission> permissionList = authPermissionService.queryByRoleList(permissionIdList);
        String permissionKey = redisUtil.buildKey(AUTH_PERMISSION_PREFIX, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(permissionList));
        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertBoToEntity(authUserBO);
        Integer count = authUserService.update(authUser);
        //有任何的更新，都要与缓存进行同步的修改
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        //有任何的更新，都要与缓存进行同步的修改
        return count > 0;
    }
}
