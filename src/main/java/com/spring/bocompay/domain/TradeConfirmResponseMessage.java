package com.spring.bocompay.domain;

/**
 * @description: 订单交付 响应报文
 * @author: Katherine
 * @create: 2018/4/26 16:02
 */
public class TradeConfirmResponseMessage {
    //head
    private String RspType;//响应类型
    private String RspCode;//交易返回码
    private String RspMsg;//交易返回信息
    private String RspDate;//响应日期
    private String RspTime;//响应时间
    //body
    private String TranRspCode;//交易处理码
    private String TranStt;//交易状态
    private String MerOrderNo;//一级商户（外部）订单号
    private String ConfirmOrder;//交付单据号
    private String ConfirmAmt;//交付金额
    private String ConfirmCry;//交付币种
    private String MerTranSerialNo;//商户流水号

    public TradeConfirmResponseMessage() {
    }

    public TradeConfirmResponseMessage(String rspType, String rspCode, String rspMsg, String rspDate, String rspTime) {
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

    public String getTranRspCode() {
        return TranRspCode;
    }

    public void setTranRspCode(String tranRspCode) {
        TranRspCode = tranRspCode;
    }

    public String getTranStt() {
        return TranStt;
    }

    public void setTranStt(String tranStt) {
        TranStt = tranStt;
    }

    public String getMerOrderNo() {
        return MerOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        MerOrderNo = merOrderNo;
    }

    public String getConfirmOrder() {
        return ConfirmOrder;
    }

    public void setConfirmOrder(String confirmOrder) {
        ConfirmOrder = confirmOrder;
    }

    public String getConfirmAmt() {
        return ConfirmAmt;
    }

    public void setConfirmAmt(String confirmAmt) {
        ConfirmAmt = confirmAmt;
    }

    public String getConfirmCry() {
        return ConfirmCry;
    }

    public void setConfirmCry(String confirmCry) {
        ConfirmCry = confirmCry;
    }

    public String getMerTranSerialNo() {
        return MerTranSerialNo;
    }

    public void setMerTranSerialNo(String merTranSerialNo) {
        MerTranSerialNo = merTranSerialNo;
    }

    @Override
    public String toString() {
        return "TradeConfirmResponseMessage{" +
                "RspType='" + RspType + '\'' +
                ", RspCode='" + RspCode + '\'' +
                ", RspMsg='" + RspMsg + '\'' +
                ", RspDate='" + RspDate + '\'' +
                ", RspTime='" + RspTime + '\'' +
                ", TranRspCode='" + TranRspCode + '\'' +
                ", TranStt='" + TranStt + '\'' +
                ", MerOrderNo='" + MerOrderNo + '\'' +
                ", ConfirmOrder='" + ConfirmOrder + '\'' +
                ", ConfirmAmt='" + ConfirmAmt + '\'' +
                ", ConfirmCry='" + ConfirmCry + '\'' +
                ", MerTranSerialNo='" + MerTranSerialNo + '\'' +
                '}';
    }
}
