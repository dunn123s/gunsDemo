package com.xsl.crm.line.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.xsl.crm.line.entity.Line;
import com.xsl.crm.line.model.params.LineParam;
import com.xsl.crm.line.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 客户线路表控制器
 *
 * @author dunn
 * @Date 2021-03-16 22:13:05
 */
@Controller
@RequestMapping("/line")
public class LineController extends BaseController {

    private String PREFIX = "/line";

    @Autowired
    private LineService lineService;

    /**
     * 跳转到主页面
     *
     * @author dunn
     * @Date 2021-03-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/line.html";
    }

    /**
     * 新增页面
     *
     * @author dunn
     * @Date 2021-03-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/line_add.html";
    }

    /**
     * 编辑页面
     *
     * @author dunn
     * @Date 2021-03-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/line_edit.html";
    }

    /**
     * 新增接口
     *
     * @author dunn
     * @Date 2021-03-16
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(LineParam lineParam) {
        this.lineService.add(lineParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author dunn
     * @Date 2021-03-16
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(LineParam lineParam) {
        this.lineService.update(lineParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author dunn
     * @Date 2021-03-16
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(LineParam lineParam) {
        this.lineService.delete(lineParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author dunn
     * @Date 2021-03-16
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(LineParam lineParam) {
        Line detail = this.lineService.getById(lineParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author dunn
     * @Date 2021-03-16
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(LineParam lineParam) {
        return this.lineService.findPageBySpec(lineParam);
    }

}


