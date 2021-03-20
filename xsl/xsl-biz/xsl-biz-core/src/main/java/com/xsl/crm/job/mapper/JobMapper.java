package com.xsl.crm.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsl.crm.job.entity.Job;
import com.xsl.crm.job.model.params.JobParam;
import com.xsl.crm.job.model.result.JobResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务调度表 Mapper 接口
 * </p>
 *
 * @author dunn
 * @since 2021-03-19
 */
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 获取列表
     *
     * @author dunn
     * @Date 2021-03-19
     */
    List<JobResult> customList(@Param("paramCondition") JobParam paramCondition);

    /**
     * 获取map列表
     *
     * @author dunn
     * @Date 2021-03-19
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") JobParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author dunn
     * @Date 2021-03-19
     */
    Page<JobResult> customPageList(@Param("page") Page page, @Param("paramCondition") JobParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author dunn
     * @Date 2021-03-19
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") JobParam paramCondition);

    public Job selectJobById(Long jobId);

}
