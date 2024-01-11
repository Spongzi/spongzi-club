package com.spongzi.club.common.util;


import com.spongzi.club.common.context.LoginContextHolder;

/**
 * 登录Util
 *
 * @author spong
 * @date 2023/12/03
 */
public class LoginUtil {

    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }
}
