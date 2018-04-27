package com.spring.bocompay.domain.merchant;

/**
 * @description: 二级商户协议信息
 * @author: Katherine
 * @create: 2018/4/27 9:55
 */
public class SubMerPtcInfo {
    protected String SubMerPtcId;//商户协议号
    protected String FeePeriod;//手续费收费周期
    protected String ReturnFeeFlg;//退货返回手续费标识
    protected String FeeGroupId;//手续费分类代码
    protected String PtcStatus;//商户协议状态
    protected String PtcMemo;//商户协议备注

    public SubMerPtcInfo() {
    }

    public SubMerPtcInfo(String subMerPtcId, String feePeriod, String returnFeeFlg, String feeGroupId, String ptcStatus, String ptcMemo) {
        SubMerPtcId = subMerPtcId;
        FeePeriod = feePeriod;
        ReturnFeeFlg = returnFeeFlg;
        FeeGroupId = feeGroupId;
        PtcStatus = ptcStatus;
        PtcMemo = ptcMemo;
    }

    public String getSubMerPtcId() {
        return SubMerPtcId;
    }

    public void setSubMerPtcId(String subMerPtcId) {
        SubMerPtcId = subMerPtcId;
    }

    public String getFeePeriod() {
        return FeePeriod;
    }

    public void setFeePeriod(String feePeriod) {
        FeePeriod = feePeriod;
    }

    public String getReturnFeeFlg() {
        return ReturnFeeFlg;
    }

    public void setReturnFeeFlg(String returnFeeFlg) {
        ReturnFeeFlg = returnFeeFlg;
    }

    public String getFeeGroupId() {
        return FeeGroupId;
    }

    public void setFeeGroupId(String feeGroupId) {
        FeeGroupId = feeGroupId;
    }

    public String getPtcStatus() {
        return PtcStatus;
    }

    public void setPtcStatus(String ptcStatus) {
        PtcStatus = ptcStatus;
    }

    public String getPtcMemo() {
        return PtcMemo;
    }

    public void setPtcMemo(String ptcMemo) {
        PtcMemo = ptcMemo;
    }

    @Override
    public String toString() {
        return "SubMerPtcInfo{" +
                "SubMerPtcId='" + SubMerPtcId + '\'' +
                ", FeePeriod='" + FeePeriod + '\'' +
                ", ReturnFeeFlg='" + ReturnFeeFlg + '\'' +
                ", FeeGroupId='" + FeeGroupId + '\'' +
                ", PtcStatus='" + PtcStatus + '\'' +
                ", PtcMemo='" + PtcMemo + '\'' +
                '}';
    }
}
