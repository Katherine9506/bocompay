package com.spring.bocompay.domain.merchant;

import java.util.List;
import java.util.Map;

/**
 * @description: 二级商户签约查询 响应报文
 * @author: Katherine
 * @create: 2018/4/27 10:58
 */
public class SndMerSignDetailResponseMessage {
    //body
    private String MerPtcInfoNum;//二级商户签约协议数量
    private Map<String, String> ParentMerBaseInfo;//一级商户基本信息
    private MerBaseInfo MerBaseInfo;//商户基本信息
    private MerBusInfo MerBusInfo;//商户业务信息
    private List<Map<String, Object>> MerPtcInfoList;//商户协议信息列表

    public SndMerSignDetailResponseMessage() {
    }


    public String getMerPtcInfoNum() {
        return MerPtcInfoNum;
    }

    public void setMerPtcInfoNum(String merPtcInfoNum) {
        MerPtcInfoNum = merPtcInfoNum;
    }

    public Map<String, String> getParentMerBaseInfo() {
        return ParentMerBaseInfo;
    }

    public void setParentMerBaseInfo(Map<String, String> parentMerBaseInfo) {
        ParentMerBaseInfo = parentMerBaseInfo;
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

    public List getMerPtcInfoList() {
        return MerPtcInfoList;
    }

    public void setMerPtcInfoList(List merPtcInfoList) {
        MerPtcInfoList = merPtcInfoList;
    }

    @Override
    public String toString() {
        return "SndMerSignDetailResponseMessage{" +
                "MerPtcInfoNum='" + MerPtcInfoNum + '\'' +
                ", ParentMerBaseInfo=" + ParentMerBaseInfo +
                ", MerBaseInfo=" + MerBaseInfo +
                ", MerBusInfo=" + MerBusInfo +
                ", MerPtcInfoList=" + MerPtcInfoList +
                '}';
    }
}





















