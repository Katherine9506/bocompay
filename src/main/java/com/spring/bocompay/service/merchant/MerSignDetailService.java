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
 * @create: 2018/4/26 18:34
 */
@Service
public class MerSignDetailService extends BaseService {

    /**
     * @param
     * @description: TODO
     * @author: Katherine
     * @create: 2018/4/26 18:34
     */
    public ResponseMessage fillMerSignDetail(BocomClient client, MerSignDetail merSignDetail) {
        Map opInfoMap = client.getOpInfoMap();
        //MerBaseInfo  商户基本信息
        merSignDetail.setMerBaseInfo(this.prepareMerBaseInfo((OpInfo) opInfoMap.get("MerBaseInfo")));
        //MerBusInfo  商户业务信息
        merSignDetail.setMerBusInfo(this.prepareMerBusInfo((OpInfo) opInfoMap.get("MerBusInfo")));
        //MerPtcInfo  商户协议信息
        merSignDetail.setMerPtcInfo(this.prepareMerPtcInfo((OpInfo) opInfoMap.get("MerPtcInfo")));

        Map opListMap = client.getOpListMap();
        //ExtMerIdList  外部商户号列表
        merSignDetail.setExtMerIdList(this.prepareExtMerIdList((OpList) opListMap.get("ExtMerIdList")));
        //MerParaList  商户业务参数列表
        merSignDetail.setMerParaList(this.prepareMerParaList((OpList) opListMap.get("MerParaList")));
        //FeeList  手续费模式列表
        merSignDetail.setFeeList(this.prepareFeeList((OpList) opListMap.get("FeeList")));
        //MerAccList  帐号信息列表
        merSignDetail.setMerAccList(this.prepareMerAccList((OpList) opListMap.get("MerAccList")));

        return this.success(merSignDetail);
    }

    private Map<String, String> prepareMerBaseInfo(OpInfo merBaseInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("MerId", merBaseInfo.getValueByName("MerId"));
        map.put("CstNo", merBaseInfo.getValueByName("CstNo"));
        map.put("HostNo", merBaseInfo.getValueByName("HostNo"));
        map.put("EnterNameCN", merBaseInfo.getValueByName("EnterNameCN"));
        map.put("EnterNameEN", merBaseInfo.getValueByName("EnterNameEN"));
        map.put("CertType", merBaseInfo.getValueByName("CertType"));
        map.put("CertNo", merBaseInfo.getValueByName("CertNo"));
        map.put("OpenBra", merBaseInfo.getValueByName("OpenBra"));
        map.put("OpenNode", merBaseInfo.getValueByName("OpenNode"));
        return map;
    }

    private Map<String, String> prepareMerBusInfo(OpInfo merBusInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("MerchNameCN", merBusInfo.getValueByName("MerchNameCN"));
        map.put("MerchNameEN", merBusInfo.getValueByName("MerchNameEN"));
        map.put("ICP", merBusInfo.getValueByName("ICP"));
        map.put("MerchType", merBusInfo.getValueByName("MerchType"));
        map.put("WebsiteURL", merBusInfo.getValueByName("WebsiteURL"));
        map.put("MerchAddr", merBusInfo.getValueByName("MerchAddr"));
        map.put("ContacterName", merBusInfo.getValueByName("ContacterName"));
        map.put("PhoneNo", merBusInfo.getValueByName("PhoneNo"));
        map.put("MobileNo", merBusInfo.getValueByName("MobileNo"));
        map.put("EmailAddr", merBusInfo.getValueByName("EmailAddr"));
        map.put("MerchDetailInfo", merBusInfo.getValueByName("MerchDetailInfo"));
        map.put("MerchMemo", merBusInfo.getValueByName("MerchMemo"));
        return map;
    }

    private Map<String, String> prepareMerPtcInfo(OpInfo merPtcInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("MerPtcId", merPtcInfo.getValueByName("MerPtcId"));
        map.put("TranType", merPtcInfo.getValueByName("TranType"));
        map.put("TotalLayer", merPtcInfo.getValueByName("TotalLayer"));
        map.put("TranMode", merPtcInfo.getValueByName("TranMode"));
        map.put("SubPtcCreMod", merPtcInfo.getValueByName("SubPtcCreMod"));
        map.put("FeeChgObj", merPtcInfo.getValueByName("FeeChgObj"));
        map.put("FeePeriod", merPtcInfo.getValueByName("FeePeriod"));
        map.put("ReturnFeeFlg", merPtcInfo.getValueByName("ReturnFeeFlg"));
        map.put("FeeGroupId", merPtcInfo.getValueByName("FeeGroupId"));
        map.put("ReturnURL", merPtcInfo.getValueByName("ReturnURL"));
        map.put("NotifyURL", merPtcInfo.getValueByName("NotifyURL"));
        map.put("PtcStatus", merPtcInfo.getValueByName("PtcStatus"));
        map.put("PtcMemo", merPtcInfo.getValueByName("PtcMemo"));
        return map;
    }

    private List<ExtMerIdInfo> prepareExtMerIdList(OpList extMerIdList) {
        int iNum = extMerIdList.getOpInfoNum();
        List<ExtMerIdInfo> list = new ArrayList<>();
        for (int index = 0; index < iNum; index++) {
            list.add(new ExtMerIdInfo(
                    extMerIdList.getInfoValueByName(index, "ExtMerId"),
                    extMerIdList.getInfoValueByName(index, "InstType")
            ));
        }
        return list;
    }

    private List<MerParaInfo> prepareMerParaList(OpList merParaList) {
        int iNum = merParaList.getOpInfoNum();
        List<MerParaInfo> list = new ArrayList<>();
        for (int index = 0; index < iNum; index++) {
            list.add(new MerParaInfo(
                    merParaList.getInfoValueByName(index, "MerParType"),
                    merParaList.getInfoValueByName(index, "MerParValue"),
                    merParaList.getInfoValueByName(index, "MerParClass"),
                    merParaList.getInfoValueByName(index, "MerParMemo")
            ));
        }
        return list;
    }

    private List<FeeInfo> prepareFeeList(OpList feeList) {
        int iNum = feeList.getOpInfoNum();
        List<FeeInfo> list = new ArrayList<>();
        for (int index = 0; index < iNum; index++) {
            list.add(new FeeInfo(
                    feeList.getInfoValueByName(index, "FeeGroupId"),
                    feeList.getInfoValueByName(index, "FeeGroupName"),
                    feeList.getInfoValueByName(index, "TranType"),
                    feeList.getInfoValueByName(index, "PayType"),
                    feeList.getInfoValueByName(index, "AccType"),
                    feeList.getInfoValueByName(index, "FeeModeId"),
                    feeList.getInfoValueByName(index, "FeeModeName"),
                    feeList.getInfoValueByName(index, "FeeModeMemo"),
                    feeList.getInfoValueByName(index, "ChargeMode"),
                    feeList.getInfoValueByName(index, "FeeRate"),
                    feeList.getInfoValueByName(index, "FeeCry"),
                    feeList.getInfoValueByName(index, "MaxFeeAmount"),
                    feeList.getInfoValueByName(index, "MinFeeAmount"),
                    feeList.getInfoValueByName(index, "FeeMemo")
            ));
        }
        return list;
    }

    private List<MerAccInfo> prepareMerAccList(OpList merAccList) {
        int iNum = merAccList.getOpInfoNum();
        List<MerAccInfo> list = new ArrayList<>();
        for (int index = 0; index < iNum; index++) {
            list.add(new MerAccInfo(
                    merAccList.getInfoValueByName(index, "Purpose"),
                    merAccList.getInfoValueByName(index, "Account"),
                    merAccList.getInfoValueByName(index, "CurrType"),
                    merAccList.getInfoValueByName(index, "AccName"),
                    merAccList.getInfoValueByName(index, "AccType"),
                    merAccList.getInfoValueByName(index, "BankId"),
                    merAccList.getInfoValueByName(index, "BranchId"),
                    merAccList.getInfoValueByName(index, "BranchName"),
                    merAccList.getInfoValueByName(index, "DeptNo"),
                    merAccList.getInfoValueByName(index, "DeptName"),
                    merAccList.getInfoValueByName(index, "AccMemo")
            ));
        }
        return list;
    }

}
