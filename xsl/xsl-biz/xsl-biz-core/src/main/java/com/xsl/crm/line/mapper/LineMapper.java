package com.xsl.crm.line.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsl.crm.line.entity.Line;
import com.xsl.crm.line.model.params.LineParam;
import com.xsl.crm.line.model.result.LineResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户线路表 Mapper 接口
 * </p>
 *
 * @author dunn
 * @since 2021-03-16
 */
public interface LineMapper extends BaseMapper<Line> {

    /**
     * 获取列表
     *
     * @author dunn
     * @Date 2021-03-16
     */
    List<LineResult> customList(@Param("paramCondition") LineParam paramCondition);

    /**
     * 获取map列表
     *
     * @author dunn
     * @Date 2021-03-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") LineParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author dunn
     * @Date 2021-03-16
     */
    Page<LineResult> customPageList(@Param("page") Page page, @Param("paramCondition") LineParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author dunn
     * @Date 2021-03-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") LineParam paramCondition);

}
