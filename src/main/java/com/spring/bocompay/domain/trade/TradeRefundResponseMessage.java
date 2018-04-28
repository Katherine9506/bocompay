package com.spring.bocompay.domain.trade;

/**
 * @description: 订单退款 响应报文
 * @author: Katherine
 * @create: 2018/4/26 15:28
 */
public class TradeRefundResponseMessage {
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
                "TranRspCode='" + TranRspCode + '\'' +
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



















