package com.xsl.crm.job.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsl.crm.job.entity.Job;
import com.xsl.crm.job.mapper.JobMapper;
import com.xsl.crm.job.model.params.JobParam;
import com.xsl.crm.job.model.result.JobResult;
import com.xsl.crm.job.service.JobService;
import com.xsl.crm.line.model.params.JobStatus;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 定时任务调度表 服务实现类
 * </p>
 *
 * @author dunn
 * @since 2021-03-19
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {
    @Resource
    private  JobMapper jobMapper;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void add(JobParam param){
        Job entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(JobParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(JobParam param){
        Job oldEntity = getOldEntity(param);
        Job newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public JobResult findBySpec(JobParam param){
        return null;
    }

    @Override
    public List<JobResult> findListBySpec(JobParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(JobParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public boolean freeze(Long jobId) {
        Job job = new Job();
        job.setJobId(jobId);
        job.setStatus(JobStatus.DISABLE.getCode());
        job.setUpdateTime(new Date());
        job.setUpdateBy(ShiroKit.getUserNotNull().getAccount());
        int i = jobMapper.updateById(job);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean unfreeze(Long jobId) {
        Job job = new Job();
        job.setJobId(jobId);
        job.setStatus(JobStatus.ENABLE.getCode());
        job.setUpdateTime(new Date());
        job.setUpdateBy(ShiroKit.getUserNotNull().getAccount());
        int i = jobMapper.updateById(job);
        if(i>0){
            return true;
        }
        return false;
    }

    private Serializable getKey(JobParam param){
        return param.getJobId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Job getOldEntity(JobParam param) {
        return this.getById(getKey(param));
    }

    private Job getEntity(JobParam param) {
        Job entity = new Job();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
