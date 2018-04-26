package com.spring.bocompay.domain;

import java.util.List;

/**
 * Created by Elena@qq.com on 2018/4/25
 * Description: 订单明细 响应报文
 */
public class OrderDetailResponseMessage {
    //head
    private String RspType;//响应类型
    private String RspCode;//交易返回码
    private String RspMsg;//交易返回信息
    private String RspDate;//响应日期
    private String RspTime;//响应时间
    //body
    private String TradeOrderNo;//交易订单号
    private String PayOrderNo;//付款流水号
    private String TradeState;//交易状态
    private String NTradeState;//新交易状态
    private String OrderState;//订单状态
    private String TranModeId;//交易模式
    private String CreateDateTime;//订单创建时间
    private String GmtCloseDateTime;//订单关闭时间
    private String MerPtcId;//一级商户签约协议号
    private String MerNameCN;//一级商户简称
    private String MerOpenBranch;//一级商户所属分行
    private String SubMerPtcId;//二级商户签约协议
    private String SubMerNameCN;//二级商户名称
    private String MerOrderNo;//一级商户（外部）订单号
    private String PlatMemo;//一级商户订单备注
    private String ReceiptCode;//电子回单校验码
    private String BankMemo;//银行订单备注
    private String TranAmt;//订单金额
    private String TranCry;//订单币种
    private String PayAmt;//实际付款金额
    private String PayCry;//实际付款币种
    private String PaiedSum;//已支付总金额
    private String RefundSum;//已退款金额
    private String ConfirmSum;//已交付金额
    private String GoodsTxt;//商品简称
    private String GoodsName;//商品名称
    private String GoodsDesc;//商品详情
    private String BuyerId;//买方会员ID
    private String BuyerName;//买方名称
    private String BuyerMemo;//买方备注
    private String SellerId;//卖方会员ID
    private String SellerName;//卖方名称
    private String SellerMemo;//卖方备注
    private String SafeReserved;//安全域
    private String ValidPeriod;//订单有效期

    private List<TradeTurnover> SettingList;//交易流水

    public OrderDetailResponseMessage() {
    }

    public OrderDetailResponseMessage(String rspType, String rspCode, String rspMsg, String rspDate, String rspTime) {
        RspType = rspType;
        RspCode = rspCode;
        RspMsg = rspMsg;
        RspDate = rspDate;
        RspTime = rspTime;
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

    public String getRspType() {
        return RspType;
    }

    public void setRspType(String rspType) {
        RspType = rspType;
    }

    public String getTradeOrderNo() {
        return TradeOrderNo;
    }

    public void setTradeOrderNo(String tradeOrderNo) {
        TradeOrderNo = tradeOrderNo;
    }

    public String getPayOrderNo() {
        return PayOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        PayOrderNo = payOrderNo;
    }

    public String getTradeState() {
        return TradeState;
    }

    public void setTradeState(String tradeState) {
        TradeState = tradeState;
    }

    public String getNTradeState() {
        return NTradeState;
    }

    public void setNTradeState(String NTradeState) {
        this.NTradeState = NTradeState;
    }

    public String getOrderState() {
        return OrderState;
    }

    public void setOrderState(String orderState) {
        OrderState = orderState;
    }

    public String getTranModeId() {
        return TranModeId;
    }

    public void setTranModeId(String tranModeId) {
        TranModeId = tranModeId;
    }

    public String getCreateDateTime() {
        return CreateDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        CreateDateTime = createDateTime;
    }

    public String getGmtCloseDateTime() {
        return GmtCloseDateTime;
    }

    public void setGmtCloseDateTime(String gmtCloseDateTime) {
        GmtCloseDateTime = gmtCloseDateTime;
    }

    public String getMerPtcId() {
        return MerPtcId;
    }

    public void setMerPtcId(String merPtcId) {
        MerPtcId = merPtcId;
    }

    public String getMerNameCN() {
        return MerNameCN;
    }

    public void setMerNameCN(String merNameCN) {
        MerNameCN = merNameCN;
    }

    public String getMerOpenBranch() {
        return MerOpenBranch;
    }

    public void setMerOpenBranch(String merOpenBranch) {
        MerOpenBranch = merOpenBranch;
    }

    public String getSubMerPtcId() {
        return SubMerPtcId;
    }

    public void setSubMerPtcId(String subMerPtcId) {
        SubMerPtcId = subMerPtcId;
    }

    public String getSubMerNameCN() {
        return SubMerNameCN;
    }

    public void setSubMerNameCN(String subMerNameCN) {
        SubMerNameCN = subMerNameCN;
    }

    public String getMerOrderNo() {
        return MerOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        MerOrderNo = merOrderNo;
    }

    public String getPlatMemo() {
        return PlatMemo;
    }

    public void setPlatMemo(String platMemo) {
        PlatMemo = platMemo;
    }

    public String getReceiptCode() {
        return ReceiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        ReceiptCode = receiptCode;
    }

    public String getBankMemo() {
        return BankMemo;
    }

    public void setBankMemo(String bankMemo) {
        BankMemo = bankMemo;
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

    public String getPayAmt() {
        return PayAmt;
    }

    public void setPayAmt(String payAmt) {
        PayAmt = payAmt;
    }

    public String getPayCry() {
        return PayCry;
    }

    public void setPayCry(String payCry) {
        PayCry = payCry;
    }

    public String getPaiedSum() {
        return PaiedSum;
    }

    public void setPaiedSum(String paiedSum) {
        PaiedSum = paiedSum;
    }

    public String getRefundSum() {
        return RefundSum;
    }

    public void setRefundSum(String refundSum) {
        RefundSum = refundSum;
    }

    public String getConfirmSum() {
        return ConfirmSum;
    }

    public void setConfirmSum(String confirmSum) {
        ConfirmSum = confirmSum;
    }

    public String getGoodsTxt() {
        return GoodsTxt;
    }

    public void setGoodsTxt(String goodsTxt) {
        GoodsTxt = goodsTxt;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getGoodsDesc() {
        return GoodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        GoodsDesc = goodsDesc;
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

    public String getBuyerMemo() {
        return BuyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        BuyerMemo = buyerMemo;
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

    public String getSellerMemo() {
        return SellerMemo;
    }

    public void setSellerMemo(String sellerMemo) {
        SellerMemo = sellerMemo;
    }

    public String getSafeReserved() {
        return SafeReserved;
    }

    public void setSafeReserved(String safeReserved) {
        SafeReserved = safeReserved;
    }

    public String getValidPeriod() {
        return ValidPeriod;
    }

    public void setValidPeriod(String validPeriod) {
        ValidPeriod = validPeriod;
    }

    public List<TradeTurnover> getSettingList() {
        return SettingList;
    }

    public void setSettingList(List<TradeTurnover> settingList) {
        SettingList = settingList;
    }

    @Override
    public String toString() {
        return "OrderDetailResponseMessage{" +
                "TradeOrderNo='" + TradeOrderNo + '\'' +
                ", PayOrderNo='" + PayOrderNo + '\'' +
                ", TradeState='" + TradeState + '\'' +
                ", NTradeState='" + NTradeState + '\'' +
                ", OrderState='" + OrderState + '\'' +
                ", TranModeId='" + TranModeId + '\'' +
                ", CreateDateTime='" + CreateDateTime + '\'' +
                ", GmtCloseDateTime='" + GmtCloseDateTime + '\'' +
                ", MerPtcId='" + MerPtcId + '\'' +
                ", MerNameCN='" + MerNameCN + '\'' +
                ", MerOpenBranch='" + MerOpenBranch + '\'' +
                ", SubMerPtcId='" + SubMerPtcId + '\'' +
                ", SubMerNameCN='" + SubMerNameCN + '\'' +
                ", MerOrderNo='" + MerOrderNo + '\'' +
                ", PlatMemo='" + PlatMemo + '\'' +
                ", ReceiptCode='" + ReceiptCode + '\'' +
                ", BankMemo='" + BankMemo + '\'' +
                ", TranAmt='" + TranAmt + '\'' +
                ", TranCry='" + TranCry + '\'' +
                ", PayAmt='" + PayAmt + '\'' +
                ", PayCry='" + PayCry + '\'' +
                ", PaiedSum='" + PaiedSum + '\'' +
                ", RefundSum='" + RefundSum + '\'' +
                ", ConfirmSum='" + ConfirmSum + '\'' +
                ", GoodsTxt='" + GoodsTxt + '\'' +
                ", GoodsName='" + GoodsName + '\'' +
                ", GoodsDesc='" + GoodsDesc + '\'' +
                ", BuyerId='" + BuyerId + '\'' +
                ", BuyerName='" + BuyerName + '\'' +
                ", BuyerMemo='" + BuyerMemo + '\'' +
                ", SellerId='" + SellerId + '\'' +
                ", SellerName='" + SellerName + '\'' +
                ", SellerMemo='" + SellerMemo + '\'' +
                ", SafeReserved='" + SafeReserved + '\'' +
                ", ValidPeriod='" + ValidPeriod + '\'' +
                ", SettingList=" + SettingList +
                '}';
    }
}






















