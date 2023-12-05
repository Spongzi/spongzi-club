package com.spongzi.subject.infra.rpc;

import com.spongzi.auth.api.UserFeignService;
import com.spongzi.auth.entity.AuthUserDTO;
import com.spongzi.auth.entity.Result;
import com.spongzi.subject.infra.entity.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户RPC
 *
 * @author spong
 * @date 2023/12/05
 */
@Component
public class UserRpc {

    @Resource
    private UserFeignService userFeignService;

    public UserInfo getUserInfo(String username) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(username);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if (!result.getSuccess()) {
            return userInfo;
        }
        AuthUserDTO data = result.getData();
        userInfo.setUserName(data.getUserName());
        userInfo.setNickName(data.getNickName());
        return userInfo;
    }

}
