package com.spongzi.auth.domain.service.impl;

import com.spongzi.auth.common.enums.AuthUserStatusEnum;
import com.spongzi.auth.domain.convert.AuthUserConvert;
import com.spongzi.auth.domain.entity.AuthUserBO;
import com.spongzi.auth.domain.service.AuthUserDomainService;
import com.spongzi.auth.infra.basic.entity.AuthUser;
import com.spongzi.auth.infra.basic.service.AuthUserService;
import com.spongzi.club.common.enums.IsDeletedEnum;
import org.springframework.stereotype.Service;

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

    @Override
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserConvert.INSTANCE.convertBoToEntity(authUserBO);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // TODO 建立一个初步的角色关联，要把当前用户的角色和权限都刷到redis中
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
