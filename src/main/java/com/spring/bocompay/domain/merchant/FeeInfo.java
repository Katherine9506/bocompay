package com.spring.bocompay.domain.merchant;

/**
 * @description: 手续费
 * @author: Katherine
 * @create: 2018/4/26 18:14
 */
public class FeeInfo {
    private String FeeGroupId;//手续费分类代码
    private String FeeGroupName;//手续费分类名称
    private String TranType;//交易类型
    private String PayType;//支付类型
    private String AccType;//付款账户类型
    private String FeeModeId;//费率模式ID
    private String FeeModeName;//费率模式名称
    private String FeeModeMemo;//费率模式备注
    private String ChargeMode;//计费方式
    private String FeeRate;//手续费金额/比例
    private String FeeCry;//手续费币种
    private String MaxFeeAmount;//最大金额
    private String MinFeeAmount;//最小金额
    private String FeeMemo;//手续费备注

    public FeeInfo() {
    }

    public FeeInfo(
            String feeGroupId, String feeGroupName, String tranType, String payType, String accType,
            String feeModeId, String feeModeName, String feeModeMemo, String chargeMode, String feeRate,
            String feeCry, String maxFeeAmount, String minFeeAmount, String feeMemo) {
        FeeGroupId = feeGroupId;
        FeeGroupName = feeGroupName;
        TranType = tranType;
        PayType = payType;
        AccType = accType;
        FeeModeId = feeModeId;
        FeeModeName = feeModeName;
        FeeModeMemo = feeModeMemo;
        ChargeMode = chargeMode;
        FeeRate = feeRate;
        FeeCry = feeCry;
        MaxFeeAmount = maxFeeAmount;
        MinFeeAmount = minFeeAmount;
        FeeMemo = feeMemo;
    }

    public String getFeeGroupId() {
        return FeeGroupId;
    }

    public void setFeeGroupId(String feeGroupId) {
        FeeGroupId = feeGroupId;
    }

    public String getFeeGroupName() {
        return FeeGroupName;
    }

    public void setFeeGroupName(String feeGroupName) {
        FeeGroupName = feeGroupName;
    }

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String tranType) {
        TranType = tranType;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    public String getAccType() {
        return AccType;
    }

    public void setAccType(String accType) {
        AccType = accType;
    }

    public String getFeeModeId() {
        return FeeModeId;
    }

    public void setFeeModeId(String feeModeId) {
        FeeModeId = feeModeId;
    }

    public String getFeeModeName() {
        return FeeModeName;
    }

    public void setFeeModeName(String feeModeName) {
        FeeModeName = feeModeName;
    }

    public String getFeeModeMemo() {
        return FeeModeMemo;
    }

    public void setFeeModeMemo(String feeModeMemo) {
        FeeModeMemo = feeModeMemo;
    }

    public String getChargeMode() {
        return ChargeMode;
    }

    public void setChargeMode(String chargeMode) {
        ChargeMode = chargeMode;
    }

    public String getFeeRate() {
        return FeeRate;
    }

    public void setFeeRate(String feeRate) {
        FeeRate = feeRate;
    }

    public String getFeeCry() {
        return FeeCry;
    }

    public void setFeeCry(String feeCry) {
        FeeCry = feeCry;
    }

    public String getMaxFeeAmount() {
        return MaxFeeAmount;
    }

    public void setMaxFeeAmount(String maxFeeAmount) {
        MaxFeeAmount = maxFeeAmount;
    }

    public String getMinFeeAmount() {
        return MinFeeAmount;
    }

    public void setMinFeeAmount(String minFeeAmount) {
        MinFeeAmount = minFeeAmount;
    }

    public String getFeeMemo() {
        return FeeMemo;
    }

    public void setFeeMemo(String feeMemo) {
        FeeMemo = feeMemo;
    }
}
