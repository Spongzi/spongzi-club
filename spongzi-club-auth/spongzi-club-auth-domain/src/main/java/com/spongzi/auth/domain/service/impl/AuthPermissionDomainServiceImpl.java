package com.spongzi.auth.domain.service.impl;

import com.spongzi.auth.domain.convert.AuthPermissionConvert;
import com.spongzi.auth.domain.entity.AuthPermissionBO;
import com.spongzi.auth.domain.service.AuthPermissionDomainService;
import com.spongzi.auth.infra.basic.entity.AuthPermission;
import com.spongzi.auth.infra.basic.service.AuthPermissionService;
import com.spongzi.club.common.enums.IsDeletedEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionConvert.INSTANCE.convertBoToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        int count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionConvert.INSTANCE.convertBoToEntity(authPermissionBO);
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeletedEnum.DELETED.getCode());
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

}
