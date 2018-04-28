package com.spring.bocompay.controller;


import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.BocomDataMap;
import com.bocom.bocompay.BocompaySetting;
import com.spring.bocompay.domain.trade.*;
import com.spring.bocompay.service.trade.*;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @description: 交易类接口
 * @author: Katherine
 * @create: 2018/4/25 17:37
 */
@RestController
@RequestMapping("bocompay/trade")
public class BocompayTradeController extends BaseController {

    @Autowired
    private TransactionMessageService transactionMessageService;
    @Autowired
    private OrderDetailResponseMessageService orderDetailService;
    @Autowired
    private RefundDetailResponseMessageService refundDetailService;
    @Autowired
    private TradeRefundResponseMessageService tradeRefundService;
    @Autowired
    private TradeConfirmResponseMessageService tradeConfirmService;

    /**
     * @param transaction 订单参数
     * @param attrs       重定向参数
     * @description: 单笔订单创建并付款<BPAYPY4101>
     * @author: Katherine
     * @create: 2018/4/26 9:57
     */
    @PostMapping(value = "singleLayerSingleOrderCreateAndPay")
    public RedirectView postSingleLayerSingleOrderCreateAndPay(TransactionMessage transaction, RedirectAttributes attrs) {
        transaction = transactionMessageService.fillInfoFields(transaction);

        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            System.out.println("初始化失败,错误信息：" + client.getLastErr());
        }
        client = prepareBocomClient(client, transaction);
        String signData = client.toSignString(transaction.getMerCertID());//密文

        attrs.addAttribute("signData", signData);
        return new RedirectView(BocompaySetting.BpayOrderPayURL);
    }

    /**
     * @param transaction 单笔订单
     * @description: 请求报文准备
     * @author: Katherine
     * @create: 2018/4/26 9:56
     */
    public BocomClient prepareBocomClient(BocomClient client, TransactionMessage transaction) {
        //设置报文头
        client.setHead("TranCode", transaction.getTranCode());
        client.setHead("MerPtcId", transaction.getMerPtcId());
        client.setHead("TranDate", transaction.getTranDate());
        client.setHead("TranTime", transaction.getTranTime());
        //设置报文体
        client.setData("BusiInfo", transaction.getBusiInfo().toString());
        client.setData("UserInfo", transaction.getUserInfo().toString());
        client.setData("GoodsInfo", transaction.getGoodsInfo().toString());
        client.setData("TranInfo", transaction.getTranInfo().toString());
        client.setData("ChannelInfo", transaction.getChannelInfo().toString());
        client.setData("MemoInfo", transaction.getMemoInfo().toString());
        return client;
    }

    @PostMapping(value = "receiveBackNotify")
    public String receiveBackNotify(HttpServletRequest request) {
        String MerCertID = "301310063009501";
        String webDeployPath = System.getProperty("bocompay.webapp");
        String xmlConfigPath = webDeployPath + "WEB-INF\\classes\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();

        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            System.out.println("初始化失败,错误信息：" + client.getLastErr());
        } else {
            String notifyMsg = request.getParameter("notifyMsg");   //获取银行通知结果
            String rst = client.executeReply(MerCertID, notifyMsg);
            if (rst == null) {
                String err = client.getLastErr();
                System.out.println("交易错误信息：" + err);
            } else {
                String status = client.changeNull(client.getData("TradeState"));
                if (status != "" && status == "Paied") {
                    //商户端返回给银行端的响应参数
                }
            }
        }
        return null;
    }

    /**
     * @description: 订单明细查询<BPAYPY4192>
     * @author: Katherine
     * @create: 2018/4/26 9:46
     */
    @PostMapping(value = "orderDetailQuery")
    public ResponseMessage orderDetailQuery(HttpServletRequest request) {
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

            client.setData("MerOrderNo", request.getParameter("MerOrderNo"));

            OrderDetailResponseMessage orderDetail = orderDetailService.fillOrderDetail(client);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("orderDetail", orderDetail);
            responseMessage = this.responseRec(client, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }

    /**
     * @param
     * @description: 退款明细查询<BPAYPY4197>
     * @author: Katherine
     * @create: 2018/4/26 10:03
     */
    @PostMapping("refundDetailQuery")
    public ResponseMessage refundDetailQuery(HttpServletRequest request) {
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

            client.setData("MerOrderNo", request.getParameter("MerOrderNo"));
            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("StartDate", request.getParameter("StartDate"));
            client.setData("EndDate", request.getParameter("EndDate"));

            RefundDetailResponseMessage refundDetail = refundDetailService.fillRefundDetail(client);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("refundDetail", refundDetail);
            responseMessage = this.responseRec(client, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }

    /**
     * @param
     * @description: 订单退款<BPAYPY4107>
     * @author: Katherine
     * @create: 2018/4/26 15:29
     */
    @PostMapping(value = "tradeRefund")
    public ResponseMessage tradeRefund(HttpServletRequest request) {
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

            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            BocomDataMap busiInfo = new BocomDataMap();//业务信息
            busiInfo.setData("MerOrderNo", request.getParameter("MerOrderNo"));
            busiInfo.setData("RefundAmt", request.getParameter("RefundAmt"));
            busiInfo.setData("RefundCry", request.getParameter("RefundCry"));
            busiInfo.setData("CombineNo", request.getParameter("CombineNo"));
            busiInfo.setData("RefundMemo", request.getParameter("RefundMemo"));
            client.setData("BusiInfo", busiInfo.toString());

            TradeRefundResponseMessage tradeRefund = tradeRefundService.fillTradeRefund(client);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("tradeRefund", tradeRefund);
            responseMessage = this.responseRec(client, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }

    /**
     * @param
     * @description: 订单交付<BPAYPY4106>
     * @author: Katherine
     * @create: 2018/4/26 15:52
     */
    @PostMapping(value = "tradePay")
    public ResponseMessage tradePay(HttpServletRequest request) {
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

            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("SubMerPtcId", request.getParameter("SubMerPtcId"));
            BocomDataMap busiInfo = new BocomDataMap();//业务信息
            busiInfo.setData("MerOrderNo", request.getParameter("MerOrderNo"));
            busiInfo.setData("ConfirmType", request.getParameter("ConfirmType"));
            busiInfo.setData("ConfirmAmt", request.getParameter("ConfirmAmt"));
            busiInfo.setData("ConfirmCry", request.getParameter("ConfirmCry"));
            busiInfo.setData("MerProfitAmt", request.getParameter("MerProfitAmt"));
            busiInfo.setData("MerProfitCry", request.getParameter("MerProfitCry"));
            busiInfo.setData("ConfirmMemo", request.getParameter("ConfirmMemo"));
            client.setData("BusiInfo", busiInfo.toString());

            TradeConfirmResponseMessage tradeConfim = tradeConfirmService.fillTradeConfirm(client);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("tradeConfim", tradeConfim);
            responseMessage = this.responseRec(client, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }
}















