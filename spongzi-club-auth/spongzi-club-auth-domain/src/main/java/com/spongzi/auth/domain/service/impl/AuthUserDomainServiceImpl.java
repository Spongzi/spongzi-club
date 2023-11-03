package com.spongzi.auth.domain.service.impl;

import com.spongzi.auth.domain.convert.AuthUserConvert;
import com.spongzi.auth.domain.entity.AuthUserBO;
import com.spongzi.auth.domain.service.AuthUserDomainService;
import com.spongzi.auth.infra.basic.entity.AuthUser;
import com.spongzi.auth.infra.basic.service.AuthUserService;
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
        Integer count = authUserService.insert(authUser);
        return count > 0;
    }
}
