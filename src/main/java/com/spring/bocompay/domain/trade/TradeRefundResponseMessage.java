package com.spring.bocompay.domain.trade;

/**
 * @description: 订单退款 响应报文
 * @author: Katherine
 * @create: 2018/4/26 15:28
 */
public class TradeRefundResponseMessage {
    //head
    private String RspType;//响应类型
    private String RspCode;//交易返回码
    private String RspMsg;//交易返回信息
    private String RspDate;//响应日期
    private String RspTime;//响应时间
    //body
    private String TranRspCode;//交易处理码
    private String TranRspMsg;//交易处理描述
    private String TranStt;//交易状态
    private String MerOrderNo;//一级商户（外部）订单号
    private String RefundOrderNo;//退款单据号
    private String RefundAmt;//退款金额
    private String RefundCry;//退款币种
    private String MerTranSerialNo;//商户流水号
    private String ChannelType;//支付类型

    public TradeRefundResponseMessage() {
    }

    public TradeRefundResponseMessage(String rspType, String rspCode, String rspMsg, String rspDate, String rspTime) {
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

    public String getTranRspMsg() {
        return TranRspMsg;
    }

    public void setTranRspMsg(String tranRspMsg) {
        TranRspMsg = tranRspMsg;
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

    public String getRefundOrderNo() {
        return RefundOrderNo;
    }

    public void setRefundOrderNo(String refundOrderNo) {
        RefundOrderNo = refundOrderNo;
    }

    public String getRefundAmt() {
        return RefundAmt;
    }

    public void setRefundAmt(String refundAmt) {
        RefundAmt = refundAmt;
    }

    public String getRefundCry() {
        return RefundCry;
    }

    public void setRefundCry(String refundCry) {
        RefundCry = refundCry;
    }

    public String getMerTranSerialNo() {
        return MerTranSerialNo;
    }

    public void setMerTranSerialNo(String merTranSerialNo) {
        MerTranSerialNo = merTranSerialNo;
    }

    public String getChannelType() {
        return ChannelType;
    }

    public void setChannelType(String channelType) {
        ChannelType = channelType;
    }

    @Override
    public String toString() {
        return "TradeRefundResponseMessage{" +
                "RspType='" + RspType + '\'' +
                ", RspCode='" + RspCode + '\'' +
                ", RspMsg='" + RspMsg + '\'' +
                ", RspDate='" + RspDate + '\'' +
                ", RspTime='" + RspTime + '\'' +
                ", TranRspCode='" + TranRspCode + '\'' +
                ", TranRspMsg='" + TranRspMsg + '\'' +
                ", TranStt='" + TranStt + '\'' +
                ", MerOrderNo='" + MerOrderNo + '\'' +
                ", RefundOrderNo='" + RefundOrderNo + '\'' +
                ", RefundAmt='" + RefundAmt + '\'' +
                ", RefundCry='" + RefundCry + '\'' +
                ", MerTranSerialNo='" + MerTranSerialNo + '\'' +
                ", ChannelType='" + ChannelType + '\'' +
                '}';
    }
}
















