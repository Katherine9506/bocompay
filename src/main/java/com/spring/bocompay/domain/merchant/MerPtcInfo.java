package com.spring.bocompay.domain.merchant;

/**
 * @description: 一级商户协议信息
 * @author: Katherine
 * @create: 2018/4/27 9:58
 */
public class MerPtcInfo {
    private String MerPtcId;//商户协议号
    private String TranType;//交易类型
    private String TotalLayer;//商户协议总层级
    private String TranMode;//交易模式
    private String SubPtcCreMod;//商城开通二级商户
    private String FeeChgObj;//手续费收取对象
    private String FeePeriod;//手续费收取周期
    private String ReturnFeeFlg;//退货返还手续费
    private String FeeGroupId;//手续费分类代码
    private String ReturnURL;//前台通知地址
    private String NotifyURL;//后台通知地址
    private String PtcStatus;//商户协议状态
    private String PtcMemo;//商户协议备注

    public MerPtcInfo() {
    }

    public MerPtcInfo(
            String merPtcId, String tranType, String totalLayer, String tranMode, String subPtcCreMod,
            String feeChgObj, String feePeriod, String returnFeeFlg, String feeGroupId, String returnURL,
            String notifyURL, String ptcStatus, String ptcMemo) {
        MerPtcId = merPtcId;
        TranType = tranType;
        TotalLayer = totalLayer;
        TranMode = tranMode;
        SubPtcCreMod = subPtcCreMod;
        FeeChgObj = feeChgObj;
        FeePeriod = feePeriod;
        ReturnFeeFlg = returnFeeFlg;
        FeeGroupId = feeGroupId;
        ReturnURL = returnURL;
        NotifyURL = notifyURL;
        PtcStatus = ptcStatus;
        PtcMemo = ptcMemo;
    }

    public String getMerPtcId() {
        return MerPtcId;
    }

    public void setMerPtcId(String merPtcId) {
        MerPtcId = merPtcId;
    }

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String tranType) {
        TranType = tranType;
    }

    public String getTotalLayer() {
        return TotalLayer;
    }

    public void setTotalLayer(String totalLayer) {
        TotalLayer = totalLayer;
    }

    public String getTranMode() {
        return TranMode;
    }

    public void setTranMode(String tranMode) {
        TranMode = tranMode;
    }

    public String getSubPtcCreMod() {
        return SubPtcCreMod;
    }

    public void setSubPtcCreMod(String subPtcCreMod) {
        SubPtcCreMod = subPtcCreMod;
    }

    public String getFeeChgObj() {
        return FeeChgObj;
    }

    public void setFeeChgObj(String feeChgObj) {
        FeeChgObj = feeChgObj;
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

    public String getReturnURL() {
        return ReturnURL;
    }

    public void setReturnURL(String returnURL) {
        ReturnURL = returnURL;
    }

    public String getNotifyURL() {
        return NotifyURL;
    }

    public void setNotifyURL(String notifyURL) {
        NotifyURL = notifyURL;
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
        return "MerPtcInfo{" +
                "MerPtcId='" + MerPtcId + '\'' +
                ", TranType='" + TranType + '\'' +
                ", TotalLayer='" + TotalLayer + '\'' +
                ", TranMode='" + TranMode + '\'' +
                ", SubPtcCreMod='" + SubPtcCreMod + '\'' +
                ", FeeChgObj='" + FeeChgObj + '\'' +
                ", FeePeriod='" + FeePeriod + '\'' +
                ", ReturnFeeFlg='" + ReturnFeeFlg + '\'' +
                ", FeeGroupId='" + FeeGroupId + '\'' +
                ", ReturnURL='" + ReturnURL + '\'' +
                ", NotifyURL='" + NotifyURL + '\'' +
                ", PtcStatus='" + PtcStatus + '\'' +
                ", PtcMemo='" + PtcMemo + '\'' +
                '}';
    }
}
