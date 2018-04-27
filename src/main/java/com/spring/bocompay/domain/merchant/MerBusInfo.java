package com.spring.bocompay.domain.merchant;

/**
 * @description: 商户业务信息
 * @author: Katherine
 * @create: 2018/4/27 10:03
 */
public class MerBusInfo {
    private String MerchNameCN;//商户中文名称
    private String MerchNameEN;//商户英文名称
    private String ICP;//ICP号
    private String MerchType;//商户类别
    private String WebsiteURL;//商户网站域名
    private String MerchAddr;//商户地址
    private String ContacterName;//联系人姓名
    private String PhoneNo;//联系电话
    private String MobileNo;//手机号码
    private String EmailAddr;//电子邮箱地址
    private String MerchDetailInfo;//商户情况说明
    private String MerchMemo;//商户备注

    private String SubMerMemId;//二级商户会员编号(在TranCode=BPAYPY4026时生效)

    public MerBusInfo() {
    }

    public MerBusInfo(
            String merchNameCN, String merchNameEN, String ICP, String merchType,
            String websiteURL, String merchAddr, String contacterName, String phoneNo,
            String mobileNo, String emailAddr, String merchDetailInfo, String merchMemo, String subMerMemId) {
        MerchNameCN = merchNameCN;
        MerchNameEN = merchNameEN;
        this.ICP = ICP;
        MerchType = merchType;
        WebsiteURL = websiteURL;
        MerchAddr = merchAddr;
        ContacterName = contacterName;
        PhoneNo = phoneNo;
        MobileNo = mobileNo;
        EmailAddr = emailAddr;
        MerchDetailInfo = merchDetailInfo;
        MerchMemo = merchMemo;
        SubMerMemId = subMerMemId;
    }

    public String getSubMerMemId() {
        return SubMerMemId;
    }

    public void setSubMerMemId(String subMerMemId) {
        SubMerMemId = subMerMemId;
    }

    public String getMerchNameCN() {
        return MerchNameCN;
    }

    public void setMerchNameCN(String merchNameCN) {
        MerchNameCN = merchNameCN;
    }

    public String getMerchNameEN() {
        return MerchNameEN;
    }

    public void setMerchNameEN(String merchNameEN) {
        MerchNameEN = merchNameEN;
    }

    public String getICP() {
        return ICP;
    }

    public void setICP(String ICP) {
        this.ICP = ICP;
    }

    public String getMerchType() {
        return MerchType;
    }

    public void setMerchType(String merchType) {
        MerchType = merchType;
    }

    public String getWebsiteURL() {
        return WebsiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        WebsiteURL = websiteURL;
    }

    public String getMerchAddr() {
        return MerchAddr;
    }

    public void setMerchAddr(String merchAddr) {
        MerchAddr = merchAddr;
    }

    public String getContacterName() {
        return ContacterName;
    }

    public void setContacterName(String contacterName) {
        ContacterName = contacterName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getEmailAddr() {
        return EmailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        EmailAddr = emailAddr;
    }

    public String getMerchDetailInfo() {
        return MerchDetailInfo;
    }

    public void setMerchDetailInfo(String merchDetailInfo) {
        MerchDetailInfo = merchDetailInfo;
    }

    public String getMerchMemo() {
        return MerchMemo;
    }

    public void setMerchMemo(String merchMemo) {
        MerchMemo = merchMemo;
    }

    @Override
    public String toString() {
        return "MerBusInfo{" +
                "MerchNameCN='" + MerchNameCN + '\'' +
                ", MerchNameEN='" + MerchNameEN + '\'' +
                ", ICP='" + ICP + '\'' +
                ", MerchType='" + MerchType + '\'' +
                ", WebsiteURL='" + WebsiteURL + '\'' +
                ", MerchAddr='" + MerchAddr + '\'' +
                ", ContacterName='" + ContacterName + '\'' +
                ", PhoneNo='" + PhoneNo + '\'' +
                ", MobileNo='" + MobileNo + '\'' +
                ", EmailAddr='" + EmailAddr + '\'' +
                ", MerchDetailInfo='" + MerchDetailInfo + '\'' +
                ", MerchMemo='" + MerchMemo + '\'' +
                ", SubMerMemId='" + SubMerMemId + '\'' +
                '}';
    }
}
