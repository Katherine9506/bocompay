package com.spring.bocompay.domain.merchant;

/**
 * @description: 一级商户 前台/后台通知地址
 * @author: Katherine
 * @create: 2018/4/26 17:20
 */
public class MerRetNotifyUrl {
    //head
    private String RspType;//响应类型
    private String RspCode;//交易返回码
    private String RspMsg;//交易返回信息
    private String RspDate;//响应日期
    private String RspTime;//响应时间
    //body
    private String MerPtcId;//一级商户协议号
    private String ReturnURL;//前台通知地址
    private String NotifyURL;//后台通知地址
    private String AgreedReturnURL;//一键支付账户签约前台通知地址
    private String AgreedNotifyURL;//一键支付账户签约后台通知地址

    public MerRetNotifyUrl() {
    }

    public MerRetNotifyUrl(String rspType, String rspCode, String rspMsg, String rspDate, String rspTime) {
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

    public String getMerPtcId() {
        return MerPtcId;
    }

    public void setMerPtcId(String merPtcId) {
        MerPtcId = merPtcId;
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

    public String getAgreedReturnURL() {
        return AgreedReturnURL;
    }

    public void setAgreedReturnURL(String agreedReturnURL) {
        AgreedReturnURL = agreedReturnURL;
    }

    public String getAgreedNotifyURL() {
        return AgreedNotifyURL;
    }

    public void setAgreedNotifyURL(String agreedNotifyURL) {
        AgreedNotifyURL = agreedNotifyURL;
    }

    @Override
    public String toString() {
        return "MerRetNotifyUrl{" +
                "RspType='" + RspType + '\'' +
                ", RspCode='" + RspCode + '\'' +
                ", RspMsg='" + RspMsg + '\'' +
                ", RspDate='" + RspDate + '\'' +
                ", RspTime='" + RspTime + '\'' +
                ", MerPtcId='" + MerPtcId + '\'' +
                ", ReturnURL='" + ReturnURL + '\'' +
                ", NotifyURL='" + NotifyURL + '\'' +
                ", AgreedReturnURL='" + AgreedReturnURL + '\'' +
                ", AgreedNotifyURL='" + AgreedNotifyURL + '\'' +
                '}';
    }
}
