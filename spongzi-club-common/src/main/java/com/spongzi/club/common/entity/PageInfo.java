package com.spongzi.club.common.entity;

import lombok.Data;

/**
 * 分页信息
 *
 * @author spong
 * @date 2023/10/09
 */
public class PageInfo {

    private Integer pageNo = 1;

    private Integer pageSize = 20;

    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1) {
            return 10;
        }
        return pageSize;
    }
}
