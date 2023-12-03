package com.spongzi.subject.application.util;

import com.spongzi.subject.application.context.LoginContextHolder;

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
