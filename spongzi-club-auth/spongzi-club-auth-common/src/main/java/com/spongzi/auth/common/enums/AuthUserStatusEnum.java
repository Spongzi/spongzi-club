package com.spongzi.auth.common.enums;

import lombok.Getter;

/**
 * 身份验证用户状态枚举
 *
 * @author spong
 * @date 2023/10/07
 */
@Getter
public enum AuthUserStatusEnum {

    OPEN(0,"启用"),
    CLOSE(1,"禁用");

    private int code;

    private String desc;

    AuthUserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthUserStatusEnum getByCode(int codeVal) {
        for (AuthUserStatusEnum authUserStatusEnum : AuthUserStatusEnum.values()) {
            if (authUserStatusEnum.code == codeVal) {
                return authUserStatusEnum;
            }
        }
        return null;
    }
}
