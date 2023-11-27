package com.spongzi.auth.domain.service.impl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.spongzi.auth.domain.convert.AuthPermissionConvert;
import com.spongzi.auth.domain.entity.AuthPermissionBO;
import com.spongzi.auth.domain.service.AuthPermissionDomainService;
import com.spongzi.auth.infra.basic.entity.AuthPermission;
import com.spongzi.auth.infra.basic.service.AuthPermissionService;
import com.spongzi.club.common.enums.IsDeletedEnum;
import com.spongzi.club.common.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private RedisUtil redisUtil;

    private final String AUTH_PERMISSION_PREFIX = "auth.permission";

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

    @Override
    public List<String> getPermission(String username) {
        String permissionKey = redisUtil.buildKey(AUTH_PERMISSION_PREFIX, username);
        String permissionValue = redisUtil.get(permissionKey);
        if (StringUtils.isBlank(permissionValue)) {
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue,
                new TypeToken<List<AuthPermission>>() {
                }
                        .getType());
        return permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
    }

}
