package com.spring.bocompay.service;

import com.spring.bocompay.domain.TransactionMessage;
import org.springframework.stereotype.Service;

/**
 * Created by Elena@qq.com on 2018/4/24
 * Description: 交易消息服务
 */
@Service
public class TransactionMessageService {

    /**
     * description: 处理TransactionMessage对象，填充xXXInfo字段
     *
     * @param transaction
     * @return TransactionMessage
     * creat_user: Elena@qq.com
     * creat_datetime: 2018/4/24 13:47
     */
    public TransactionMessage fillInfoFields(TransactionMessage transaction) {
        //业务信息
        transaction.getBusiInfo().setData("MerOrderNo", transaction.getMerOrderNo());
        //会员信息
        transaction.getUserInfo().setData("BuyerId", transaction.getBuyerId());
        transaction.getUserInfo().setData("BuyerName", transaction.getBuyerName());
        transaction.getUserInfo().setData("SellerId", transaction.getSellerId());
        transaction.getUserInfo().setData("SellerName", transaction.getSellerName());
        //商品信息
        transaction.getGoodsInfo().setData("GoodsName", transaction.getGoodsName());
        transaction.getGoodsInfo().setData("GoodsTxt", transaction.getGoodsTxt());
        transaction.getGoodsInfo().setData("GoodsDesc", transaction.getGoodsDesc());
        //交易信息
        transaction.getTranInfo().setData("TranModeId", transaction.getTranModeId());
        transaction.getTranInfo().setData("TranAmt", transaction.getTranAmt());
        transaction.getTranInfo().setData("TranCry", transaction.getTranCry());
        //通道信息
        transaction.getChannelInfo().setData("ChannelApi", transaction.getChannelApi());
        transaction.getChannelInfo().setData("ChannelInst", transaction.getChannelInst());
        //备注信息
        transaction.getMemoInfo().setData("BuyerMemo", transaction.getBuyerMemo());
        transaction.getMemoInfo().setData("SellerMemo", transaction.getSellerMemo());
        transaction.getMemoInfo().setData("PlatMemo", transaction.getPlatMemo());
        transaction.getMemoInfo().setData("PayMemo", transaction.getPayMemo());
        return transaction;
    }

}
