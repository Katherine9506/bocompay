<%@ page import="com.bocom.bocompay.BocomClient" %>
<%@ page import="com.bocom.bocompay.BocomDataMap" %>
<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> 一键支付合并订单创建并付款（BPAYPY4167）</title>
</head>

<body bgcolor="#FFFFFF" text="#000000" onload=" javascript: form1.submit() ">
<%
    String MerCertID = request.getParameter("MerCertID").trim();

    String webDeployPath = application.getRealPath("/");
    if (!File.separator.equals(webDeployPath.substring(webDeployPath.length() - 1))) {
        webDeployPath = webDeployPath + File.separatorChar;
    }
    String xmlConfigPath = webDeployPath + "bocommjava/ini/BocompayMerchant.xml";

    BocomClient client = new BocomClient();

    int ret = client.initialize(xmlConfigPath); //

    if (ret != 0) {
        out.print("初始化失败,错误信息：" + client.getLastErr());
        return;
    }
    //开始报文头
    String TranCode = request.getParameter("TranCode");
    client.setHead("TranCode", TranCode);
    client.setHead("MerPtcId", request.getParameter("MerPtcId"));
    client.setHead("TranDate", request.getParameter("TranDate"));
    client.setHead("TranTime", request.getParameter("TranTime"));
    //结束报文头
    //设置报文体一级节点数据（除OrderList外）
    client.setData("TotalCount", request.getParameter("TotalCount"));
    client.setData("ChannelApi", request.getParameter("ChannelApi"));
    client.setData("ChannelInst", request.getParameter("ChannelInst"));
    client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
    client.setData("SafeReserved", request.getParameter("SafeReserved"));
    client.setData("PayMemo", request.getParameter("PayMemo"));

    String MerAgreeNo = request.getParameter("MerAgreeNo");
    String AgreeNo = request.getParameter("AgreeNo");
    String SessionID = request.getParameter("SessionID");
    String DynPassword = request.getParameter("DynPassword");
    BocomDataMap map8 = new BocomDataMap();
    map8.setData("DynPassword", DynPassword);
    map8.setData("SessionID", SessionID);
    map8.setData("AgreeNo", AgreeNo);
    map8.setData("MerAgreeNo", MerAgreeNo);
    client.setData("OnekeyInf", map8.toString());
    //设置报文体一级节点数据（除OrderList外）结束
    //开始设置报文体
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
    if (rst == null) {
        String err = client.getLastErr();
        out.print("交易错误信息：" + err + "<br>");
    } else if ("E".equalsIgnoreCase(client.getHead("RspType"))) {
        out.println("响应类型：" + client.changeNull(client.getHead("RspType")) + "<br>");
        out.println("交易返回码：" + client.changeNull(client.getHead("RspCode")) + "<br>");
        out.println("交易返回信息：" + client.changeNull(client.getHead("RspMsg")) + "<br>");
        out.println("响应日期：" + client.changeNull(client.getHead("RspDate")) + "<br>");
        out.println("响应时间：" + client.changeNull(client.getHead("RspTime")) + "<br>");
    } else {
        out.println("响应类型：" + client.changeNull(client.getHead("RspType")) + "<br>");
        out.println("交易返回码：" + client.changeNull(client.getHead("RspCode")) + "<br>");
        out.println("交易返回信息：" + client.changeNull(client.getHead("RspMsg")) + "<br>");
        out.println("响应日期：" + client.changeNull(client.getHead("RspDate")) + "<br>");
        out.println("响应时间：" + client.changeNull(client.getHead("RspTime")) + "<br>");
        out.println("**********通知商户支付结果信息开始打印**********" + "<br>");
        out.println("商户交易流水号：" + client.changeNull(client.getData("MerTranSerialNo")) + "<br>");
        out.println("交易状态：" + client.changeNull(client.getData("TranState")) + "<br>");
        out.println("交易金额：" + client.changeNull(client.getData("Amount")) + "<br>");
        out.println("交易币种：" + client.changeNull(client.getData("Currency")) + "<br>");
        out.println("银行备注信息：" + client.changeNull(client.getData("Bankcomment")) + "<br>");
        out.println("**********通知商户支付结果信息结束打印**********" + "<br>");
        out.println("succeed!");

    }
%>

<p>
    <a href="index.htm">返回首页</a>
</p>

<p>
    &nbsp;
</p>
</body>
</html>
