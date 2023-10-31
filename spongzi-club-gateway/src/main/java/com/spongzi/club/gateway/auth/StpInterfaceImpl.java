package com.spongzi.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author spong
 * @date 2023/10/31
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        // 1. 直接跟数据库交互
        // 2. 可以去redis缓存
        // 3. 先从缓存中去拿，没有的话可以去调用微服务去拿
        List<String> permissionList = new LinkedList<>();
        permissionList.add("user:add");
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        List<String> roleList = new LinkedList<>();
        roleList.add("admin");
        return roleList;
    }

}
