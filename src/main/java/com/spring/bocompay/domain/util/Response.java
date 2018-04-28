package com.spring.bocompay.domain.util;

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
    private Map<String, Object> data;//响应数据

    private Map<String, String> headerMap;

    public Response() {
    }

    public Response(String rspType, String rspCode, String rspMsg, String rspDate, String rspTime) {
        RspType = rspType;
        RspCode = rspCode;
        RspMsg = rspMsg;
        RspDate = rspDate;
        RspTime = rspTime;
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
