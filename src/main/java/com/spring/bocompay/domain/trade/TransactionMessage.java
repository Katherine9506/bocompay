package com.spring.bocompay.domain.trade;

import com.bocom.bocompay.BocomDataMap;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Elena@qq.com on 2018/4/24
 * Description: 交易创建
 */
public class TransactionMessage {

    @Value("#{prop.MerCertID}")
    private String MerCertID;//商户证书ID

    //报文头
    private String TranCode;//交易码
    private String MerPtcId;//一级商户协议号
    private String TranDate;//请求日期
    private String TranTime;//请求时间

    //报文体

    private String MerTranSerialNo;//商户交易流水号
    private String SafeReserved;//安全域

    private BocomDataMap BusiInfo = new BocomDataMap();//业务信息
    private String MerOrderNo;//平台商户（外部）订单号

    private BocomDataMap UserInfo = new BocomDataMap();//会员信息
    private String BuyerId;//买家会员编号
    private String BuyerName;//买家昵称
    private String SellerId;//卖家会员编号
    private String SellerName;//卖家昵称

    private BocomDataMap GoodsInfo = new BocomDataMap();//商品信息
    private String GoodsName;//商品名称
    private String GoodsTxt;//商品简称
    private String GoodsDesc;//商品详情

    private BocomDataMap TranInfo = new BocomDataMap();//交易信息
    private String TranModeId;//交易方式*
    private String TranAmt;//交易金额(须两位小数)*
    private String TranCry;//交易币种*

    private BocomDataMap ChannelInfo = new BocomDataMap();//通道信息
    private String ChannelApi;//通道接口编号
    private String ChannelInst;//通道所属机构

    private BocomDataMap MemoInfo = new BocomDataMap();//备注信息
    private String BuyerMemo;//买家备注
    private String SellerMemo;//卖家备注
    private String PlatMemo;//平台商备注
    private String PayMemo;//付款备注

    //String SubMerPtcId;


    public String getMerCertID() {
        return MerCertID;
    }

    public void setMerCertID(String merCertID) {
        MerCertID = merCertID;
    }

    public void setMerOrderNo(String merOrderNo) {
        MerOrderNo = merOrderNo;
    }

    public String getMerOrderNo() {
        return MerOrderNo;
    }

    public String getTranCode() {
        return TranCode;
    }

    public void setTranCode(String tranCode) {
        TranCode = tranCode;
    }

    public String getMerPtcId() {
        return MerPtcId;
    }

    public void setMerPtcId(String merPtcId) {
        MerPtcId = merPtcId;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getTranTime() {
        return TranTime;
    }

    public void setTranTime(String tranTime) {
        TranTime = tranTime;
    }

    public String getMerTranSerialNo() {
        return MerTranSerialNo;
    }

    public void setMerTranSerialNo(String merTranSerialNo) {
        MerTranSerialNo = merTranSerialNo;
    }

    public String getSafeReserved() {
        return SafeReserved;
    }

    public void setSafeReserved(String safeReserved) {
        SafeReserved = safeReserved;
    }

    public BocomDataMap getBusiInfo() {
        return BusiInfo;
    }

    public void setBusiInfo(BocomDataMap busiInfo) {
        BusiInfo = busiInfo;
    }

    public BocomDataMap getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(BocomDataMap userInfo) {
        UserInfo = userInfo;
    }

    public String getBuyerId() {
        return BuyerId;
    }

    public void setBuyerId(String buyerId) {
        BuyerId = buyerId;
    }

    public String getBuyerName() {
        return BuyerName;
    }

    public void setBuyerName(String buyerName) {
        BuyerName = buyerName;
    }

    public String getSellerId() {
        return SellerId;
    }

    public void setSellerId(String sellerId) {
        SellerId = sellerId;
    }

    public String getSellerName() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }

    public BocomDataMap getGoodsInfo() {
        return GoodsInfo;
    }

    public void setGoodsInfo(BocomDataMap goodsInfo) {
        GoodsInfo = goodsInfo;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getGoodsTxt() {
        return GoodsTxt;
    }

    public void setGoodsTxt(String goodsTxt) {
        GoodsTxt = goodsTxt;
    }

    public String getGoodsDesc() {
        return GoodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        GoodsDesc = goodsDesc;
    }

    public BocomDataMap getTranInfo() {
        return TranInfo;
    }

    public void setTranInfo(BocomDataMap tranInfo) {
        TranInfo = tranInfo;
    }

    public String getTranModeId() {
        return TranModeId;
    }

    public void setTranModeId(String tranModeId) {
        TranModeId = tranModeId;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getTranCry() {
        return TranCry;
    }

    public void setTranCry(String tranCry) {
        TranCry = tranCry;
    }

    public BocomDataMap getChannelInfo() {
        return ChannelInfo;
    }

    public void setChannelInfo(BocomDataMap channelInfo) {
        ChannelInfo = channelInfo;
    }

    public String getChannelApi() {
        return ChannelApi;
    }

    public void setChannelApi(String channelApi) {
        ChannelApi = channelApi;
    }

    public String getChannelInst() {
        return ChannelInst;
    }

    public void setChannelInst(String channelInst) {
        ChannelInst = channelInst;
    }

    public BocomDataMap getMemoInfo() {
        return MemoInfo;
    }

    public void setMemoInfo(BocomDataMap memoInfo) {
        MemoInfo = memoInfo;
    }

    public String getBuyerMemo() {
        return BuyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        BuyerMemo = buyerMemo;
    }

    public String getSellerMemo() {
        return SellerMemo;
    }

    public void setSellerMemo(String sellerMemo) {
        SellerMemo = sellerMemo;
    }

    public String getPlatMemo() {
        return PlatMemo;
    }

    public void setPlatMemo(String platMemo) {
        PlatMemo = platMemo;
    }

    public String getPayMemo() {
        return PayMemo;
    }

    public void setPayMemo(String payMemo) {
        PayMemo = payMemo;
    }

    @Override
    public String toString() {
        return "TransactionMessage{" +
                "MerCertID='" + MerCertID + '\'' +
                ", TranCode='" + TranCode + '\'' +
                ", MerPtcId='" + MerPtcId + '\'' +
                ", TranDate='" + TranDate + '\'' +
                ", TranTime='" + TranTime + '\'' +
                ", MerTranSerialNo='" + MerTranSerialNo + '\'' +
                ", SafeReserved='" + SafeReserved + '\'' +
                ", BusiInfo=" + BusiInfo +
                ", MerOrderNo='" + MerOrderNo + '\'' +
                ", UserInfo=" + UserInfo +
                ", BuyerId='" + BuyerId + '\'' +
                ", BuyerName='" + BuyerName + '\'' +
                ", SellerId='" + SellerId + '\'' +
                ", SellerName='" + SellerName + '\'' +
                ", GoodsInfo=" + GoodsInfo +
                ", GoodsName='" + GoodsName + '\'' +
                ", GoodsTxt='" + GoodsTxt + '\'' +
                ", GoodsDesc='" + GoodsDesc + '\'' +
                ", TranInfo=" + TranInfo +
                ", TranModeId='" + TranModeId + '\'' +
                ", TranAmt='" + TranAmt + '\'' +
                ", TranCry='" + TranCry + '\'' +
                ", ChannelInfo=" + ChannelInfo +
                ", ChannelApi='" + ChannelApi + '\'' +
                ", ChannelInst='" + ChannelInst + '\'' +
                ", MemoInfo=" + MemoInfo +
                ", BuyerMemo='" + BuyerMemo + '\'' +
                ", SellerMemo='" + SellerMemo + '\'' +
                ", PlatMemo='" + PlatMemo + '\'' +
                ", PayMemo='" + PayMemo + '\'' +
                '}';
    }
}
