package com.spring.bocompay.controller;

import com.bocom.bocompay.BocomClient;
import com.spring.bocompay.domain.merchant.MerRetNotifyUrl;
import com.spring.bocompay.service.merchant.MerRetNotifyUrlService;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

}
