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
    <title>单笔预订单创建并付款(BPAYPY4151)</title>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%

    String MerCertID = request.getParameter("MerCertID").trim();

    String webDeployPath = application.getRealPath("/");
    if (!File.separator.equals(webDeployPath.substring(webDeployPath.length() - 1))) {
        webDeployPath = webDeployPath + File.separatorChar;
    }
    String xmlConfigPath = webDeployPath + "bocommjava/ini/BocompayMerchant.xml";

    BocomClient client = new BocomClient();

    int ret = client.initialize(xmlConfigPath); //
    if (ret != 0) {                                                                 //
        out.print("errorMessage:" + client.getLastErr());
    } else {
        String TranCode;
        String MerPtcId;
        String TranDate;
        String TranTime;

        String SubMerPtcId;
        String MerOrderNo;

        String BuyerId;
        String BuyerName;
        String CardNo;
        String SellerId;
        String SellerName;
        String GoodsName;

        String GoodsTxt;
        String GoodsDesc;
        String TranModeId;
        String TranAmt;
        String TranCry;

        String ChannelApi;
        String ChannelInst;

        String ValidPeriod;

        TranCode = request.getParameter("TranCode");
        MerPtcId = request.getParameter("MerPtcId");
        TranDate = request.getParameter("TranDate");
        TranTime = request.getParameter("TranTime");

        client.setHead("TranCode", TranCode);
        client.setHead("MerPtcId", MerPtcId);
        client.setHead("TranDate", TranDate);
        client.setHead("TranTime", TranTime);

        //开始设置报文体
        SubMerPtcId = request.getParameter("SubMerPtcId");
        MerOrderNo = request.getParameter("MerOrderNo");

        BuyerId = request.getParameter("BuyerId");
        BuyerName = request.getParameter("BuyerName");
        CardNo = request.getParameter("CardNo");
        SellerId = request.getParameter("SellerId");
        SellerName = request.getParameter("SellerName");
        GoodsName = request.getParameter("GoodsName");

        GoodsTxt = request.getParameter("GoodsTxt");
        GoodsDesc = request.getParameter("GoodsDesc");
        TranModeId = request.getParameter("TranModeId");
        TranAmt = request.getParameter("TranAmt");
        TranCry = request.getParameter("TranCry");

        ChannelApi = request.getParameter("ChannelApi");
        ChannelInst = request.getParameter("ChannelInst");

        ValidPeriod = request.getParameter("ValidPeriod");

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
//开始打印
            out.println("响应类型：" + client.changeNull(client.getHead("RspType")) + "<br>");
            out.println("交易返回码：" + client.changeNull(client.getHead("RspCode")) + "<br>");
            out.println("交易返回信息：" + client.changeNull(client.getHead("RspMsg")) + "<br>");
            out.println("响应日期：" + client.changeNull(client.getHead("RspDate")) + "<br>");
            out.println("响应时间：" + client.changeNull(client.getHead("RspTime")) + "<br>");
            out.println("**********交易交付(BPAYPY4151)开始打印**********" + "<br>");
            out.println("一级商户协议号：" + client.changeNull(client.getData("MerPtcId")) + "<br>");
            out.println("平台商户（外部）订单号：" + client.changeNull(client.getData("MerOrderNo")) + "<br>");
            out.println("交易流水号：" + client.changeNull(client.getData("TradeTn")) + "<br>");
            out.println("第三方商户号：" + client.changeNull(client.getData("TrdMerId")) + "<br>");
            out.println("交易状态：" + client.changeNull(client.getData("TranStt")) + "<br>");
            out.println("交易处理码：" + client.changeNull(client.getData("TranRspCode")) + "<br>");
            out.println("交易处理信息：" + client.changeNull(client.getData("TranRspMsg")) + "<br>");
            out.println("**********交易交付(BPAYPY4151)结束打印**********" + "<br>");
//结束打印
            out.println("succeed!");
        }

    }
%>

<p>
    <a href="extraIndex.htm">返回扩展首页</a>
</p>

<p>
    &nbsp;
</p>
</body>
</html>
