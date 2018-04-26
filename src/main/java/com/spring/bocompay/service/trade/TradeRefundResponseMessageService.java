package com.spring.bocompay.service.trade;

import com.bocom.bocompay.BocomClient;
import com.spring.bocompay.domain.trade.TradeRefundResponseMessage;
import com.spring.bocompay.service.BaseService;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/26 15:34
 */
@Service
public class TradeRefundResponseMessageService extends BaseService {
    public ResponseMessage fillTradeRefund(BocomClient client, TradeRefundResponseMessage tradeRefund) {
        tradeRefund.setTranRspCode(client.getData("TranRspCode"));
        tradeRefund.setTranRspMsg(client.getData("TranRspMsg"));
        tradeRefund.setTranStt(client.getData("TranStt"));
        tradeRefund.setMerOrderNo(client.getData("MerOrderNo"));
        tradeRefund.setRefundOrderNo(client.getData("RefundOrderNo"));
        tradeRefund.setRefundAmt(client.getData("RefundAmt"));
        tradeRefund.setRefundCry(client.getData("RefundCry"));
        tradeRefund.setMerTranSerialNo(client.getData("MerTranSerialNo"));
        tradeRefund.setChannelType(client.getData("ChannelType"));

        return this.success(tradeRefund);
    }
}
