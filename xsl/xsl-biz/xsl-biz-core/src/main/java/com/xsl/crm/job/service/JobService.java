package com.xsl.crm.job.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsl.crm.job.entity.Job;
import com.xsl.crm.job.model.params.JobParam;
import com.xsl.crm.job.model.result.JobResult;

import java.util.List;

/**
 * <p>
 * 定时任务调度表 服务类
 * </p>
 *
 * @author dunn
 * @since 2021-03-19
 */
public interface JobService extends IService<Job> {

    /**
     * 新增
     *
     * @author dunn
     * @Date 2021-03-19
     */
    void add(JobParam param);

    /**
     * 删除
     *
     * @author dunn
     * @Date 2021-03-19
     */
    void delete(JobParam param);

    /**
     * 更新
     *
     * @author dunn
     * @Date 2021-03-19
     */
    void update(JobParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author dunn
     * @Date 2021-03-19
     */
    JobResult findBySpec(JobParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author dunn
     * @Date 2021-03-19
     */
    List<JobResult> findListBySpec(JobParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author dunn
     * @Date 2021-03-19
     */
     LayuiPageInfo findPageBySpec(JobParam param);

     boolean freeze(Long jobId);

    boolean unfreeze(Long jobId);


}
