package com.spring.bocompay.domain.merchant;

import java.util.List;

/**
 * @description: 一级商户的签约详细信息
 * @author: Katherine
 * @create: 2018/4/26 17:35
 */
public class MerSignDetailResponseMessage {
    //body
    private MerBaseInfo MerBaseInfo;//商户基本信息
    private MerBusInfo MerBusInfo;//商户业务信息
    private MerPtcInfo MerPtcInfo;//商户协议信息
    private List<ExtMerIdInfo> ExtMerIdList;//外部商户号列表
    private List<MerParaInfo> MerParaList;//商户业务参数列表
    private List<FeeInfo> FeeList;//手续费模式列表
    private List<MerAccInfo> MerAccList;//帐号信息列表

    public MerSignDetailResponseMessage() {
    }

    public com.spring.bocompay.domain.merchant.MerBaseInfo getMerBaseInfo() {
        return MerBaseInfo;
    }

    public void setMerBaseInfo(com.spring.bocompay.domain.merchant.MerBaseInfo merBaseInfo) {
        MerBaseInfo = merBaseInfo;
    }

    public com.spring.bocompay.domain.merchant.MerBusInfo getMerBusInfo() {
        return MerBusInfo;
    }

    public void setMerBusInfo(com.spring.bocompay.domain.merchant.MerBusInfo merBusInfo) {
        MerBusInfo = merBusInfo;
    }

    public com.spring.bocompay.domain.merchant.MerPtcInfo getMerPtcInfo() {
        return MerPtcInfo;
    }

    public void setMerPtcInfo(com.spring.bocompay.domain.merchant.MerPtcInfo merPtcInfo) {
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
        return "MerSignDetailResponseMessage{" +
                "MerBaseInfo=" + MerBaseInfo +
                ", MerBusInfo=" + MerBusInfo +
                ", MerPtcInfo=" + MerPtcInfo +
                ", ExtMerIdList=" + ExtMerIdList +
                ", MerParaList=" + MerParaList +
                ", FeeList=" + FeeList +
                ", MerAccList=" + MerAccList +
                '}';
    }
}

























