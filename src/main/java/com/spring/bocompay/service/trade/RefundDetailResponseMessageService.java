package com.spring.bocompay.service.trade;

import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.OpInfo;
import com.bocom.bocompay.OpList;
import com.spring.bocompay.domain.trade.RefundDetailResponseMessage;
import com.spring.bocompay.domain.trade.RefundTurnover;
import com.spring.bocompay.service.BaseService;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/26 11:37
 */
@Service
public class RefundDetailResponseMessageService extends BaseService {

    /**
     * @param client       退款明细响应报文
     * @param refundDetail 退款明细
     * @description: 填充退款明细并返回成功响应
     * @author: Katherine
     * @create: 2018/4/26 11:39
     */
    public ResponseMessage fillRefundDetail(BocomClient client, RefundDetailResponseMessage refundDetail) {
        refundDetail.setMerPtcId(client.getData("MerPtcId"));
        refundDetail.setMerPtcName(client.getData("MerPtcName"));
        refundDetail.setOpenBra(client.getData("OpenBra"));
        refundDetail.setMerOrderNo(client.getData("MerOrderNo"));
        refundDetail.setGmtCreate(client.getData("GmtCreate"));
        refundDetail.setGmtModify(client.getData("GmtModify"));
        refundDetail.setTradeOrderNo(client.getData("TradeOrderNo"));
        refundDetail.setPayNo(client.getData("PayNo"));

        OpList settingList = (OpList) client.getOpListMap().get("SettingList");
        List<RefundTurnover> SettingList = new ArrayList<>();
        int iNum = settingList.getOpInfoNum();
        for (int index = 0; index < iNum; index++) {
            SettingList.add(this.prepareRefundTurnover(settingList.getOpInfo(index)));
        }

        return this.success(refundDetail);
    }

    public RefundTurnover prepareRefundTurnover(OpInfo opInfo) {
        RefundTurnover refundTurnover = new RefundTurnover();
        refundTurnover.setMerTranSerialNo(opInfo.getValueByName("MerTranSerialNo"));
        refundTurnover.setWaterNo(opInfo.getValueByName("WaterNo"));
        refundTurnover.setRefundState(opInfo.getValueByName("RefundState"));
        refundTurnover.setChannelApi(opInfo.getValueByName("ChannelApi"));
        refundTurnover.setTranAmt(opInfo.getValueByName("TranAmt"));
        refundTurnover.setTranCry(opInfo.getValueByName("TranCry"));
        refundTurnover.setReturnFee(opInfo.getValueByName("ReturnFee"));
        refundTurnover.setRefundAccNo(opInfo.getValueByName("RefundAccNo"));
        refundTurnover.setRefundAccName(opInfo.getValueByName("RefundAccName"));
        refundTurnover.setRecAccNo(opInfo.getValueByName("RecAccNo"));
        refundTurnover.setRecAccName(opInfo.getValueByName("RecAccName"));
        refundTurnover.setRecBankName(opInfo.getValueByName("RecBankName"));
        refundTurnover.setRecBranchId(opInfo.getValueByName("RecBranchId"));
        refundTurnover.setBatchNo(opInfo.getValueByName("BatchNo"));
        refundTurnover.setRefundMemo(opInfo.getValueByName("RefundMemo"));

        return refundTurnover;
    }
}














