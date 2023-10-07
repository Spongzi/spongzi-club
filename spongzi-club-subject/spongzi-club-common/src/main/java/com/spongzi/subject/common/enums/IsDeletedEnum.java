package com.spongzi.subject.common.enums;

import lombok.Getter;

/**
 * 删除状态枚举
 *
 * @author spong
 * @date 2023/10/07
 */
@Getter
public enum IsDeletedEnum {

    DELETED(1,"已删除"),
    UN_DELETED(0,"未删除");

    private int code;

    private String desc;

    IsDeletedEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IsDeletedEnum getByCode(int codeVal) {
        for (IsDeletedEnum isDeletedEnum : IsDeletedEnum.values()) {
            if (isDeletedEnum.code == codeVal) {
                return isDeletedEnum;
            }
        }
        return null;
    }
}
