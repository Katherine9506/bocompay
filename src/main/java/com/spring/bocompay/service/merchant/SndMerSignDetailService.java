package com.spring.bocompay.service.merchant;

import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.OpInfo;
import com.bocom.bocompay.OpList;
import com.spring.bocompay.domain.merchant.*;
import com.spring.bocompay.service.BaseService;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/27 11:13
 */
@Service
public class SndMerSignDetailService extends BaseService {

    public ResponseMessage fillSndMerSignDetail(BocomClient client, SndMerSignDetailResponseMessage sndMerSignDetail) {
        //MerPtcInfoNum  二级商户签约协议数量
        sndMerSignDetail.setMerPtcInfoNum(client.getData("MerPtcInfoCount"));

        Map opInfoMap = client.getOpInfoMap();
        //ParentMerBaseInfo  一级商户基本信息
        sndMerSignDetail.setParentMerBaseInfo(this.prepareParentMerBaseInfo((OpInfo) opInfoMap.get("ParentMerBaseInfo")));
        //MerBaseInfo  商户基本信息
        sndMerSignDetail.setMerBaseInfo(this.prepareMerBaseInfo((OpInfo) opInfoMap.get("MerBaseInfo")));
        //MerBusInfo  商户业务信息
        sndMerSignDetail.setMerBusInfo(this.prepareMerBusInfo((OpInfo) opInfoMap.get("MerBusInfo")));

        //MerPtcInfoList  商户协议信息列表(二级的)
        int rebuildSize = Integer.parseInt(client.getData("RebuildSize"));//报文实际数值
        sndMerSignDetail.setMerPtcInfoList(this.prepareMerPtcInfoList(rebuildSize, client));

        return this.success(sndMerSignDetail);
    }

    public Map<String, String> prepareParentMerBaseInfo(OpInfo opInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("ParentMerPtcId", opInfo.getValueByName("ParentMerPtcId"));
        map.put("ParentEntNameCN", opInfo.getValueByName("ParentEntNameCN"));
        map.put("ParentCstNo", opInfo.getValueByName("ParentCstNo"));
        map.put("ParentMerchNameCN", opInfo.getValueByName("ParentMerchNameCN"));
        map.put("ParentPtcSta", opInfo.getValueByName("ParentPtcSta"));
        return map;
    }

    public MerBaseInfo prepareMerBaseInfo(OpInfo opInfo) {
        return new MerBaseInfo(
                opInfo.getValueByName("MerId"),
                opInfo.getValueByName("CstNo"),
                opInfo.getValueByName("HostNo"),
                opInfo.getValueByName("EntNameCN"),
                opInfo.getValueByName("EntNameEN"),
                opInfo.getValueByName("CertType"),
                opInfo.getValueByName("CertNo"),
                opInfo.getValueByName("OpenBra"),
                opInfo.getValueByName("OpenNode")
        );
    }

    public MerBusInfo prepareMerBusInfo(OpInfo opInfo) {
        return new MerBusInfo(
                opInfo.getValueByName("MerchNameCN"),
                opInfo.getValueByName("MerchNameEN"),
                opInfo.getValueByName("ICP"),
                opInfo.getValueByName("MerchType"),
                opInfo.getValueByName("WebsiteURL"),
                opInfo.getValueByName("MerchAddr"),
                opInfo.getValueByName("ContacterName"),
                opInfo.getValueByName("PhoneNo"),
                opInfo.getValueByName("MobileNo"),
                opInfo.getValueByName("EmailAddr"),
                opInfo.getValueByName("MerchDetailInfo"),
                opInfo.getValueByName("MerchMemo"),
                opInfo.getValueByName("SubMerMemId")
        );
    }

    public List prepareMerPtcInfoList(Integer rebuildSize, BocomClient client) {
        List merPtcInfoList = new ArrayList();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < rebuildSize; i++) {
            map.put("MerPtcInfo", this.prepareSubMerPtcInfo(i, client));
            Map opListMap = client.getOpListMap();
            OpList FeeList = (OpList) opListMap.get("Rebuild" + i + "FeeList");
            OpList MerAccList = (OpList) opListMap.get("Rebuild" + i + "MerAccList");
            map.put("FeeList", FeeList);
            if (FeeList != null) {
                map.put("FeeList", this.prepareFeeList((OpList) opListMap.get("Rebuild" + i + "FeeList")));
            }
            map.put("MerAccList", MerAccList);
            if (MerAccList != null) {
                map.put("MerAccList", this.prepareMerAccList((OpList) opListMap.get("Rebuild" + i + "MerAccList")));
            }
            merPtcInfoList.add(map);
        }
        return merPtcInfoList;
    }

    public SubMerPtcInfo prepareSubMerPtcInfo(Integer index, BocomClient client) {
        return new SubMerPtcInfo(
                client.getData("Rebuild" + index + "SubMerPtcId"),
                client.getData("Rebuild" + index + "FeePeriod"),
                client.getData("Rebuild" + index + "ReturnFeeFlg"),
                client.getData("Rebuild" + index + "FeeGroupId"),
                client.getData("Rebuild" + index + "PtcStatus"),
                client.getData("Rebuild" + index + "PtcMemo")
        );
    }

    public List<FeeInfo> prepareFeeList(OpList opList) {
        int iNum = opList.getOpInfoNum();
        List<FeeInfo> list = new ArrayList<>();
        for (int index = 0; index < iNum; index++) {
            list.add(new FeeInfo(
                    opList.getInfoValueByName(index, "FeeGroupId"),
                    opList.getInfoValueByName(index, "FeeGroupName"),
                    opList.getInfoValueByName(index, "TranType"),
                    opList.getInfoValueByName(index, "PayType"),
                    opList.getInfoValueByName(index, "AccType"),
                    opList.getInfoValueByName(index, "FeeModeId"),
                    opList.getInfoValueByName(index, "FeeModeName"),
                    opList.getInfoValueByName(index, "FeeModeMemo"),
                    opList.getInfoValueByName(index, "ChargeMode"),
                    opList.getInfoValueByName(index, "FeeRate"),
                    opList.getInfoValueByName(index, "FeeCry"),
                    opList.getInfoValueByName(index, "MaxFeeAmount"),
                    opList.getInfoValueByName(index, "MinFeeAmount"),
                    opList.getInfoValueByName(index, "FeeMemo")
            ));
        }
        return list;
    }

    public List<MerAccInfo> prepareMerAccList(OpList opList) {
        int iNum = opList.getOpInfoNum();
        List<MerAccInfo> list = new ArrayList<>();
        for (int index = 0; index < iNum; index++) {
            list.add(new MerAccInfo(
                    opList.getInfoValueByName(index, "Purpose"),
                    opList.getInfoValueByName(index, "Account"),
                    opList.getInfoValueByName(index, "CurrType"),
                    opList.getInfoValueByName(index, "AccName"),
                    opList.getInfoValueByName(index, "AccType"),
                    opList.getInfoValueByName(index, "BankId"),
                    opList.getInfoValueByName(index, "BranchId"),
                    opList.getInfoValueByName(index, "BranchName"),
                    opList.getInfoValueByName(index, "DeptNo"),
                    opList.getInfoValueByName(index, "DeptName"),
                    opList.getInfoValueByName(index, "AccMemo")
            ));
        }
        return list;
    }

}
