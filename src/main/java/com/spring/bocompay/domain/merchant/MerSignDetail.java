package com.spring.bocompay.domain.merchant;

import java.util.List;
import java.util.Map;

/**
 * @description: 一级商户的签约详细信息
 * @author: Katherine
 * @create: 2018/4/26 17:35
 */
public class MerSignDetail {
    //head
    private String RspType;//响应类型
    private String RspCode;//交易返回码
    private String RspMsg;//交易返回信息
    private String RspDate;//响应日期
    private String RspTime;//响应时间
    //body
    private Map<String, String> MerBaseInfo;//商户基本信息
//    private String MerId;//商户编号
//    private String CstNo;//企业网银客户号
//    private String HostNo;//核心客户号
//    private String EnterNameCN;//企业中文名称
//    private String EnterNameEN;//企业英文名称
//    private String CertType;//证件类型
//    private String CertNo;//证件号码
//    private String OpenBra;//商户开户分行
//    private String OpenNode;//商户开户网点

    private Map<String, String> MerBusInfo;//商户业务信息
//    private String MerchNameCN;//商户中文名称
//    private String MerchNameEN;//商户英文名称
//    private String ICP;//ICP号
//    private String MerchType;//商户类别
//    private String WebsiteURL;//商户网站域名
//    private String MerchAddr;//商户地址
//    private String ContacterName;//联系人姓名
//    private String PhoneNo;//联系电话
//    private String MobileNo;//手机号码
//    private String EmailAddr;//电子邮箱地址
//    private String MerchDetailInfo;//商户情况说明
//    private String MerchMemo;//商户备注

    private Map<String, String> MerPtcInfo;//商户协议信息
//    private String MerPtcId;//商户协议号
//    private String TranType;//交易类型
//    private String TotalLayer;//商户协议总层级
//    private String TranMode;//交易模式
//    private String SubPtcCreMod;//商城开通二级商户
//    private String FeeChgObj;//手续费收取对象
//    private String FeePeriod;//手续费收取周期
//    private String ReturnFeeFlg;//退货返还手续费
//    private String FeeGroupId;//手续费分类代码
//    private String ReturnURL;//前台通知地址
//    private String NotifyURL;//后台通知地址
//    private String PtcStatus;//商户协议状态
//    private String PtcMemo;//商户协议备注

    private List<ExtMerIdInfo> ExtMerIdList;//外部商户号列表

    private List<MerParaInfo> MerParaList;//商户业务参数列表

    private List<FeeInfo> FeeList;//手续费模式列表

    private List<MerAccInfo> MerAccList;//帐号信息列表

    public MerSignDetail() {
    }

    public MerSignDetail(String rspType, String rspCode, String rspMsg, String rspDate, String rspTime) {
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

    public Map<String, String> getMerBaseInfo() {
        return MerBaseInfo;
    }

    public void setMerBaseInfo(Map<String, String> merBaseInfo) {
        MerBaseInfo = merBaseInfo;
    }

    public Map<String, String> getMerBusInfo() {
        return MerBusInfo;
    }

    public void setMerBusInfo(Map<String, String> merBusInfo) {
        MerBusInfo = merBusInfo;
    }

    public Map<String, String> getMerPtcInfo() {
        return MerPtcInfo;
    }

    public void setMerPtcInfo(Map<String, String> merPtcInfo) {
        MerPtcInfo = merPtcInfo;
    }

    public List<ExtMerIdInfo> getExtMerIdList() {
        return ExtMerIdList;
    }

    public void setExtMerIdList(List<ExtMerIdInfo> extMerIdList) {
        ExtMerIdList = extMerIdList;
    }

    public List<MerParaInfo> getMerParaList() {
        return MerParaList;
    }

    public void setMerParaList(List<MerParaInfo> merParaList) {
        MerParaList = merParaList;
    }

    public List<FeeInfo> getFeeList() {
        return FeeList;
    }

    public void setFeeList(List<FeeInfo> feeList) {
        FeeList = feeList;
    }

    public List<MerAccInfo> getMerAccList() {
        return MerAccList;
    }

    public void setMerAccList(List<MerAccInfo> merAccList) {
        MerAccList = merAccList;
    }

    @Override
    public String toString() {
        return "MerSignDetail{" +
                "RspType='" + RspType + '\'' +
                ", RspCode='" + RspCode + '\'' +
                ", RspMsg='" + RspMsg + '\'' +
                ", RspDate='" + RspDate + '\'' +
                ", RspTime='" + RspTime + '\'' +
                ", MerBaseInfo=" + MerBaseInfo +
                ", MerBusInfo=" + MerBusInfo +
                ", MerPtcInfo=" + MerPtcInfo +
                ", ExtMerIdList=" + ExtMerIdList +
                ", MerParaList=" + MerParaList +
                ", FeeList=" + FeeList +
                ", MerAccList=" + MerAccList +
                '}';
    }
}

























