package com.xsl.crm.line.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsl.crm.line.entity.Line;
import com.xsl.crm.line.mapper.LineMapper;
import com.xsl.crm.line.model.params.LineParam;
import com.xsl.crm.line.model.result.LineResult;
import com.xsl.crm.line.service.LineService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 客户线路表 服务实现类
 * </p>
 *
 * @author dunn
 * @since 2021-03-16
 */
@Service
public class LineServiceImpl extends ServiceImpl<LineMapper, Line> implements LineService {

    @Override
    public void add(LineParam param){
        Line entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(LineParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(LineParam param){
        Line oldEntity = getOldEntity(param);
        Line newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public LineResult findBySpec(LineParam param){
        return null;
    }

    @Override
    public List<LineResult> findListBySpec(LineParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(LineParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(LineParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Line getOldEntity(LineParam param) {
        return this.getById(getKey(param));
    }

    private Line getEntity(LineParam param) {
        Line entity = new Line();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
