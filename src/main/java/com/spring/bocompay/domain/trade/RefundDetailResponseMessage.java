package com.spring.bocompay.domain.trade;

import java.util.List;

/**
 * @description: 退款明细 响应报文
 * @author: Katherine
 * @create: 2018/4/26 11:07
 */
public class RefundDetailResponseMessage {
    //body
    private String MerPtcId;//一级商户协议号
    private String MerPtcName;//一级商户名称
    private String OpenBra;//所属分行
    private String MerOrderNo;//商户订单号
    private String GmtCreate;//订单创建时间
    private String GmtModify;//订单更新时间
    private String TradeOrderNo;//交易订单号
    private String PayNo;//银行交易流水号

    private List<RefundTurnover> SettingList;//退款流水

    public RefundDetailResponseMessage() {
    }


    public List<RefundTurnover> getSettingList() {
        return SettingList;
    }

    public void setSettingList(List<RefundTurnover> settingList) {
        SettingList = settingList;
    }

    public String getMerPtcId() {
        return MerPtcId;
    }

    public void setMerPtcId(String merPtcId) {
        MerPtcId = merPtcId;
    }

    public String getMerPtcName() {
        return MerPtcName;
    }

    public void setMerPtcName(String merPtcName) {
        MerPtcName = merPtcName;
    }

    public String getOpenBra() {
        return OpenBra;
    }

    public void setOpenBra(String openBra) {
        OpenBra = openBra;
    }

    public String getMerOrderNo() {
        return MerOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        MerOrderNo = merOrderNo;
    }

    public String getGmtCreate() {
        return GmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        GmtCreate = gmtCreate;
    }

    public String getGmtModify() {
        return GmtModify;
    }

    public void setGmtModify(String gmtModify) {
        GmtModify = gmtModify;
    }

    public String getTradeOrderNo() {
        return TradeOrderNo;
    }

    public void setTradeOrderNo(String tradeOrderNo) {
        TradeOrderNo = tradeOrderNo;
    }

    public String getPayNo() {
        return PayNo;
    }

    public void setPayNo(String payNo) {
        PayNo = payNo;
    }

    @Override
    public String toString() {
        return "RefundDetailResponseMessage{" +
                "MerPtcId='" + MerPtcId + '\'' +
                ", MerPtcName='" + MerPtcName + '\'' +
                ", OpenBra='" + OpenBra + '\'' +
                ", MerOrderNo='" + MerOrderNo + '\'' +
                ", GmtCreate='" + GmtCreate + '\'' +
                ", GmtModify='" + GmtModify + '\'' +
                ", TradeOrderNo='" + TradeOrderNo + '\'' +
                ", PayNo='" + PayNo + '\'' +
                ", SettingList=" + SettingList +
                '}';
    }
}
















