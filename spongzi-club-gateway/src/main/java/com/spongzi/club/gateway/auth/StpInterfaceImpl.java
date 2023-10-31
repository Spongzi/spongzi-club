package com.spongzi.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.spongzi.club.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author spong
 * @date 2023/10/31
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;

    private final String AUTH_PERMISSION_PREFIX = "auth.permission";

    private final String AUTH_ROLE_PREFIX = "auth.role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        // 1. 直接跟数据库交互
        // 2. 可以去redis缓存
        // 3. 先从缓存中去拿，没有的话可以去调用微服务去拿
        return getStrings(AUTH_PERMISSION_PREFIX, loginId);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        return getStrings(AUTH_ROLE_PREFIX, loginId);
    }

    /**
     * 根据身份得到角色权限列表
     *
     * @param authRolePrefix 身份验证角色前缀
     * @param loginId        登录ID
     * @return {@link List}<{@link String}>
     */
    private List<String> getStrings(String authRolePrefix, Object loginId) {
        String authKey = redisUtil.buildKey(authRolePrefix, loginId.toString());
        String authValue = redisUtil.get(authKey);
        if (StringUtils.isNotBlank(authValue)) {
            return Collections.emptyList();
        }
        return new Gson().fromJson(authValue, new TypeToken<ArrayList<String>>(){}.getType());
    }

}
