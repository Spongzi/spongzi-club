package com.spongzi.auth.domain.service.impl;

import com.spongzi.auth.domain.convert.AuthRoleConvert;
import com.spongzi.auth.domain.entity.AuthRoleBO;
import com.spongzi.auth.domain.service.AuthRoleDomainService;
import com.spongzi.auth.infra.basic.entity.AuthRole;
import com.spongzi.auth.infra.basic.service.AuthRoleService;
import com.spongzi.club.common.enums.IsDeletedEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 身份验证角色域服务实施
 *
 * @author spong
 * @date 2023/11/03
 */
@Service
@Slf4j
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleConvert.INSTANCE.convertBoToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        int count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleConvert.INSTANCE.convertBoToEntity(authRoleBO);
        int count = authRoleService.update(authRole);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authRoleBO.getId());
        authRole.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        int count = authRoleService.update(authRole);
        return count > 0;
    }

}
