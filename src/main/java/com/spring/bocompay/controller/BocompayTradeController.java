package com.spring.bocompay.controller;


import com.bocom.bocompay.BocomClient;
import com.bocom.bocompay.BocomDataMap;
import com.bocom.bocompay.BocompaySetting;
import com.spring.bocompay.domain.trade.*;
import com.spring.bocompay.domain.util.Response;
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
     * @param
     * @description: 合并订单创建并付款< BPAYPY4102>
     * @author: Katherine
     * @create: 2018/4/28 14:32
     */
    @PostMapping(value = "pay1_mergeOrderCreateAndPay")
    public RedirectView pay1_mergeOrderCreateAndPay(HttpServletRequest request, RedirectAttributes attributes) {
        String MerCertID = request.getParameter("MerCertID").trim();
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
        BocomClient client = new BocomClient();
        int ret = client.initialize(xmlConfigPath);
        if (ret != 0) {
            System.out.println("初始化失败,错误信息：" + client.getLastErr());
        } else {
            client = this.initializeRequest(request, client);

            client.setData("TotalCount", request.getParameter("TotalCount"));
            client.setData("ChannelApi", request.getParameter("ChannelApi"));
            client.setData("ChannelInst", request.getParameter("ChannelInst"));
            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("SafeReserved", request.getParameter("SafeReserved"));
            client.setData("PayMemo", request.getParameter("PayMemo"));

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

            String signData = client.toSignString(MerCertID);
            attributes.addAttribute("signData", signData);
        }

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

    /**
     * @description: 商户订单后台通知<MAPIPY4196>
     * @author: Katherine
     * @create: 2018/5/2 9:00
     */
    @PostMapping(value = "receiveBackNotify")
    public String receiveBackNotify(HttpServletRequest request) {
        String MerCertID = "301310063009501";
        String xmlConfigPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static\\ini\\BocompayMerchant.xml";
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

            String rst = client.execute(MerCertID, TranCode);

            if (rst == null) {
                responseMessage = new ResponseMessage(true, client.getLastErr(), 500, null);
            } else {
                Response response = this.initializeResponse(client);
                if ("E".equalsIgnoreCase(client.getHead("RspType"))) {
                    responseMessage = new ResponseMessage(true, client.getHead("RspMsg"), 201, response);
                } else {
                    OrderDetailResponseMessage orderDetail = orderDetailService.fillOrderDetail(client);
                    Map<String, Object> responseData = response.getData();
                    responseData.put("orderDetail", orderDetail);
                    responseMessage = new ResponseMessage(true, "成功", 200, response);
                }
            }
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

            String rst = client.execute(MerCertID, TranCode);

            if (rst == null) {
                responseMessage = new ResponseMessage(true, client.getLastErr(), 500, null);
            } else {
                Response response = this.initializeResponse(client);
                if ("E".equalsIgnoreCase(client.getHead("RspType"))) {
                    responseMessage = new ResponseMessage(true, client.getHead("RspMsg"), 201, response);
                } else {
                    RefundDetailResponseMessage refundDetail = refundDetailService.fillRefundDetail(client);
                    Map<String, Object> responseData = response.getData();
                    responseData.put("refundDetail", refundDetail);
                    responseMessage = new ResponseMessage(true, "成功", 200, response);
                }
            }
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

            String rst = client.execute(MerCertID, TranCode);

            if (rst == null) {
                responseMessage = new ResponseMessage(true, client.getLastErr(), 500, null);
            } else {
                Response response = this.initializeResponse(client);
                if ("E".equalsIgnoreCase(client.getHead("RspType"))) {
                    responseMessage = new ResponseMessage(true, client.getHead("RspMsg"), 201, response);
                } else {
                    TradeRefundResponseMessage tradeRefund = tradeRefundService.fillTradeRefund(client);
                    Map<String, Object> responseData = response.getData();
                    responseData.put("tradeRefund", tradeRefund);
                    responseMessage = new ResponseMessage(true, "成功", 200, response);
                }
            }
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

            String rst = client.execute(MerCertID, TranCode);

            if (rst == null) {
                responseMessage = new ResponseMessage(true, client.getLastErr(), 500, null);
            } else {
                Response response = this.initializeResponse(client);
                if ("E".equalsIgnoreCase(client.getHead("RspType"))) {
                    responseMessage = new ResponseMessage(true, client.getHead("RspMsg"), 201, response);
                } else {
                    TradeConfirmResponseMessage tradeConfim = tradeConfirmService.fillTradeConfirm(client);
                    Map<String, Object> responseData = response.getData();
                    responseData.put("tradeConfim", tradeConfim);
                    responseMessage = new ResponseMessage(true, "成功", 200, response);
                }
            }
        }
        return responseMessage;
    }

    /**
     * @param
     * @description: 单笔预订单创建（手机支付插件）<BPAYPY4151>
     * @author: Katherine
     * @create: 2018/4/28 14:42
     */
    @PostMapping(value = "pay0_singlePreOrderCreateAndPay")
    public ResponseMessage pay0_singlePreOrderCreateAndPay(HttpServletRequest request) {
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

            String SubMerPtcId = request.getParameter("SubMerPtcId");
            String MerOrderNo = request.getParameter("MerOrderNo");

            String BuyerId = request.getParameter("BuyerId");
            String BuyerName = request.getParameter("BuyerName");
            String CardNo = request.getParameter("CardNo");
            String SellerId = request.getParameter("SellerId");
            String SellerName = request.getParameter("SellerName");
            String GoodsName = request.getParameter("GoodsName");

            String GoodsTxt = request.getParameter("GoodsTxt");
            String GoodsDesc = request.getParameter("GoodsDesc");
            String TranModeId = request.getParameter("TranModeId");
            String TranAmt = request.getParameter("TranAmt");
            String TranCry = request.getParameter("TranCry");

            String ChannelApi = request.getParameter("ChannelApi");
            String ChannelInst = request.getParameter("ChannelInst");

            String ValidPeriod = request.getParameter("ValidPeriod");

            //设这报文体数据
            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("SafeReserved", request.getParameter("SafeReserved"));
            BocomDataMap map = new BocomDataMap();
            map.setData("SubMerPtcId", SubMerPtcId);
            client.setData("PtcInfo", map.toString());//BocomClient类中并没有定义这种String,object的简单类型，所以，
            //只有手动转成String,String的形式

            BocomDataMap map2 = new BocomDataMap();
            map2.setData("MerOrderNo", MerOrderNo);
            client.setData("BusiInfo", map2.toString());

            BocomDataMap map3 = new BocomDataMap();
            map3.setData("BuyerId", BuyerId);
            map3.setData("BuyerName", BuyerName);
            map3.setData("CardNo", CardNo);
            map3.setData("SellerId", SellerId);
            map3.setData("SellerName", SellerName);
            client.setData("UserInfo", map3.toString());

            BocomDataMap map4 = new BocomDataMap();
            map4.setData("GoodsName", GoodsName);
            map4.setData("GoodsTxt", GoodsTxt);
            map4.setData("GoodsDesc", GoodsDesc);
            client.setData("GoodsInfo", map4.toString());

            BocomDataMap map5 = new BocomDataMap();
            map5.setData("TranModeId", TranModeId);
            map5.setData("TranAmt", TranAmt);
            map5.setData("TranCry", TranCry);
            client.setData("TranInfo", map5.toString());

            BocomDataMap map7 = new BocomDataMap();
            map7.setData("ChannelApi", ChannelApi);
            map7.setData("ChannelInst", ChannelInst);
            client.setData("ChannelInfo", map7.toString());

            BocomDataMap map6 = new BocomDataMap();
            map6.setData("BuyerMemo", request.getParameter("BuyerMemo"));
            map6.setData("SellerMemo", request.getParameter("SellerMemo"));
            map6.setData("PlatMemo", request.getParameter("PlatMemo"));
            map6.setData("PayMemo", request.getParameter("PayMemo"));
            client.setData("MemoInfo", map6.toString());

            client.setData("ValidPeriod", ValidPeriod);

            String rst = client.execute(MerCertID, TranCode);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("MerPtcId", client.getData("MerPtcId"));
            responseData.put("MerOrderNo", client.getData("MerOrderNo"));
            responseData.put("TradeTn", client.getData("TradeTn"));
            responseData.put("TrdMerId", client.getData("TrdMerId"));
            responseData.put("TranStt", client.getData("TranStt"));
            responseData.put("TranRspCode", client.getData("TranRspCode"));
            responseData.put("TranRspMsg", client.getData("TranRspMsg"));
            responseMessage = this.responseRec(client, rst, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }

    /**
     * @param
     * @description: 合并预订单创建（手机支付插件）< BPAYPY4152>
     * @author: Katherine
     * @create: 2018/4/28 14:51
     */
    @PostMapping(value = "pay0_mergePreOrderCreate")
    public ResponseMessage pay0_mergePreOrderCreate(HttpServletRequest request) {
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

            client.setData("TotalCount", request.getParameter("TotalCount"));
            client.setData("ChannelApi", request.getParameter("ChannelApi"));
            client.setData("ChannelInst", request.getParameter("ChannelInst"));
            client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
            client.setData("PayMemo", request.getParameter("PayMemo"));
            client.setData("SafeReserved", request.getParameter("SafeReserved"));
            client.setData("ValidPeriod", request.getParameter("ValidPeriod"));
            client.setData("CardNo", request.getParameter("CardNo"));

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

            String rst = client.execute(MerCertID, TranCode);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("MerPtcId", client.getData("MerPtcId"));
            responseData.put("MerTranSerialNo", client.getData("MerTranSerialNo"));
            responseData.put("TradeTn", client.getData("TradeTn"));
            responseData.put("TrdMerId", client.getData("TrdMerId"));
            responseData.put("TranStt", client.getData("TranStt"));
            responseData.put("TranRspCode", client.getData("TranRspCode"));
            responseData.put("TranRspMsg", client.getData("TranRspMsg"));
            responseMessage = this.responseRec(client, rst, MerCertID, TranCode, responseData);
        }
        return responseMessage;
    }
}















