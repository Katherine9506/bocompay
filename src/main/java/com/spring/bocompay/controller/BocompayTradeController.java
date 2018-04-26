package com.spring.bocompay.controller;


import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.BocompaySetting;
import com.spring.bocompay.domain.OrderDetailResponseMessage;
import com.spring.bocompay.domain.RefundDetailResponseMessage;
import com.spring.bocompay.domain.TradeRefundResponseMessage;
import com.spring.bocompay.domain.TransactionMessage;
import com.spring.bocompay.service.OrderDetailResponseMessageService;
import com.spring.bocompay.service.RefundDetailResponseMessageService;
import com.spring.bocompay.service.TradeRefundResponseMessageService;
import com.spring.bocompay.service.TransactionMessageService;
import com.spring.bocompay.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 交易类接口
 * @author: Katherine
 * @create: 2018/4/25 17:37
 */
@RestController
@RequestMapping("bocompay")
public class BocompayTradeController {

    @Autowired
    private TransactionMessageService transactionMessageService;
    @Autowired
    private OrderDetailResponseMessageService orderDetailService;
    @Autowired
    private RefundDetailResponseMessageService refundDetailService;
    @Autowired
    private TradeRefundResponseMessageService tradeRefundService;

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
        System.out.println("signData: " + signData);

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
            String MerPtcId = request.getParameter("MerPtcId");
            String TranDate = request.getParameter("TranDate");
            String TranTime = request.getParameter("TranTime");
            String MerOrderNo = request.getParameter("MerOrderNo");

            client.setHead("TranCode", TranCode);
            client.setHead("MerPtcId", MerPtcId);
            client.setHead("TranDate", TranDate);
            client.setHead("TranTime", TranTime);

            client.setData("MerOrderNo", MerOrderNo);
            String rst = client.execute(MerCertID, TranCode);
            OrderDetailResponseMessage orderDetail = null;
            if (rst == null) {
                responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
            } else {
                orderDetail = new OrderDetailResponseMessage(
                        client.changeNull(client.getHead("RspType")),
                        client.changeNull(client.getHead("RspCode")),
                        client.changeNull(client.getHead("RspMsg")),
                        client.changeNull(client.getHead("RspDate")),
                        client.changeNull(client.getHead("RspTime"))
                );
                if ("E".equalsIgnoreCase(orderDetail.getRspType())) {
                    responseMessage = new ResponseMessage(true, orderDetail.getRspMsg(), 201, orderDetail);
                } else {
                    responseMessage = orderDetailService.fillOrderDetail(client, orderDetail);
                }
            }
        }
        return responseMessage;
    }

    /**
     * @param
     * @description: 退款明细查询< BPAYPY4197>
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
            String MerPtcId = request.getParameter("MerPtcId");
            String TranDate = request.getParameter("TranDate");
            String TranTime = request.getParameter("TranTime");

            String MerOrderNo = request.getParameter("MerOrderNo");
            String MerTranSerialNo = request.getParameter("MerTranSerialNo");
            String StartDate = request.getParameter("StartDate");
            String EndDate = request.getParameter("EndDate");

            client.setHead("TranCode", TranCode);
            client.setHead("MerPtcId", MerPtcId);
            client.setHead("TranDate", TranDate);
            client.setHead("TranTime", TranTime);

            client.setData("MerOrderNo", MerOrderNo);
            client.setData("MerTranSerialNo", MerTranSerialNo);
            client.setData("StartDate", StartDate);
            client.setData("EndDate", EndDate);

            String rst = client.execute(MerCertID, TranCode);
            RefundDetailResponseMessage refundDetail = null;
            if (rst == null) {
                responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
            } else {
                refundDetail = new RefundDetailResponseMessage(
                        client.changeNull(client.getHead("RspType")),
                        client.changeNull(client.getHead("RspCode")),
                        client.changeNull(client.getHead("RspMsg")),
                        client.changeNull(client.getHead("RspDate")),
                        client.changeNull(client.getHead("RspTime"))
                );
                if ("E".equalsIgnoreCase(refundDetail.getRspType())) {
                    responseMessage = new ResponseMessage(true, refundDetail.getRspMsg(), 201, refundDetail);
                } else {
                    responseMessage = refundDetailService.fillRefundDetail(client, refundDetail);
                }
            }
        }
        return responseMessage;
    }

    /**
     * @param
     * @description: 订单退款
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
            String MerPtcId = request.getParameter("MerPtcId");
            String TranDate = request.getParameter("TranDate");
            String TranTime = request.getParameter("TranTime");

            String MerTranSerialNo = request.getParameter("MerTranSerialNo");//商户流水号
            String MerOrderNo = request.getParameter("MerOrderNo");//平台商户（外部）订单号
            String RefundAmt = request.getParameter("RefundAmt");//退款金额
            String RefundCry = request.getParameter("RefundCry");//退款币种
            String CombineNo = request.getParameter("CombineNo");//并单编号
            String RefundMemo = request.getParameter("RefundMemo");//退款备注

            client.setHead("TranCode", TranCode);
            client.setHead("MerPtcId", MerPtcId);
            client.setHead("TranDate", TranDate);
            client.setHead("TranTime", TranTime);

            client.setData("MerTranSerialNo", MerTranSerialNo);
            client.setData("MerOrderNo", MerOrderNo);
            client.setData("RefundAmt", RefundAmt);
            client.setData("RefundCry", RefundCry);
            client.setData("CombineNo", CombineNo);
            client.setData("RefundMemo", RefundMemo);

            String rst = client.execute(MerCertID, TranCode);
            TradeRefundResponseMessage tradeRefund = null;
            if (rst == null) {
                responseMessage = new ResponseMessage(false, client.getLastErr(), 500, null);
            } else {
                tradeRefund = new TradeRefundResponseMessage(
                        client.changeNull(client.getHead("RspType")),
                        client.changeNull(client.getHead("RspCode")),
                        client.changeNull(client.getHead("RspMsg")),
                        client.changeNull(client.getHead("RspDate")),
                        client.changeNull(client.getHead("RspTime"))
                );
                if ("E".equalsIgnoreCase(tradeRefund.getRspType())) {
                    responseMessage = new ResponseMessage(true, tradeRefund.getRspMsg(), 201, tradeRefund);
                } else {
                    responseMessage = tradeRefundService.fillTradeRefund(client, tradeRefund);
                }
            }
        }
        return responseMessage;
    }
}















