package com.spongzi.subject.infra.entity;

import lombok.Data;

/**
 * 用户信息
 *
 * @author spong
 * @date 2023/12/05
 */
@Data
public class UserInfo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

}
