package com.spongzi.auth.api;

import com.spongzi.auth.entity.AuthUserDTO;
import com.spongzi.auth.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户Feign服务
 *
 * @author spong
 * @date 2023/12/05
 */
@FeignClient("spongzi-club-auth")
public interface UserFeignService {

    @PostMapping("/user/getUserInfo")
    Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);

}
