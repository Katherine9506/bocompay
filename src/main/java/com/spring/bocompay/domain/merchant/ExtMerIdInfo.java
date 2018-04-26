package com.spring.bocompay.domain.merchant;

/**
 * @description: 外部商户号信息
 * @author: Katherine
 * @create: 2018/4/26 18:10
 */
public class ExtMerIdInfo {
    private String ExtMerId;//外部商户号
    private String InstType;//机构名称

    public ExtMerIdInfo() {
    }

    public ExtMerIdInfo(String extMerId, String instType) {
        ExtMerId = extMerId;
        InstType = instType;
    }

    public String getExtMerId() {
        return ExtMerId;
    }

    public void setExtMerId(String extMerId) {
        ExtMerId = extMerId;
    }

    public String getInstType() {
        return InstType;
    }

    public void setInstType(String instType) {
        InstType = instType;
    }

    @Override
    public String toString() {
        return "ExtMerIdInfo{" +
                "ExtMerId='" + ExtMerId + '\'' +
                ", InstType='" + InstType + '\'' +
                '}';
    }
}
