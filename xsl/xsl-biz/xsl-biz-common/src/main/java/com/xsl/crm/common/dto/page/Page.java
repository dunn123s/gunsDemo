package com.xsl.crm.common.dto.page;

import lombok.Data;

import java.util.List;

/**
 * 分页结果的封装(for Layui Table)
 *
 */
@Data
public class Page<T> {

    private Integer code = 0;

    private String msg = "请求成功";

    private List<T> data;
    // 总记录数
    private long count;
    // 当前页，从1开始
    private long current = 1;
    // 每页记录数
    private long size;

}
