<%@ page import="com.bocom.bocompay.BocomClient" %>
<%@ page import="com.bocom.bocompay.BocompaySetting" %>
<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>一键支付签约(BPAYPY4161)</title>
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

    TranCode = request.getParameter("TranCode");
    MerPtcId = request.getParameter("MerPtcId");
    TranDate = request.getParameter("TranDate");
    TranTime = request.getParameter("TranTime");

    client.setHead("TranCode", TranCode);
    client.setHead("MerPtcId", MerPtcId);
    client.setHead("TranDate", TranDate);
    client.setHead("TranTime", TranTime);

    //设这报文体数据
    client.setData("MerAgreeNo", request.getParameter("MerAgreeNo"));
    client.setData("AccName", request.getParameter("AccName"));
    client.setData("CertType", request.getParameter("CertType"));
    client.setData("CertNo", request.getParameter("CertNo"));
    client.setData("MerComment", request.getParameter("MerComment"));
    client.setData("OnekeyTranType", request.getParameter("OnekeyTranType"));
    client.setData("NetType", request.getParameter("NetType"));

    //获取签名密文
    String signData = client.toSignString(MerCertID);
%>
<form name="form1" method="post" action="<%=BocompaySetting.AgreeURL%>">
    <input type="hidden" name="signData" value="<%out.print(signData); %>"/>
</form>
</body>
</html>
