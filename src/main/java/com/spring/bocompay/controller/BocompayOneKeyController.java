package com.spring.bocompay.controller;

import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.BocomDataMap;
import com.bocom.bocompay.BocompaySetting;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @author: Katherine
 * @create: 2018/4/27 14:46
 */
@RestController
@RequestMapping("bocompay/onekey")
public class BocompayOneKeyController extends BaseController {

    /**
     * @description: 一键支付付款账户签约<    BPAYPY4161>
     * @author: Katherine
     * @create: 2018/4/27 16:16
     */
    @PostMapping(value = "BPAYPY4161_agree")
    public Object BPAYPY4161_agree(HttpServletRequest request, RedirectAttributes attrs) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            return new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            client.setHead("TranCode", request.getParameter("TranCode"));
            client.setHead("MerPtcId", request.getParameter("MerPtcId"));
            client.setHead("TranDate", request.getParameter("TranDate"));
            client.setHead("TranTime", request.getParameter("TranTime"));

            client.setData("MerAgreeNo", request.getParameter("MerAgreeNo"));
            client.setData("AccName", request.getParameter("AccName"));
            client.setData("CertType", request.getParameter("CertType"));
            client.setData("CertNo", request.getParameter("CertNo"));
            client.setData("MerComment", request.getParameter("MerComment"));
            client.setData("OnekeyTranType", request.getParameter("OnekeyTranType"));
            client.setData("NetType", request.getParameter("NetType"));

            String signData = client.toSignString(MerCertID);
            attrs.addAttribute("signData", signData);
            return new RedirectView(BocompaySetting.AgreeURL);
        }
    }

    /**
     * @description: 一键支付签约查询<BPAYPY4162>
     * @author: Katherine
     * @create: 2018/4/27 16:16
     */
    @PostMapping(value = "BPAYPY4162_queryAgree")
    public ResponseMessage BPAYPY4162_queryAgree(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);
            client.setData("MerAgreeNo", request.getParameter("MerAgreeNo"));

            Map<String, Object> responseData = this.toResponseDataWithOneKeyPay(client);
            responseMessage = this.responseRecWithTranType(client, MerCertID, TranCode, "ONEKEY", responseData);
        }
        return responseMessage;
    }


    /**
     * @description: 一键支付协议撤约<BPAYPY4163>
     * @author: Katherine
     * @create: 2018/4/27 16:17
     */
    @PostMapping(value = "BPAYPY4163_unAgree")
    public ResponseMessage BPAYPY4163_unAgree(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();

        int ret = client.initialize(xmlConfigPath); //
        if (ret != 0) {
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);

            client.setData("AgreeNo", request.getParameter("AgreeNo"));

            responseMessage = this.responseRecWithTranType(client, MerCertID, TranCode, "ONEKEY", null);
        }
        return responseMessage;
    }

    /**
     * @description: 一键支付短信发送<BPAYPY4164>
     * @author: Katherine
     * @create: 2018/4/27 16:44
     */
    @PostMapping(value = "BPAYPY4164_sendMessage")
    public ResponseMessage BPAYPY4164_sendMessage(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath); //
        if (ret != 0) {                                                                 //
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);

            client.setData("AgreeNo", request.getParameter("AgreeNo"));
            client.setData("MerAgreeNo", request.getParameter("MerAgreeNo"));
            client.setData("Amount", request.getParameter("Amount"));
            client.setData("ApplyTime", request.getParameter("ApplyTime"));

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("SessionID", client.getData("SessionID"));
            responseData.put("Mobile", client.getData("Mobile"));
            responseMessage = this.responseRecWithTranType(client, MerCertID, TranCode, "ONEKEY", responseData);
        }
        return responseMessage;
    }

    @PostMapping(value = "BPAYPY4166_pay0_singlePayByMessage")
    public ResponseMessage BPAYPY4166_pay0_singlePayByMessage(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath); //
        if (ret != 0) {                                                                 //
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);

            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("SafeReserved", request.getParameter("SafeReserved"));

            BocomDataMap PtcInfo = new BocomDataMap();
            PtcInfo.setData("SubMerPtcId", request.getParameter("SubMerPtcId"));
            client.setData("PtcInfo", PtcInfo.toString());

            BocomDataMap BusiInfo = new BocomDataMap();
            BusiInfo.setData("MerOrderNo", request.getParameter("MerOrderNo"));
            client.setData("BusiInfo", BusiInfo.toString());

            BocomDataMap OnekeyInf = new BocomDataMap();
            OnekeyInf.setData("DynPassword", request.getParameter("DynPassword"));
            OnekeyInf.setData("SessionID", request.getParameter("SessionID"));
            OnekeyInf.setData("AgreeNo", request.getParameter("AgreeNo"));
            OnekeyInf.setData("MerAgreeNo", request.getParameter("MerAgreeNo"));
            client.setData("OnekeyInf", OnekeyInf.toString());

            BocomDataMap UserInfo = new BocomDataMap();
            UserInfo.setData("BuyerId", request.getParameter("BuyerId"));
            UserInfo.setData("BuyerName", request.getParameter("BuyerName"));
            UserInfo.setData("SellerId", request.getParameter("SellerId"));
            UserInfo.setData("SellerName", request.getParameter("SellerName"));
            client.setData("UserInfo", UserInfo.toString());

            BocomDataMap GoodsInfo = new BocomDataMap();
            GoodsInfo.setData("GoodsName", request.getParameter("GoodsName"));
            GoodsInfo.setData("GoodsTxt", request.getParameter("GoodsTxt"));
            GoodsInfo.setData("GoodsDesc", request.getParameter("GoodsDesc"));
            client.setData("GoodsInfo", GoodsInfo.toString());

            BocomDataMap TranInfo = new BocomDataMap();
            TranInfo.setData("TranModeId", request.getParameter("TranModeId"));
            TranInfo.setData("TranAmt", request.getParameter("TranAmt"));
            TranInfo.setData("TranCry", request.getParameter("TranCry"));
            client.setData("TranInfo", TranInfo.toString());

            BocomDataMap ChannelInfo = new BocomDataMap();
            ChannelInfo.setData("ChannelApi", request.getParameter("ChannelApi"));
            ChannelInfo.setData("ChannelInst", request.getParameter("ChannelInst"));
            client.setData("ChannelInfo", ChannelInfo.toString());

            BocomDataMap MemoInfo = new BocomDataMap();
            MemoInfo.setData("BuyerMemo", request.getParameter("BuyerMemo"));
            MemoInfo.setData("SellerMemo", request.getParameter("SellerMemo"));
            MemoInfo.setData("PlatMemo", request.getParameter("PlatMemo"));
            MemoInfo.setData("PayMemo", request.getParameter("PayMemo"));
            client.setData("MemoInfo", MemoInfo.toString());

            Map<String, Object> responseData = this.toResponseDataWithOneKeyPay(client);
            responseMessage = this.responseRec(client, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }

    /**
     * @description: 一键支付并单短信付款<BPAYPY4167>
     * @author: Katherine
     * @create: 2018/4/27 17:31
     */
    @PostMapping(value = "BPAYPY4167_pay1_multiPayByMessage")
    public ResponseMessage BPAYPY4167_pay1_multiPayByMessage(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath); //
        if (ret != 0) {                                                                 //
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);

            client.setData("TotalCount", request.getParameter("TotalCount"));
            client.setData("ChannelApi", request.getParameter("ChannelApi"));
            client.setData("ChannelInst", request.getParameter("ChannelInst"));
            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("SafeReserved", request.getParameter("SafeReserved"));
            client.setData("PayMemo", request.getParameter("PayMemo"));

            BocomDataMap OnekeyInf = new BocomDataMap();
            OnekeyInf.setData("DynPassword", request.getParameter("DynPassword"));
            OnekeyInf.setData("SessionID", request.getParameter("SessionID"));
            OnekeyInf.setData("AgreeNo", request.getParameter("AgreeNo"));
            OnekeyInf.setData("MerAgreeNo", request.getParameter("MerAgreeNo"));
            client.setData("OnekeyInf", OnekeyInf.toString());

            String strHiddenRealCount = request.getParameter("HiddenRealCount");
            int intHiddenRealCount = Integer.parseInt(strHiddenRealCount);
            String forInnerInto = "";
            for (int i = 1; i <= intHiddenRealCount; i++) {
                BocomDataMap PtcInfo = new BocomDataMap();
                BocomDataMap BusiInfoMap = new BocomDataMap();
                BocomDataMap UserInfoMap = new BocomDataMap();
                BocomDataMap GoodsInfoMap = new BocomDataMap();
                BocomDataMap TranInfoMap = new BocomDataMap();
                BocomDataMap MemoInfoMap = new BocomDataMap();
                if (i == 1) {
                    PtcInfo.setData("SubMerPtcId", request.getParameter("SubMerPtcId"));
                    BusiInfoMap.setData("MerOrderNo", request.getParameter("MerOrderNo"));
                    UserInfoMap.setData("BuyerId", request.getParameter("BuyerId"));
                    UserInfoMap.setData("BuyerName", request.getParameter("BuyerName"));
                    UserInfoMap.setData("SellerId", request.getParameter("SellerId"));
                    UserInfoMap.setData("SellerName", request.getParameter("SellerName"));
                    GoodsInfoMap.setData("GoodsName", request.getParameter("GoodsName"));
                    GoodsInfoMap.setData("GoodsTxt", request.getParameter("GoodsTxt"));
                    GoodsInfoMap.setData("GoodsDesc", request.getParameter("GoodsDesc"));
                    TranInfoMap.setData("TranModeId", request.getParameter("TranModeId"));
                    TranInfoMap.setData("TranAmt", request.getParameter("TranAmt"));
                    TranInfoMap.setData("TranCry", request.getParameter("TranCry"));

                    MemoInfoMap.setData("BuyerMemo", request.getParameter("BuyerMemo"));
                    MemoInfoMap.setData("SellerMemo", request.getParameter("SellerMemo"));
                    MemoInfoMap.setData("PlatMemo", request.getParameter("PlatMemo"));
                } else {
                    PtcInfo.setData("SubMerPtcId", request.getParameter("SubMerPtcId" + i));
                    BusiInfoMap.setData("MerOrderNo", request.getParameter("MerOrderNo" + i));
                    UserInfoMap.setData("BuyerId", request.getParameter("BuyerId" + i));
                    UserInfoMap.setData("BuyerName", request.getParameter("BuyerName" + i));
                    UserInfoMap.setData("SellerId", request.getParameter("SellerId" + i));
                    UserInfoMap.setData("SellerName", request.getParameter("SellerName" + i));
                    GoodsInfoMap.setData("GoodsName", request.getParameter("GoodsName" + i));
                    GoodsInfoMap.setData("GoodsTxt", request.getParameter("GoodsTxt" + i));
                    GoodsInfoMap.setData("GoodsDesc", request.getParameter("GoodsDesc" + i));
                    TranInfoMap.setData("TranModeId", request.getParameter("TranModeId" + i));
                    TranInfoMap.setData("TranAmt", request.getParameter("TranAmt" + i));
                    TranInfoMap.setData("TranCry", request.getParameter("TranCry" + i));

                    MemoInfoMap.setData("BuyerMemo", request.getParameter("BuyerMemo" + i));
                    MemoInfoMap.setData("SellerMemo", request.getParameter("SellerMemo" + i));
                    MemoInfoMap.setData("PlatMemo", request.getParameter("PlatMemo" + i));
                }
                forInnerInto += "<OrderInfo><PtcInfo>" + PtcInfo.toString() + "</PtcInfo>"
                        + "<BusiInfo>" + BusiInfoMap.toString() + "</BusiInfo>"
                        + "<UserInfo>" + UserInfoMap.toString() + "</UserInfo>"
                        + "<GoodsInfo>" + GoodsInfoMap.toString() + "</GoodsInfo>"
                        + "<TranInfo>" + TranInfoMap.toString() + "</TranInfo>"
                        + "<MemoInfo>" + MemoInfoMap.toString() + "</MemoInfo></OrderInfo>";
            }
            client.setData("OrderList", forInnerInto);

            Map<String, Object> responseData = this.toResponseDataWithOneKeyPay(client);
            responseMessage = this.responseRec(client, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }

    /**
     * @description: 一键支付单笔直接付款<BPAYPY4168>
     * @author: Katherine
     * @create: 2018/4/27 17:32
     */
    @PostMapping(value = "BPAYPY4168_pay0_singlePayDirect")
    public ResponseMessage BPAYPY4168_pay0_singlePayDirect(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath); //
        if (ret != 0) {                                                                 //
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);

            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("SafeReserved", request.getParameter("SafeReserved"));

            BocomDataMap map1 = new BocomDataMap();
            map1.setData("SubMerPtcId", request.getParameter("SubMerPtcId"));
            client.setData("PtcInfo", map1.toString());

            BocomDataMap map2 = new BocomDataMap();
            map2.setData("MerOrderNo", request.getParameter("MerOrderNo"));
            client.setData("BusiInfo", map2.toString());

            BocomDataMap map8 = new BocomDataMap();
            map8.setData("CardNo", request.getParameter("CardNo"));
            map8.setData("AccName", request.getParameter("AccName"));
            map8.setData("CardExpDate", request.getParameter("CardExpDate"));
            map8.setData("AgreeNo", request.getParameter("AgreeNo"));
            map8.setData("MerAgreeNo", request.getParameter("MerAgreeNo"));
            client.setData("OnekeyInf", map8.toString());

            BocomDataMap map3 = new BocomDataMap();
            map3.setData("BuyerId", request.getParameter("BuyerId"));
            map3.setData("BuyerName", request.getParameter("BuyerName"));
            map3.setData("SellerId", request.getParameter("SellerId"));
            map3.setData("SellerName", request.getParameter("SellerName"));
            client.setData("UserInfo", map3.toString());

            BocomDataMap map4 = new BocomDataMap();
            map4.setData("GoodsName", request.getParameter("GoodsName"));
            map4.setData("GoodsTxt", request.getParameter("GoodsTxt"));
            map4.setData("GoodsDesc", request.getParameter("GoodsDesc"));
            client.setData("GoodsInfo", map4.toString());

            BocomDataMap map5 = new BocomDataMap();
            map5.setData("TranModeId", request.getParameter("TranModeId"));
            map5.setData("TranAmt", request.getParameter("TranAmt"));
            map5.setData("TranCry", request.getParameter("TranCry"));
            client.setData("TranInfo", map5.toString());

            BocomDataMap map6 = new BocomDataMap();
            map6.setData("ChannelApi", request.getParameter("ChannelApi"));
            map6.setData("ChannelInst", request.getParameter("ChannelInst"));
            client.setData("ChannelInfo", map6.toString());

            BocomDataMap map7 = new BocomDataMap();
            map7.setData("BuyerMemo", request.getParameter("BuyerMemo"));
            map7.setData("SellerMemo", request.getParameter("SellerMemo"));
            map7.setData("PlatMemo", request.getParameter("PlatMemo"));
            map7.setData("PayMemo", request.getParameter("PayMemo"));
            client.setData("MemoInfo", map7.toString());

            Map<String, Object> responseData = this.toResponseDataWithOneKeyPay(client);
            responseMessage = this.responseRec(client, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }

    /**
     * @description: 一键支付并单直接付款<BPAYPY4169>
     * @author: Katherine
     * @create: 2018/4/27 17:32
     */
    @PostMapping(value = "BPAYPY4169_pay1_multiPayDirect")
    public ResponseMessage BPAYPY4169_pay1_multiPayDirect(HttpServletRequest request) {
        ResponseMessage responseMessage = null;
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath); //
        if (ret != 0) {                                                                 //
            responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
        } else {
            String TranCode = request.getParameter("TranCode");
            client = this.initializeRequest(request, client);

            client.setData("TotalCount", request.getParameter("TotalCount"));
            client.setData("ChannelApi", request.getParameter("ChannelApi"));
            client.setData("ChannelInst", request.getParameter("ChannelInst"));
            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("SafeReserved", request.getParameter("SafeReserved"));
            client.setData("PayMemo", request.getParameter("PayMemo"));

            BocomDataMap map8 = new BocomDataMap();
            map8.setData("CardNo", request.getParameter("CardNo"));
            map8.setData("AccName", request.getParameter("AccName"));
            map8.setData("CardExpDate", request.getParameter("CardExpDate"));
            map8.setData("AgreeNo", request.getParameter("AgreeNo"));
            map8.setData("MerAgreeNo", request.getParameter("MerAgreeNo"));
            client.setData("OnekeyInf", map8.toString());

            String strHiddenRealCount = request.getParameter("HiddenRealCount");
            int intHiddenRealCount = Integer.parseInt(strHiddenRealCount);
            String forInnerInto = "";
            for (int i = 1; i <= intHiddenRealCount; i++) {
                BocomDataMap PtcInfo = new BocomDataMap();
                BocomDataMap BusiInfoMap = new BocomDataMap();
                BocomDataMap UserInfoMap = new BocomDataMap();
                BocomDataMap GoodsInfoMap = new BocomDataMap();
                BocomDataMap TranInfoMap = new BocomDataMap();
                BocomDataMap MemoInfoMap = new BocomDataMap();
                if (i == 1) {
                    PtcInfo.setData("SubMerPtcId", request.getParameter("SubMerPtcId"));
                    BusiInfoMap.setData("MerOrderNo", request.getParameter("MerOrderNo"));
                    UserInfoMap.setData("BuyerId", request.getParameter("BuyerId"));
                    UserInfoMap.setData("BuyerName", request.getParameter("BuyerName"));
                    UserInfoMap.setData("SellerId", request.getParameter("SellerId"));
                    UserInfoMap.setData("SellerName", request.getParameter("SellerName"));
                    GoodsInfoMap.setData("GoodsName", request.getParameter("GoodsName"));
                    GoodsInfoMap.setData("GoodsTxt", request.getParameter("GoodsTxt"));
                    GoodsInfoMap.setData("GoodsDesc", request.getParameter("GoodsDesc"));
                    TranInfoMap.setData("TranModeId", request.getParameter("TranModeId"));
                    TranInfoMap.setData("TranAmt", request.getParameter("TranAmt"));
                    TranInfoMap.setData("TranCry", request.getParameter("TranCry"));

                    MemoInfoMap.setData("BuyerMemo", request.getParameter("BuyerMemo"));
                    MemoInfoMap.setData("SellerMemo", request.getParameter("SellerMemo"));
                    MemoInfoMap.setData("PlatMemo", request.getParameter("PlatMemo"));
                } else {
                    PtcInfo.setData("SubMerPtcId", request.getParameter("SubMerPtcId" + i));
                    BusiInfoMap.setData("MerOrderNo", request.getParameter("MerOrderNo" + i));
                    UserInfoMap.setData("BuyerId", request.getParameter("BuyerId" + i));
                    UserInfoMap.setData("BuyerName", request.getParameter("BuyerName" + i));
                    UserInfoMap.setData("SellerId", request.getParameter("SellerId" + i));
                    UserInfoMap.setData("SellerName", request.getParameter("SellerName" + i));
                    GoodsInfoMap.setData("GoodsName", request.getParameter("GoodsName" + i));
                    GoodsInfoMap.setData("GoodsTxt", request.getParameter("GoodsTxt" + i));
                    GoodsInfoMap.setData("GoodsDesc", request.getParameter("GoodsDesc" + i));
                    TranInfoMap.setData("TranModeId", request.getParameter("TranModeId" + i));
                    TranInfoMap.setData("TranAmt", request.getParameter("TranAmt" + i));
                    TranInfoMap.setData("TranCry", request.getParameter("TranCry" + i));

                    MemoInfoMap.setData("BuyerMemo", request.getParameter("BuyerMemo" + i));
                    MemoInfoMap.setData("SellerMemo", request.getParameter("SellerMemo" + i));
                    MemoInfoMap.setData("PlatMemo", request.getParameter("PlatMemo" + i));
                }
                forInnerInto += "<OrderInfo><PtcInfo>" + PtcInfo.toString() + "</PtcInfo>"
                        + "<BusiInfo>" + BusiInfoMap.toString() + "</BusiInfo>"
                        + "<UserInfo>" + UserInfoMap.toString() + "</UserInfo>"
                        + "<GoodsInfo>" + GoodsInfoMap.toString() + "</GoodsInfo>"
                        + "<TranInfo>" + TranInfoMap.toString() + "</TranInfo>"
                        + "<MemoInfo>" + MemoInfoMap.toString() + "</MemoInfo></OrderInfo>";
            }
            client.setData("OrderList", forInnerInto);

            Map<String, Object> responseData = this.toResponseDataWithOneKeyPay(client);
            responseMessage = this.responseRec(client, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }

    public Map<String, Object> toResponseDataWithOneKeyPay(BocomClient client) {
        Map<String, Object> map = new HashMap<>();
        map.put("MerTranSerialNo", client.getData("MerTranSerialNo"));
        map.put("TranState", client.getData("TranState"));
        map.put("Amount", client.getData("Amount"));
        map.put("Currency", client.getData("Currency"));
        map.put("Bankcomment", client.getData("Bankcomment"));
        return map;
    }
}
