package com.spring.bocompay.domain.merchant;

/**
 * @description: 一级商户 前台/后台通知地址
 * @author: Katherine
 * @create: 2018/4/26 17:20
 */
public class MerRetNotifyUrl {
    //body
    private String MerPtcId;//一级商户协议号
    private String ReturnURL;//前台通知地址
    private String NotifyURL;//后台通知地址
    private String AgreedReturnURL;//一键支付账户签约前台通知地址
    private String AgreedNotifyURL;//一键支付账户签约后台通知地址

    public MerRetNotifyUrl() {
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
                "MerPtcId='" + MerPtcId + '\'' +
                ", ReturnURL='" + ReturnURL + '\'' +
                ", NotifyURL='" + NotifyURL + '\'' +
                ", AgreedReturnURL='" + AgreedReturnURL + '\'' +
                ", AgreedNotifyURL='" + AgreedNotifyURL + '\'' +
                '}';
    }
}
