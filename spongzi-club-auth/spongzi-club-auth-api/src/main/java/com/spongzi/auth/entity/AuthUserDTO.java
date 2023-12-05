package com.spongzi.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AuthUser)DTO
 *
 * @author spongzi
 * @since 2023-11-01 01:25:22
 */
@Data
public class AuthUserDTO implements Serializable {
    private static final long serialVersionUID = -50435275569670090L;
    
    private Long id;
    
    private String userName;
    
    private String nickName;
    
    private String email;
    
    private String phone;
    
    private String password;
    
    private Integer sex;
    
    private String avatar;
    
    private Integer status;
    
    private String introduce;
    
    private String extJson;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 已删除
     */
    private Integer isDeleted;
}

