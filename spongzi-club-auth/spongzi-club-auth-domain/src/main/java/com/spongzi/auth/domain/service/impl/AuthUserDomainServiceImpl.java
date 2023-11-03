package com.spongzi.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.spongzi.auth.common.enums.AuthUserStatusEnum;
import com.spongzi.auth.domain.constants.AuthConstant;
import com.spongzi.auth.domain.convert.AuthUserConvert;
import com.spongzi.auth.domain.entity.AuthUserBO;
import com.spongzi.auth.domain.service.AuthUserDomainService;
import com.spongzi.auth.infra.basic.entity.AuthRole;
import com.spongzi.auth.infra.basic.entity.AuthUser;
import com.spongzi.auth.infra.basic.entity.AuthUserRole;
import com.spongzi.auth.infra.basic.service.AuthRoleService;
import com.spongzi.auth.infra.basic.service.AuthUserRoleService;
import com.spongzi.auth.infra.basic.service.AuthUserService;
import com.spongzi.club.common.enums.IsDeletedEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 身份验证用户域服务实施
 *
 * @author spong
 * @date 2023/11/03
 */
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthRoleService authRoleService;

    private final String salt = "spongzi";

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
