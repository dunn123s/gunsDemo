package com.xsl.crm.line.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsl.crm.line.entity.Line;
import com.xsl.crm.line.model.params.LineParam;
import com.xsl.crm.line.model.result.LineResult;

import java.util.List;

/**
 * <p>
 * 客户线路表 服务类
 * </p>
 *
 * @author dunn
 * @since 2021-03-16
 */
public interface LineService extends IService<Line> {

    /**
     * 新增
     *
     * @author dunn
     * @Date 2021-03-16
     */
    void add(LineParam param);

    /**
     * 删除
     *
     * @author dunn
     * @Date 2021-03-16
     */
    void delete(LineParam param);

    /**
     * 更新
     *
     * @author dunn
     * @Date 2021-03-16
     */
    void update(LineParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author dunn
     * @Date 2021-03-16
     */
    LineResult findBySpec(LineParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author dunn
     * @Date 2021-03-16
     */
    List<LineResult> findListBySpec(LineParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author dunn
     * @Date 2021-03-16
     */
     LayuiPageInfo findPageBySpec(LineParam param);

}
