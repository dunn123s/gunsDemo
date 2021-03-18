package com.xsl.crm.common.dto.page;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.roses.core.util.HttpContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
//import com.baomidou.mybatisplus.core.metadata.OrderItem;

public class PageUtils {
	
	public static final int IF_NULL_PAGE = LayuiPageFactory.IF_NULL_PAGE;
	public static final int IF_NULL_LIMIT = LayuiPageFactory.IF_NULL_LIMIT;
	
	/**
	 * 获取layui table的分页参数
	 *
	 * @author fengshuonan
	 * @Date 2019/1/25 22:13
	 */
	public static <T> PageEntry<T> of() {
		HttpServletRequest request = HttpContext.getRequest();

		//每页多少条数据
        int limit = NumberUtils.toInt(request.getParameter("limit"), IF_NULL_LIMIT);

        //第几页
        int page = NumberUtils.toInt(request.getParameter("page"), IF_NULL_PAGE);

		return new PageEntry<T>(page, limit);
	}

	public static <T> PageEntry<T> of(PageQmd query) {
		// 每页多少条数据
		Integer limit = query.getLimit();
		if (limit == null)
			limit = IF_NULL_LIMIT;
		// 第几页
		Integer page = query.getPage();
		if (page == null)
			page = IF_NULL_PAGE;

		PageEntry<T> entry = new PageEntry<T>(page, limit);

		String field = query.getField();
		String order = query.getOrder();

		if (StringUtils.isNotEmpty("field")) {
			String column = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert(field);
			boolean desc = StringUtils.equalsIgnoreCase("desc", order);
//			OrderItem item = new OrderItem();
//			item.setColumn(column);
//			item.setAsc(asc);
//			entry.addOrder(item);
			if(desc)
			entry.setDesc(field);
			else
				entry.setAsc(field);
		}
		

		return entry;
	}

	/**
	 * 创建layui能识别的分页响应参数
	 *
	 * @author fengshuonan
	 * @Date 2019/1/25 22:14
	 */
	public static <T> Page<T> of(IPage<T> page) {
		Page<T> result = new Page<T>();
		result.setCount(page.getTotal());
		result.setData(page.getRecords());
		result.setCurrent(page.getCurrent());
		result.setSize(page.getSize());
		return result;
	}


}
