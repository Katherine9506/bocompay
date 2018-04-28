package com.spring.bocompay.domain.trade;

/**
 * @description: 订单交付 响应报文
 * @author: Katherine
 * @create: 2018/4/26 16:02
 */
public class TradeConfirmResponseMessage {
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
                "TranRspCode='" + TranRspCode + '\'' +
                ", TranStt='" + TranStt + '\'' +
                ", MerOrderNo='" + MerOrderNo + '\'' +
                ", ConfirmOrder='" + ConfirmOrder + '\'' +
                ", ConfirmAmt='" + ConfirmAmt + '\'' +
                ", ConfirmCry='" + ConfirmCry + '\'' +
                ", MerTranSerialNo='" + MerTranSerialNo + '\'' +
                '}';
    }
}

