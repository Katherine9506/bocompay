package com.spring.bocompay.controller;

import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.BocomDataMap;
import com.spring.bocompay.domain.merchant.MerRetNotifyUrl;
import com.spring.bocompay.domain.merchant.MerSignDetailResponseMessage;
import com.spring.bocompay.domain.merchant.SndMerSignDetailResponseMessage;
import com.spring.bocompay.service.merchant.MerRetNotifyUrlService;
import com.spring.bocompay.service.merchant.MerSignDetailService;
import com.spring.bocompay.service.merchant.SndMerSignDetailService;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 商户管理接口
 * @author: Katherine
 * @create: 2018/4/26 16:47
 */
@RestController
@RequestMapping(value = "bocompay/merMag")
public class MerchantManageController {

    @Autowired
    private MerRetNotifyUrlService merRetNotifyUrlService;
    @Autowired
    private MerSignDetailService merSignDetailService;
    @Autowired
    private SndMerSignDetailService sndMerSignDetailService;

    /**
     * @param
     * @description: 一级商户订单通知维护<BPAYPY4020>
     * @author: Katherine
     * @create: 2018/4/26 17:14
     */
    @PostMapping(value = "merRetNotifyUrlMaintain")
    public ResponseMessage merRetNotifyUrlMaintain(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";

        BocomClient client = new BocomClient();

        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            String MerPtcId = request.getParameter("MerPtcId");
            String TranDate = request.getParameter("TranDate");
            String TranTime = request.getParameter("TranTime");

            String ReturnURL = request.getParameter("MerTranSerialNo");//前台通知地址
            String NotifyURL = request.getParameter("SubMerPtcId");//后台通知地址
            String AgreedReturnURL = request.getParameter("MerOrderNo");//一键支付账户签约前台通知地址
            String AgreedNotifyURL = request.getParameter("ConfirmType");//一键支付账户签约后台通知地址


            client.setHead("TranCode", TranCode);
            client.setHead("MerPtcId", MerPtcId);
            client.setHead("TranDate", TranDate);
            client.setHead("TranTime", TranTime);

            client.setData("ReturnURL", ReturnURL);
            client.setData("NotifyURL", NotifyURL);
            client.setData("AgreedReturnURL", AgreedReturnURL);
            client.setData("AgreedNotifyURL", AgreedNotifyURL);

            String rst = client.execute(MerCertID, TranCode);
            MerRetNotifyUrl merRetNotifyUrl = null;
            if (rst == null) {
                responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
            } else {
                merRetNotifyUrl = new MerRetNotifyUrl(
                        client.changeNull(client.getHead("RspType")),
                        client.changeNull(client.getHead("RspCode")),
                        client.changeNull(client.getHead("RspMsg")),
                        client.changeNull(client.getHead("RspDate")),
                        client.changeNull(client.getHead("RspTime"))
                );
                if ("E".equalsIgnoreCase(merRetNotifyUrl.getRspType())) {
                    responseMessage = new ResponseMessage(true, merRetNotifyUrl.getRspMsg(), 201, merRetNotifyUrl);
                } else {
                    responseMessage = merRetNotifyUrlService.fillMerRetNotifyUrl(client, merRetNotifyUrl);
                }
            }
        }
        return responseMessage;
    }

    /**
     * @description: 一级商户签约信息查询<BPAYPY4022>
     * @author: Katherine
     * @create: 2018/4/26 17:34
     */
    @PostMapping(value = "merSignDetailQuery")
    public ResponseMessage merSignDetailQuery(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";

        BocomClient client = new BocomClient();

        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            String MerPtcId = request.getParameter("MerPtcId");
            String TranDate = request.getParameter("TranDate");
            String TranTime = request.getParameter("TranTime");

            client.setHead("TranCode", TranCode);
            client.setHead("MerPtcId", MerPtcId);
            client.setHead("TranDate", TranDate);
            client.setHead("TranTime", TranTime);

            String rst = client.execute(MerCertID, TranCode);
            MerSignDetailResponseMessage merSignDetail = null;
            if (rst == null) {
                responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
            } else {
                merSignDetail = new MerSignDetailResponseMessage(
                        client.changeNull(client.getHead("RspType")),
                        client.changeNull(client.getHead("RspCode")),
                        client.changeNull(client.getHead("RspMsg")),
                        client.changeNull(client.getHead("RspDate")),
                        client.changeNull(client.getHead("RspTime"))
                );
                if ("E".equalsIgnoreCase(merSignDetail.getRspType())) {
                    responseMessage = new ResponseMessage(true, merSignDetail.getRspMsg(), 201, merSignDetail);
                } else {
                    responseMessage = merSignDetailService.fillMerSignDetail(client, merSignDetail);
                }
            }
        }
        return responseMessage;
    }

    /**
     * @description: 二级商户签约信息查询<BPAYPY4026>
     * @author: Katherine
     * @create: 2018/4/27 14:02
     */
    @PostMapping(value = "sndMerSignDetailQuery")
    public ResponseMessage sndMerSignDetailQuery(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";

        BocomClient client = new BocomClient();

        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            String MerPtcId = request.getParameter("MerPtcId");
            String TranDate = request.getParameter("TranDate");
            String TranTime = request.getParameter("TranTime");

            client.setHead("TranCode", TranCode);
            client.setHead("MerPtcId", MerPtcId);
            client.setHead("TranDate", TranDate);
            client.setHead("TranTime", TranTime);

            client.setData("SubMerPtcId", request.getParameter("SubMerPtcId"));

            String rst = client.execute(MerCertID, TranCode);
            SndMerSignDetailResponseMessage sndMerSignDetail = null;
            if (rst == null) {
                responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
            } else {
                sndMerSignDetail = new SndMerSignDetailResponseMessage(
                        client.changeNull(client.getHead("RspType")),
                        client.changeNull(client.getHead("RspCode")),
                        client.changeNull(client.getHead("RspMsg")),
                        client.changeNull(client.getHead("RspDate")),
                        client.changeNull(client.getHead("RspTime"))
                );
                if ("E".equalsIgnoreCase(sndMerSignDetail.getRspType())) {
                    responseMessage = new ResponseMessage(true, sndMerSignDetail.getRspMsg(), 201, sndMerSignDetail);
                } else {
                    responseMessage = sndMerSignDetailService.fillSndMerSignDetail(client, sndMerSignDetail);
                }
            }
        }
        return responseMessage;
    }

    @PostMapping(value = "sndMerSignAdd")
    public ResponseMessage sndMerSignAdd(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";

        BocomClient client = new BocomClient();

        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client.setHead("TranCode", TranCode);
            client.setHead("MerPtcId", request.getParameter("MerPtcId"));
            client.setHead("TranDate", request.getParameter("TranDate"));
            client.setHead("TranTime", request.getParameter("TranTime"));

            //开始报文体
            BocomDataMap MerBaseInfo = new BocomDataMap();
            MerBaseInfo.setData("CstNo", request.getParameter("CstNo"));
            MerBaseInfo.setData("HostNo", request.getParameter("HostNo"));
            MerBaseInfo.setData("EnterNameCN", request.getParameter("EnterNameCN"));
            MerBaseInfo.setData("EnterNameEN", request.getParameter("EnterNameEN"));
            MerBaseInfo.setData("CertType", request.getParameter("CertType"));
            MerBaseInfo.setData("CertNo", request.getParameter("CertNo"));
            MerBaseInfo.setData("OpenBra", request.getParameter("OpenBra"));
            MerBaseInfo.setData("OpenNode", request.getParameter("OpenNode"));
            client.setData("MerBaseInfo", MerBaseInfo.toString());

            BocomDataMap MerBusInfo = new BocomDataMap();
            MerBusInfo.setData("MerchNameCN", request.getParameter("MerchNameCN"));
            MerBusInfo.setData("MerchNameEN", request.getParameter("MerchNameEN"));
            MerBusInfo.setData("ICP", request.getParameter("ICP"));
            MerBusInfo.setData("MerchType", request.getParameter("MerchType"));
            MerBusInfo.setData("WebsiteURL", request.getParameter("WebsiteURL"));
            MerBusInfo.setData("MerchAddr", request.getParameter("MerchAddr"));
            MerBusInfo.setData("ContacterName", request.getParameter("ContacterName"));
            MerBusInfo.setData("PhoneNo", request.getParameter("PhoneNo"));
            MerBusInfo.setData("MobileNo", request.getParameter("MobileNo"));
            MerBusInfo.setData("EmailAddr", request.getParameter("EmailAddr"));
            MerBusInfo.setData("MerchDetailInfo", request.getParameter("MerchDetailInfo"));
            MerBusInfo.setData("SubMerMemId", request.getParameter("SubMerMemId"));
            MerBusInfo.setData("MerchMemo", request.getParameter("MerchMemo"));
            client.setData("MerBusInfo", MerBusInfo.toString());

            BocomDataMap MerPtcInfo = new BocomDataMap();
            MerPtcInfo.setData("FeePeriod", request.getParameter("FeePeriod"));
            MerPtcInfo.setData("ReturnFeeFlg", request.getParameter("ReturnFeeFlg"));
            MerPtcInfo.setData("FeeGroupId", request.getParameter("FeeGroupId"));
            MerPtcInfo.setData("PtcMemo", request.getParameter("PtcMemo"));
            client.setData("MerPtcInfo", MerPtcInfo.toString());

            BocomDataMap MerAccSettInfo = new BocomDataMap();
            MerAccSettInfo.setData("Purpose", request.getParameter("Purpose1"));
            MerAccSettInfo.setData("Account", request.getParameter("Account1"));
            MerAccSettInfo.setData("CurrType", request.getParameter("CurrType1"));
            MerAccSettInfo.setData("AccName", request.getParameter("AccName1"));
            MerAccSettInfo.setData("AccType", request.getParameter("AccType1"));
            MerAccSettInfo.setData("BankId", request.getParameter("BankId1"));
            MerAccSettInfo.setData("BranchId", request.getParameter("BranchId1"));
            MerAccSettInfo.setData("BranchName", request.getParameter("BranchName1"));
            MerAccSettInfo.setData("DeptNo", request.getParameter("DeptNo1"));
            MerAccSettInfo.setData("DeptName", request.getParameter("DeptName1"));
            MerAccSettInfo.setData("AccMemo", request.getParameter("AccMemo1"));
            client.setData("MerAccList.MerAccInfo", MerAccSettInfo);

            BocomDataMap MerAccRefundInfo = new BocomDataMap();
            MerAccRefundInfo.setData("Purpose", request.getParameter("Purpose2"));
            MerAccRefundInfo.setData("Account", request.getParameter("Account2"));
            MerAccRefundInfo.setData("CurrType", request.getParameter("CurrType2"));
            MerAccRefundInfo.setData("AccName", request.getParameter("AccName2"));
            MerAccRefundInfo.setData("AccType", request.getParameter("AccType2"));
            MerAccRefundInfo.setData("BankId", request.getParameter("BankId2"));
            MerAccRefundInfo.setData("BranchId", request.getParameter("BranchId2"));
            MerAccRefundInfo.setData("BranchName", request.getParameter("BranchName2"));
            MerAccRefundInfo.setData("DeptNo", request.getParameter("DeptNo2"));
            MerAccRefundInfo.setData("DeptName", request.getParameter("DeptName2"));
            MerAccRefundInfo.setData("AccMemo", request.getParameter("AccMemo2"));
            client.setData("MerAccList.MerAccInfo", MerAccRefundInfo);

            BocomDataMap MerAccFeeInfo = new BocomDataMap();
            MerAccFeeInfo.setData("Purpose", request.getParameter("Purpose3"));
            MerAccFeeInfo.setData("Account", request.getParameter("Account3"));
            MerAccFeeInfo.setData("CurrType", request.getParameter("CurrType3"));
            MerAccFeeInfo.setData("AccName", request.getParameter("AccName3"));
            MerAccFeeInfo.setData("AccType", request.getParameter("AccType3"));
            MerAccFeeInfo.setData("BankId", request.getParameter("BankId3"));
            MerAccFeeInfo.setData("BranchId", request.getParameter("BranchId3"));
            MerAccFeeInfo.setData("BranchName", request.getParameter("BranchName3"));
            MerAccFeeInfo.setData("DeptNo", request.getParameter("DeptNo3"));
            MerAccFeeInfo.setData("DeptName", request.getParameter("DeptName3"));
            MerAccFeeInfo.setData("AccMemo", request.getParameter("AccMemo3"));
            client.setData("MerAccList.MerAccInfo", MerAccFeeInfo);

            String rst = client.execute(MerCertID, TranCode);

            if (rst == null) {
                responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
            } else {
                if ("E".equalsIgnoreCase(client.getHead("RspType"))) {
                    responseMessage = new ResponseMessage(true, client.getHead("RspMsg"), 201, null);
                } else {
                    Map<String, String> rtnMap = new HashMap<>();
                    rtnMap.put("RspType", client.getHead("RspType"));
                    rtnMap.put("RspCode", client.getHead("RspCode"));
                    rtnMap.put("RspMsg", client.getHead("RspMsg"));
                    rtnMap.put("RspDate", client.getHead("RspDate"));
                    rtnMap.put("RspTime", client.getHead("RspTime"));
                    rtnMap.put("MerPtcId", client.getData("MerPtcId"));
                    rtnMap.put("SubMerPtcId", client.getData("SubMerPtcId"));
                    responseMessage = new ResponseMessage(true, "成功", 200, rtnMap);
                }
            }
        }
        return responseMessage;
    }

}
