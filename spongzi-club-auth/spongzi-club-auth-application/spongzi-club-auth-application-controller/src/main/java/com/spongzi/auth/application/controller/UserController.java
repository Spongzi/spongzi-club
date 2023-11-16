package com.spongzi.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.spongzi.auth.application.convert.AuthUserConvert;
import com.spongzi.auth.application.dto.AuthUserDTO;
import com.spongzi.auth.domain.entity.AuthUserBO;
import com.spongzi.auth.domain.service.AuthUserDomainService;
import com.spongzi.club.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    /**
     * 注冊用户
     *
     * @param authUserDTO 身份验证用户数据到
     * @return {@link Result}<{@link SaResult}>
     */
    @PostMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.dto: {}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);

            AuthUserBO authUserBO = AuthUserConvert.INSTANCE.convertDtoToBo(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.error: {}", e.getMessage());
            return Result.fail("注册用户失败");
        }
    }


    /**
     * 修改用户信息
     *
     * @param authUserDTO 身份验证用户数据到
     * @return {@link Result}<{@link Boolean}>
     */
    @PostMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserConvert.INSTANCE.convertDtoToBo(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 删除
     *
     * @param authUserDTO 身份验证用户数据到
     * @return {@link Result}<{@link Boolean}>
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserConvert.INSTANCE.convertDtoToBo(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("删除用户信息失败");
        }
    }

    /**
     * 用户的启用和禁用
     *
     * @param authUserDTO 身份验证用户数据到
     * @return {@link Result}<{@link Boolean}>
     */
    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            AuthUserBO authUserBO = AuthUserConvert.INSTANCE.convertDtoToBo(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.changeStatus.error:{}", e.getMessage(), e);
            return Result.fail("启用/禁用用户信息失败");
        }
    }

    @PostMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "验证码不能为空");
            return Result.ok(authUserDomainService.doLogin(validCode));
        } catch (Exception e) {
            log.error("UserController.doLogin.error:{}", e.getMessage(), e);
            return Result.fail("用户登录失败");
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:3011/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    /**
     * 检查用户信息
     *
     * @param authUserDTO 身份验证用户数据到
     */
    private static void checkUserInfo(AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "用户邮箱不能为空");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "用户密码不能为空");
    }
}
