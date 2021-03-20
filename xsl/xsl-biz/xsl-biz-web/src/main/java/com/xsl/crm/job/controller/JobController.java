package com.xsl.crm.job.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.xsl.crm.job.entity.Job;
import com.xsl.crm.job.model.params.JobParam;
import com.xsl.crm.job.service.JobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 定时任务调度表控制器
 *
 * @author dunn
 * @Date 2021-03-19 15:22:20
 */
@Controller
@RequestMapping("/job")
public class JobController extends BaseController {

    private String PREFIX = "/job";

    @Autowired
    private JobService jobService;

    /**
     * 跳转到主页面
     *
     * @author dunn
     * @Date 2021-03-19
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/job.html";
    }

    /**
     * 新增页面
     *
     * @author dunn
     * @Date 2021-03-19
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/job_add.html";
    }

    /**
     * 编辑页面
     *
     * @author dunn
     * @Date 2021-03-19
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/job_edit.html";
    }

    /**
     * 新增接口
     *
     * @author dunn
     * @Date 2021-03-19
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(JobParam jobParam) {
        jobParam.setCreateBy(ShiroKit.getUserNotNull().getAccount());
        jobParam.setCreateTime(new Date());
        this.jobService.add(jobParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author dunn
     * @Date 2021-03-19
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(JobParam jobParam) {
        this.jobService.update(jobParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author dunn
     * @Date 2021-03-19
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(JobParam jobParam) {
        this.jobService.delete(jobParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author dunn
     * @Date 2021-03-19
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(JobParam jobParam) {
        Job detail = this.jobService.getById(jobParam.getJobId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author dunn
     * @Date 2021-03-19
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(JobParam jobParam) {
        return this.jobService.findPageBySpec(jobParam);
    }

    /**
     * 冻结任务状态
     *
     */
    @ResponseBody
    @RequestMapping("/freeze")
    public ResponseData freeze(@RequestParam Long jobId){
        boolean freeze = jobService.freeze(jobId);
        return ResponseData.success(freeze);
    }

    /**
     * 冻结任务状态
     *
     */
    @ResponseBody
    @RequestMapping("/unfreeze")
    public ResponseData unfreeze(@RequestParam Long jobId){
        boolean freeze = jobService.unfreeze(jobId);
        return ResponseData.success(freeze);
    }
    @ResponseBody
    @RequestMapping("/run")
    public ResponseData run(@RequestParam Long jobId) throws SchedulerException {
        return ResponseData.success();
    }

}


