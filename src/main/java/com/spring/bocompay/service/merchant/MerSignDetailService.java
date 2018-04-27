package com.spring.bocompay.service.merchant;

import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.OpInfo;
import com.bocom.bocompay.OpList;
import com.spring.bocompay.domain.merchant.*;
import com.spring.bocompay.service.BaseService;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ResponseMessage fillMerSignDetail(BocomClient client, MerSignDetailResponseMessage merSignDetail) {
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

    private MerBaseInfo prepareMerBaseInfo(OpInfo merBaseInfo) {
        return new MerBaseInfo(
                merBaseInfo.getValueByName("MerId"),
                merBaseInfo.getValueByName("CstNo"),
                merBaseInfo.getValueByName("HostNo"),
                merBaseInfo.getValueByName("EntNameCN"),
                merBaseInfo.getValueByName("EntNameEN"),
                merBaseInfo.getValueByName("CertType"),
                merBaseInfo.getValueByName("CertNo"),
                merBaseInfo.getValueByName("OpenBra"),
                merBaseInfo.getValueByName("OpenNode")
        );
    }

    private MerBusInfo prepareMerBusInfo(OpInfo merBusInfo) {
        return new MerBusInfo(
                merBusInfo.getValueByName("MerchNameCN"),
                merBusInfo.getValueByName("MerchNameEN"),
                merBusInfo.getValueByName("ICP"),
                merBusInfo.getValueByName("MerchType"),
                merBusInfo.getValueByName("WebsiteURL"),
                merBusInfo.getValueByName("MerchAddr"),
                merBusInfo.getValueByName("ContacterName"),
                merBusInfo.getValueByName("PhoneNo"),
                merBusInfo.getValueByName("MobileNo"),
                merBusInfo.getValueByName("EmailAddr"),
                merBusInfo.getValueByName("MerchDetailInfo"),
                merBusInfo.getValueByName("MerchMemo"),
                null
        );
    }

    private MerPtcInfo prepareMerPtcInfo(OpInfo merPtcInfo) {
        return new MerPtcInfo(
                merPtcInfo.getValueByName("MerPtcId"),
                merPtcInfo.getValueByName("TranType"),
                merPtcInfo.getValueByName("TotalLayer"),
                merPtcInfo.getValueByName("TranMode"),
                merPtcInfo.getValueByName("SubPtcCreMod"),
                merPtcInfo.getValueByName("FeeChgObj"),
                merPtcInfo.getValueByName("FeePeriod"),
                merPtcInfo.getValueByName("ReturnFeeFlg"),
                merPtcInfo.getValueByName("FeeGroupId"),
                merPtcInfo.getValueByName("ReturnURL"),
                merPtcInfo.getValueByName("NotifyURL"),
                merPtcInfo.getValueByName("PtcStatus"),
                merPtcInfo.getValueByName("PtcMemo")
        );
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
