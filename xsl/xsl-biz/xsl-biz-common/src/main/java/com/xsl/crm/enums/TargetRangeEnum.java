package com.xsl.crm.enums;

public enum TargetRangeEnum {
    DAY("DAY","日区间"),
    WEEK("WEEK","周区间"),
    MONTH("MONTH","月区间"),
    SEASON("SEASON","季区间"),
    YEAR("YEAR","年区间");
    private String code;
    private String name;

    TargetRangeEnum(String code, String name) {
        this.code = code;
        this.name = name;

    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

}
