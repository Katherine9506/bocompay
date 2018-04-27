package com.spring.bocompay.domain.merchant;

/**
 * @description: 商户基本信息
 * @author: Katherine
 * @create: 2018/4/27 9:50
 */
public class MerBaseInfo {
    private String MerId;//商户编号
    private String CstNo;//企业网银客户号
    private String HostNo;//核心客户号
    private String EntNameCN;//企业中文名称
    private String EntNameEN;//企业英文名称
    private String CertType;//证件类型
    private String CertNo;//证件号码
    private String OpenBra;//商户开户分行
    private String OpenNode;//商户开户网点

    public MerBaseInfo() {
    }

    public MerBaseInfo(
            String merId, String cstNo, String hostNo, String entNameCN, String entNameEN,
            String certType, String certNo, String openBra, String openNode) {
        MerId = merId;
        CstNo = cstNo;
        HostNo = hostNo;
        EntNameCN = entNameCN;
        EntNameEN = entNameEN;
        CertType = certType;
        CertNo = certNo;
        OpenBra = openBra;
        OpenNode = openNode;
    }

    public String getMerId() {
        return MerId;
    }

    public void setMerId(String merId) {
        MerId = merId;
    }

    public String getCstNo() {
        return CstNo;
    }

    public void setCstNo(String cstNo) {
        CstNo = cstNo;
    }

    public String getHostNo() {
        return HostNo;
    }

    public void setHostNo(String hostNo) {
        HostNo = hostNo;
    }

    public String getEntNameCN() {
        return EntNameCN;
    }

    public void setEntNameCN(String entNameCN) {
        EntNameCN = entNameCN;
    }

    public String getEntNameEN() {
        return EntNameEN;
    }

    public void setEntNameEN(String entNameEN) {
        EntNameEN = entNameEN;
    }

    public String getCertType() {
        return CertType;
    }

    public void setCertType(String certType) {
        CertType = certType;
    }

    public String getCertNo() {
        return CertNo;
    }

    public void setCertNo(String certNo) {
        CertNo = certNo;
    }

    public String getOpenBra() {
        return OpenBra;
    }

    public void setOpenBra(String openBra) {
        OpenBra = openBra;
    }

    public String getOpenNode() {
        return OpenNode;
    }

    public void setOpenNode(String openNode) {
        OpenNode = openNode;
    }

    @Override
    public String toString() {
        return "MerBaseInfo{" +
                "MerId='" + MerId + '\'' +
                ", CstNo='" + CstNo + '\'' +
                ", HostNo='" + HostNo + '\'' +
                ", EntNameCN='" + EntNameCN + '\'' +
                ", EntNameEN='" + EntNameEN + '\'' +
                ", CertType='" + CertType + '\'' +
                ", CertNo='" + CertNo + '\'' +
                ", OpenBra='" + OpenBra + '\'' +
                ", OpenNode='" + OpenNode + '\'' +
                '}';
    }
}