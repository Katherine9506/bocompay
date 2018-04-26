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

}
