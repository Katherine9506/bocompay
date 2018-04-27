package com.spring.bocompay.domain.merchant;

import java.util.List;
import java.util.Map;

/**
 * @description: 二级商户签约查询 响应报文
 * @author: Katherine
 * @create: 2018/4/27 10:58
 */
public class SndMerSignDetailResponseMessage {
    //head
    private String RspType;//响应类型
    private String RspCode;//交易返回码
    private String RspMsg;//交易返回信息
    private String RspDate;//响应日期
    private String RspTime;//响应时间
    //body
    private String MerPtcInfoNum;//二级商户签约协议数量
    private Map<String, String> ParentMerBaseInfo;//一级商户基本信息
    //    ParentMerPtcId 一级商户协议号
    //    ParentEntNameCN 一级商户企业中文名称
    //    ParentCstNo 一级商户网银客户号
    //    ParentMerchNameCN 一级商户中文名称
    //    ParentPtcSta 一级商户协议状态
    private MerBaseInfo MerBaseInfo;//商户基本信息
    private MerBusInfo MerBusInfo;//商户业务信息
    private List<Map<String, Object>> MerPtcInfoList;//商户协议信息列表

    public SndMerSignDetailResponseMessage() {
    }

    public SndMerSignDetailResponseMessage(String rspType, String rspCode, String rspMsg, String rspDate, String rspTime) {
        RspType = rspType;
        RspCode = rspCode;
        RspMsg = rspMsg;
        RspDate = rspDate;
        RspTime = rspTime;
    }

    public String getRspType() {
        return RspType;
    }

    public void setRspType(String rspType) {
        RspType = rspType;
    }

    public String getRspCode() {
        return RspCode;
    }

    public void setRspCode(String rspCode) {
        RspCode = rspCode;
    }

    public String getRspMsg() {
        return RspMsg;
    }

    public void setRspMsg(String rspMsg) {
        RspMsg = rspMsg;
    }

    public String getRspDate() {
        return RspDate;
    }

    public void setRspDate(String rspDate) {
        RspDate = rspDate;
    }

    public String getRspTime() {
        return RspTime;
    }

    public void setRspTime(String rspTime) {
        RspTime = rspTime;
    }

    public String getMerPtcInfoNum() {
        return MerPtcInfoNum;
    }

    public void setMerPtcInfoNum(String merPtcInfoNum) {
        MerPtcInfoNum = merPtcInfoNum;
    }

    public Map<String, String> getParentMerBaseInfo() {
        return ParentMerBaseInfo;
    }

    public void setParentMerBaseInfo(Map<String, String> parentMerBaseInfo) {
        ParentMerBaseInfo = parentMerBaseInfo;
    }

    public com.spring.bocompay.domain.merchant.MerBaseInfo getMerBaseInfo() {
        return MerBaseInfo;
    }

    public void setMerBaseInfo(com.spring.bocompay.domain.merchant.MerBaseInfo merBaseInfo) {
        MerBaseInfo = merBaseInfo;
    }

    public com.spring.bocompay.domain.merchant.MerBusInfo getMerBusInfo() {
        return MerBusInfo;
    }

    public void setMerBusInfo(com.spring.bocompay.domain.merchant.MerBusInfo merBusInfo) {
        MerBusInfo = merBusInfo;
    }

    public List getMerPtcInfoList() {
        return MerPtcInfoList;
    }

    public void setMerPtcInfoList(List merPtcInfoList) {
        MerPtcInfoList = merPtcInfoList;
    }

    @Override
    public String toString() {
        return "SndMerSignDetailResponseMessage{" +
                "RspType='" + RspType + '\'' +
                ", RspCode='" + RspCode + '\'' +
                ", RspMsg='" + RspMsg + '\'' +
                ", RspDate='" + RspDate + '\'' +
                ", RspTime='" + RspTime + '\'' +
                ", MerPtcInfoNum='" + MerPtcInfoNum + '\'' +
                ", ParentMerBaseInfo=" + ParentMerBaseInfo +
                ", MerBaseInfo=" + MerBaseInfo +
                ", MerBusInfo=" + MerBusInfo +
                ", MerPtcInfoList=" + MerPtcInfoList +
                '}';
    }
}





















