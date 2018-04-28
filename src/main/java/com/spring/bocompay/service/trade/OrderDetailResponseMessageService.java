package com.spring.bocompay.service.trade;

import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.OpInfo;
import com.bocom.bocompay.OpList;
import com.spring.bocompay.domain.trade.OrderDetailResponseMessage;
import com.spring.bocompay.domain.trade.TradeTurnover;
import com.spring.bocompay.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/25 13:42
 */
@Service
public class OrderDetailResponseMessageService extends BaseService {

    /**
     * @param client 订单明细响应报文
     * @description: 填充订单明细
     * @author: Katherine
     * @create: 2018/4/26 9:52
     */
    public OrderDetailResponseMessage fillOrderDetail(BocomClient client) {
        OrderDetailResponseMessage orderDetail = new OrderDetailResponseMessage();
        orderDetail.setTradeOrderNo(client.getData("TradeOrderNo"));
        orderDetail.setPayOrderNo(client.getData("PayOrderNo"));
        orderDetail.setTradeState(client.getData("TradeState"));
        orderDetail.setNTradeState(client.getData("NTradeState"));
        orderDetail.setOrderState(client.getData("OrderState"));
        orderDetail.setTranModeId(client.getData("TranModeId"));
        orderDetail.setCreateDateTime(client.getData("CreateDateTime"));
        orderDetail.setGmtCloseDateTime(client.getData("GmtCloseDateTime"));
        orderDetail.setMerPtcId(client.getData("MerPtcId"));
        orderDetail.setMerNameCN(client.getData("MerNameCN"));
        orderDetail.setMerOpenBranch(client.getData("MerOpenBranch"));
        orderDetail.setSubMerPtcId(client.getData("SubMerPtcId"));
        orderDetail.setSubMerNameCN(client.getData("SubMerNameCN"));
        orderDetail.setMerOrderNo(client.getData("MerOrderNo"));
        orderDetail.setPlatMemo(client.getData("PlatMemo"));
        orderDetail.setReceiptCode(client.getData("ReceiptCode"));
        orderDetail.setBankMemo(client.getData("BankMemo"));
        orderDetail.setTranAmt(client.getData("TranAmt"));
        orderDetail.setTranCry(client.getData("TranCry"));
        orderDetail.setPayAmt(client.getData("PayAmt"));
        orderDetail.setPayCry(client.getData("PayCry"));
        orderDetail.setPaiedSum(client.getData("PaiedSum"));
        orderDetail.setRefundSum(client.getData("RefundSum"));
        orderDetail.setConfirmSum(client.getData("ConfirmSum"));
        orderDetail.setGoodsTxt(client.getData("GoodsTxt"));
        orderDetail.setGoodsName(client.getData("GoodsName"));
        orderDetail.setGoodsDesc(client.getData("GoodsDesc"));
        orderDetail.setBuyerId(client.getData("BuyerId"));
        orderDetail.setBuyerName(client.getData("BuyerName"));
        orderDetail.setBuyerMemo(client.getData("BuyerMemo"));
        orderDetail.setSellerId(client.getData("SellerId"));
        orderDetail.setSellerName(client.getData("SellerName"));
        orderDetail.setSellerMemo(client.getData("SellerMemo"));
        orderDetail.setSafeReserved(client.getData("SafeReserved"));
        orderDetail.setValidPeriod(client.getData("ValidPeriod"));

        OpList settingList = (OpList) client.getOpListMap().get("SettingList");
        List<TradeTurnover> SettingList = new ArrayList<>();
        int iNum = settingList.getOpInfoNum();
        for (int index = 0; index < iNum; index++) {
            SettingList.add(this.prepareTradeTurnover(settingList.getOpInfo(index)));
        }
        return orderDetail;
    }

    public TradeTurnover prepareTradeTurnover(OpInfo opInfo) {
        TradeTurnover turnover = new TradeTurnover();
        turnover.setPayNo(opInfo.getValueByName("PayNo"));
        turnover.setMerTranSerialNo(opInfo.getValueByName("MerTranSerialNo"));
        turnover.setPayDate(opInfo.getValueByName("PayDate"));
        turnover.setPayType(opInfo.getValueByName("PayType"));
        turnover.setPayAmt(opInfo.getValueByName("PayAmt"));
        turnover.setPayState(opInfo.getValueByName("PayState"));
        turnover.setPayMemo(opInfo.getValueByName("PayMemo"));
        turnover.setPayBankMemo(opInfo.getValueByName("PayBankMemo"));
        turnover.setChannelType(opInfo.getValueByName("ChannelType"));
        turnover.setPayBankName(opInfo.getValueByName("PayBankName"));
        return turnover;
    }
}

