<%@ page import="com.bocom.bocompay.BocomClient" %>
<%@ page import="com.bocom.bocompay.BocomDataMap" %>
<%@ page import="com.bocom.bocompay.BocompaySetting" %>
<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>合并订单创建并付款(BPAYPY4102)</title>
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
    client.setHead("TranCode", request.getParameter("TranCode"));
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
    //设置报文体一级节点数据（除OrderList外）结束
    //开始设置报文体
    String strHiddenRealCount = request.getParameter("HiddenRealCount");
    int intHiddenRealCount = Integer.parseInt(strHiddenRealCount);
    String forInnerInto = "";
    for (int i = 1; i <= intHiddenRealCount; i++) {
        //BocomDataMap PtcInfo = new BocomDataMap();
        BocomDataMap BusiInfoMap = new BocomDataMap();
        BocomDataMap UserInfoMap = new BocomDataMap();
        BocomDataMap GoodsInfoMap = new BocomDataMap();
        BocomDataMap TranInfoMap = new BocomDataMap();
        BocomDataMap MemoInfoMap = new BocomDataMap();
        if (i == 1) {
            //PtcInfo.setData("SubMerPtcId",request.getParameter("SubMerPtcId"));
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
            //PtcInfo.setData("SubMerPtcId",request.getParameter("SubMerPtcId"+i));
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
        //forInnerInto += "<OrderInfo><PtcInfo>"+PtcInfo.toString()+"</PtcInfo>"
        forInnerInto += "<OrderInfo>"
                + "<BusiInfo>" + BusiInfoMap.toString() + "</BusiInfo>"
                + "<UserInfo>" + UserInfoMap.toString() + "</UserInfo>"
                + "<GoodsInfo>" + GoodsInfoMap.toString() + "</GoodsInfo>"
                + "<TranInfo>" + TranInfoMap.toString() + "</TranInfo>"
                + "<MemoInfo>" + MemoInfoMap.toString() + "</MemoInfo></OrderInfo>";
    }
    client.setData("OrderList", forInnerInto);
    System.out.println("srcData: " + client.toString());//明文
    String signData = client.toSignString(MerCertID);
    System.out.println("signData: " + signData);//密文
%>
<form name="form1" method="post" action="<%=BocompaySetting.BpayOrderPayURL%>">
    <input type="hidden" name="signData" value="<%out.print(signData); %>"/>
</form>

<p>
    &nbsp;
</p>
</body>
</html>
