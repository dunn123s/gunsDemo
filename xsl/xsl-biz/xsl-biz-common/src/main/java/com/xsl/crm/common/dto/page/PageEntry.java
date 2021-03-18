package com.xsl.crm.common.dto.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageEntry<T> extends Page<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1872876171749018441L;

	public PageEntry() {
		super();
	}

	public PageEntry(long current, long size, boolean isSearchCount) {
		super(current, size, isSearchCount);
	}

	public PageEntry(long current, long size, long total, boolean isSearchCount) {
		super(current, size, total, isSearchCount);
	}

	public PageEntry(long current, long size, long total) {
		super(current, size, total);
	}

	public PageEntry(long current, long size) {
		super(current, size);
	}
	
	

}
