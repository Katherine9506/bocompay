package com.spring.bocompay.domain;

/**
 * @description: 退款流水
 * @author: Katherine
 * @create: 2018/4/26 11:27
 */
public class RefundTurnover {
    private String MerTranSerialNo;//商户流水号
    private String WaterNo;//银行退款流水号
    private String RefundState;//退款状态
    private String ChannelApi;//通道接口编号
    private String TranAmt;//退款金额
    private String TranCry;//币种
    private String ReturnFee;//退还手续费
    private String RefundAccNo;//退款账号
    private String RefundAccName;//退款户名
    private String RecAccNo;//收款账户
    private String RecAccName;//收款户名
    private String RecBankName;//收款银行名称
    private String RecBranchId;//收款账户网点
    private String BatchNo;//批次号
    private String RefundMemo;//退款备注

    public RefundTurnover() {
    }

    public String getMerTranSerialNo() {
        return MerTranSerialNo;
    }

    public void setMerTranSerialNo(String merTranSerialNo) {
        MerTranSerialNo = merTranSerialNo;
    }

    public String getWaterNo() {
        return WaterNo;
    }

    public void setWaterNo(String waterNo) {
        WaterNo = waterNo;
    }

    public String getRefundState() {
        return RefundState;
    }

    public void setRefundState(String refundState) {
        RefundState = refundState;
    }

    public String getChannelApi() {
        return ChannelApi;
    }

    public void setChannelApi(String channelApi) {
        ChannelApi = channelApi;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getTranCry() {
        return TranCry;
    }

    public void setTranCry(String tranCry) {
        TranCry = tranCry;
    }

    public String getReturnFee() {
        return ReturnFee;
    }

    public void setReturnFee(String returnFee) {
        ReturnFee = returnFee;
    }

    public String getRefundAccNo() {
        return RefundAccNo;
    }

    public void setRefundAccNo(String refundAccNo) {
        RefundAccNo = refundAccNo;
    }

    public String getRefundAccName() {
        return RefundAccName;
    }

    public void setRefundAccName(String refundAccName) {
        RefundAccName = refundAccName;
    }

    public String getRecAccNo() {
        return RecAccNo;
    }

    public void setRecAccNo(String recAccNo) {
        RecAccNo = recAccNo;
    }

    public String getRecAccName() {
        return RecAccName;
    }

    public void setRecAccName(String recAccName) {
        RecAccName = recAccName;
    }

    public String getRecBankName() {
        return RecBankName;
    }

    public void setRecBankName(String recBankName) {
        RecBankName = recBankName;
    }

    public String getRecBranchId() {
        return RecBranchId;
    }

    public void setRecBranchId(String recBranchId) {
        RecBranchId = recBranchId;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getRefundMemo() {
        return RefundMemo;
    }

    public void setRefundMemo(String refundMemo) {
        RefundMemo = refundMemo;
    }

    @Override
    public String toString() {
        return "RefundTurnover{" +
                "MerTranSerialNo='" + MerTranSerialNo + '\'' +
                ", WaterNo='" + WaterNo + '\'' +
                ", RefundState='" + RefundState + '\'' +
                ", ChannelApi='" + ChannelApi + '\'' +
                ", TranAmt='" + TranAmt + '\'' +
                ", TranCry='" + TranCry + '\'' +
                ", ReturnFee='" + ReturnFee + '\'' +
                ", RefundAccNo='" + RefundAccNo + '\'' +
                ", RefundAccName='" + RefundAccName + '\'' +
                ", RecAccNo='" + RecAccNo + '\'' +
                ", RecAccName='" + RecAccName + '\'' +
                ", RecBankName='" + RecBankName + '\'' +
                ", RecBranchId='" + RecBranchId + '\'' +
                ", BatchNo='" + BatchNo + '\'' +
                ", RefundMemo='" + RefundMemo + '\'' +
                '}';
    }
}
