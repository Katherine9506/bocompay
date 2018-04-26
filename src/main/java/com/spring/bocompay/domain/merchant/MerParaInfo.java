package com.spring.bocompay.domain.merchant;

/**
 * @description: 商户业务参数
 * @author: Katherine
 * @create: 2018/4/26 18:12
 */
public class MerParaInfo {
    private String MerParType;//商户签约参数类型
    private String MerParValue;//商户签约参数值
    private String MerParClass;//商户签约参数分类
    private String MerParMemo;//商户签约参数备注

    public MerParaInfo() {
    }

    public MerParaInfo(String merParType, String merParValue, String merParClass, String merParMemo) {
        MerParType = merParType;
        MerParValue = merParValue;
        MerParClass = merParClass;
        MerParMemo = merParMemo;
    }

    public String getMerParType() {
        return MerParType;
    }

    public void setMerParType(String merParType) {
        MerParType = merParType;
    }

    public String getMerParValue() {
        return MerParValue;
    }

    public void setMerParValue(String merParValue) {
        MerParValue = merParValue;
    }

    public String getMerParClass() {
        return MerParClass;
    }

    public void setMerParClass(String merParClass) {
        MerParClass = merParClass;
    }

    public String getMerParMemo() {
        return MerParMemo;
    }

    public void setMerParMemo(String merParMemo) {
        MerParMemo = merParMemo;
    }

    @Override
    public String toString() {
        return "MerParaInfo{" +
                "MerParType='" + MerParType + '\'' +
                ", MerParValue='" + MerParValue + '\'' +
                ", MerParClass='" + MerParClass + '\'' +
                ", MerParMemo='" + MerParMemo + '\'' +
                '}';
    }
}
