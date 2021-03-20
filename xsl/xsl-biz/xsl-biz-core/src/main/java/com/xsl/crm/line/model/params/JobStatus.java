package com.xsl.crm.line.model.params;

public enum JobStatus {
    ENABLE("0","正常"),DISABLE("1","暂停");
    private String code;
    private String message;
    JobStatus(String code,String message){
        this.code = code;
        this.message = message;
    }
    public String getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
