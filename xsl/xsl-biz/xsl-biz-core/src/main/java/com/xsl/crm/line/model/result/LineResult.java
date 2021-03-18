package com.xsl.crm.line.model.result;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 客户线路表
 * </p>
 *
 * @author dunn
 * @since 2021-03-16
 */
@Data
public class LineResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Integer id;

    /**
     * 线路
     */
    private String line;

    /**
     * 系统平台
     */
    private String sysPlatform;

}
