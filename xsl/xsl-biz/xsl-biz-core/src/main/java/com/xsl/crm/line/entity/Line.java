package com.xsl.crm.line.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 客户线路表
 * </p>
 *
 * @author dunn
 * @since 2021-03-16
 */
@TableName("t_line")
public class Line implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 线路
     */
    @TableField("line")
    private String line;

    /**
     * 系统平台
     */
    @TableField("sys_platform")
    private String sysPlatform;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getSysPlatform() {
        return sysPlatform;
    }

    public void setSysPlatform(String sysPlatform) {
        this.sysPlatform = sysPlatform;
    }

    @Override
    public String toString() {
        return "Line{" +
        "id=" + id +
        ", line=" + line +
        ", sysPlatform=" + sysPlatform +
        "}";
    }
}
