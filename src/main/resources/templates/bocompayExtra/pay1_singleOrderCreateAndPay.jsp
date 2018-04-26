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
    <title>交易创建并付款(BPAYPY4101)</title>
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
    if (ret != 0) {                                                                 //
        out.print("初始化失败,错误信息：" + client.getLastErr());
        return;
    }
    String TranCode;
    String MerPtcId;
    String TranDate;
    String TranTime;

    String SubMerPtcId;
    String MerOrderNo;

    String BuyerId;
    String BuyerName;
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

    BocomDataMap map6 = new BocomDataMap();
    map6.setData("ChannelApi", ChannelApi);
    map6.setData("ChannelInst", ChannelInst);
    client.setData("ChannelInfo", map6.toString());

    BocomDataMap map7 = new BocomDataMap();
    map7.setData("BuyerMemo", request.getParameter("BuyerMemo"));
    map7.setData("SellerMemo", request.getParameter("SellerMemo"));
    map7.setData("PlatMemo", request.getParameter("PlatMemo"));
    map7.setData("PayMemo", request.getParameter("PayMemo"));
    client.setData("MemoInfo", map7.toString());

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
