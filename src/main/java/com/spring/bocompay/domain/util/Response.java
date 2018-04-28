package com.spring.bocompay.domain.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 返回报文头部
 * @author: Katherine
 * @create: 2018/4/28 9:18
 */
public class Response {
    private String RspType;//响应类型
    private String RspCode;//交易返回码
    private String RspMsg;//交易返回信息
    private String RspDate;//响应日期
    private String RspTime;//响应时间
    private Map<String, Object> data = new HashMap<>();//响应数据

    private Map<String, String> headerMap;

    public Response() {
    }

    public Response(String rspType, String rspCode, String rspMsg, String rspDate, String rspTime) {
        RspType = rspType;
        RspCode = rspCode;
        RspMsg = rspMsg;
        RspDate = rspDate;
        RspTime = rspTime;
        this.headerMap = new HashMap<>();
        this.headerMap.put("RspType", this.RspType);
        this.headerMap.put("RspCode", this.RspCode);
        this.headerMap.put("RspMsg", this.RspMsg);
        this.headerMap.put("RspDate", this.RspDate);
        this.headerMap.put("RspTime", this.RspTime);
    }

    public Map<String, String> getHeader() {
        return headerMap;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
