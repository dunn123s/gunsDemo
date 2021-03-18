package com.xsl.crm.common.dto.page;

import lombok.Data;

@Data
public abstract class PageQmd {
	/**
	 * 第几页
	 */
	private Integer page;
	/**
	 * 每页记录数
	 */
	private Integer limit;
	
	/**
	 * 排序字段
	 */
	private String field;
	
	/**
	 * asc, desc
	 */
	private String order;
}
