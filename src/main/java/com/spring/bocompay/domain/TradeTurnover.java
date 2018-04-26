package com.spring.bocompay.domain;

/**
 * Created by Elena@qq.com on 2018/4/25
 * Description: 订单明细查询时，返回的订单下所有的付款、退款、交付流水信息
 */
public class TradeTurnover {
    private String PayNo;//银行交易流水号
    private String MerTranSerialNo;//商户流水号
    private String PayDate;//流水时间
    private String PayType;//流水类型
    private String PayAmt;//流水金额
    private String PayState;//流水状态
    private String PayMemo;//商户流水备注
    private String PayBankMemo;//银行流水备注
    private String ChannelType;//支付类型
    private String PayBankName;//所属银行名称

    public TradeTurnover() {
    }

    public String getPayNo() {
        return PayNo;
    }

    public void setPayNo(String payNo) {
        PayNo = payNo;
    }

    public String getMerTranSerialNo() {
        return MerTranSerialNo;
    }

    public void setMerTranSerialNo(String merTranSerialNo) {
        MerTranSerialNo = merTranSerialNo;
    }

    public String getPayDate() {
        return PayDate;
    }

    public void setPayDate(String payDate) {
        PayDate = payDate;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    public String getPayAmt() {
        return PayAmt;
    }

    public void setPayAmt(String payAmt) {
        PayAmt = payAmt;
    }

    public String getPayState() {
        return PayState;
    }

    public void setPayState(String payState) {
        PayState = payState;
    }

    public String getPayMemo() {
        return PayMemo;
    }

    public void setPayMemo(String payMemo) {
        PayMemo = payMemo;
    }

    public String getPayBankMemo() {
        return PayBankMemo;
    }

    public void setPayBankMemo(String payBankMemo) {
        PayBankMemo = payBankMemo;
    }

    public String getChannelType() {
        return ChannelType;
    }

    public void setChannelType(String channelType) {
        ChannelType = channelType;
    }

    public String getPayBankName() {
        return PayBankName;
    }

    public void setPayBankName(String payBankName) {
        PayBankName = payBankName;
    }

    @Override
    public String toString() {
        return "TradeTurnover{" +
                "PayNo='" + PayNo + '\'' +
                ", MerTranSerialNo='" + MerTranSerialNo + '\'' +
                ", PayDate='" + PayDate + '\'' +
                ", PayType='" + PayType + '\'' +
                ", PayAmt='" + PayAmt + '\'' +
                ", PayState='" + PayState + '\'' +
                ", PayMemo='" + PayMemo + '\'' +
                ", PayBankMemo='" + PayBankMemo + '\'' +
                ", ChannelType='" + ChannelType + '\'' +
                ", PayBankName='" + PayBankName + '\'' +
                '}';
    }
}
