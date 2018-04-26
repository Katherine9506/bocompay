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
    <title>交易交付(BPAYPY4106)</title>
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
        String SubMerPtcId;
        String TranDate;
        String TranTime;

        String MerOrderNo;
        String ConfirmType;
        String ConfirmAmt;
        String ConfirmCry;
        String MerProfitAmt;
        String MerProfitCry;

        TranCode = request.getParameter("TranCode");
        MerPtcId = request.getParameter("MerPtcId");
        SubMerPtcId = request.getParameter("SubMerPtcId");
        TranDate = request.getParameter("TranDate");
        TranTime = request.getParameter("TranTime");

        client.setHead("MerPtcId", MerPtcId);
        client.setHead("TranTime", TranTime);
        client.setHead("TranCode", TranCode);
        client.setHead("TranDate", TranDate);


        //开始设置报文体
        MerOrderNo = request.getParameter("MerOrderNo");
        ConfirmType = request.getParameter("ConfirmType");
        ConfirmAmt = request.getParameter("ConfirmAmt");
        ConfirmCry = request.getParameter("ConfirmCry");
        MerProfitAmt = request.getParameter("MerProfitAmt");
        MerProfitCry = request.getParameter("MerProfitCry");

        //设这报文体数据

        BocomDataMap map2 = new BocomDataMap();//业务信息
        map2.setData("MerOrderNo", MerOrderNo);
        map2.setData("ConfirmAmt", ConfirmAmt);
        map2.setData("MerProfitAmt", MerProfitAmt);
        map2.setData("ConfirmCry", ConfirmCry);
        map2.setData("ConfirmType", ConfirmType);
        map2.setData("MerProfitCry", MerProfitCry);

        map2.setData("ConfirmMemo", request.getParameter("ConfirmMemo"));
        client.setData("BusiInfo", map2.toString());
        client.setData("MerTranSerialNo", request.getParameter("MerTranSerialNo"));
        client.setData("SubMerPtcId", SubMerPtcId);

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
            out.println("**********交易交付(BPAYPY4106)开始打印**********" + "<br>");
            out.println("商户交易流水号：" + client.changeNull(client.getData("MerTranSerialNo")) + "<br>");
            out.println("交易处理码：" + client.changeNull(client.getData("TranRspCode")) + "<br>");
            out.println("交易状态：" + client.changeNull(client.getData("TranStt")) + "<br>");
            out.println("平台商户（外部）订单号：" + client.changeNull(client.getData("MerOrderNo")) + "<br>");
            out.println("交付单据号：" + client.changeNull(client.getData("ConfirmOrder")) + "<br>");
            out.println("交付金额：" + client.changeNull(client.getData("ConfirmAmt")) + "<br>");
            out.println("交付币种：" + client.changeNull(client.getData("ConfirmCry")) + "<br>");
            out.println("**********交易交付(BPAYPY4106)结束打印**********" + "<br>");
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
