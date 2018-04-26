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
    <title>二级商户签约信息新增(BPAYPY4028)测试</title>
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
        //开始报文头
        String TranCode = request.getParameter("TranCode");
        client.setHead("TranCode", TranCode);
        client.setHead("MerPtcId", request.getParameter("MerPtcId"));
        client.setHead("TranDate", request.getParameter("TranDate"));
        client.setHead("TranTime", request.getParameter("TranTime"));
        //开始报文体
        BocomDataMap MerBaseInfo = new BocomDataMap();
        MerBaseInfo.setData("CstNo", request.getParameter("CstNo"));
        MerBaseInfo.setData("HostNo", request.getParameter("HostNo"));
        MerBaseInfo.setData("EnterNameCN", request.getParameter("EnterNameCN"));
        MerBaseInfo.setData("EnterNameEN", request.getParameter("EnterNameEN"));
        MerBaseInfo.setData("CertType", request.getParameter("CertType"));
        MerBaseInfo.setData("CertNo", request.getParameter("CertNo"));
        MerBaseInfo.setData("OpenBra", request.getParameter("OpenBra"));
        MerBaseInfo.setData("OpenNode", request.getParameter("OpenNode"));
        client.setData("MerBaseInfo", MerBaseInfo.toString());

        BocomDataMap MerBusInfo = new BocomDataMap();
        MerBusInfo.setData("MerchNameCN", request.getParameter("MerchNameCN"));
        MerBusInfo.setData("MerchNameEN", request.getParameter("MerchNameEN"));
        MerBusInfo.setData("ICP", request.getParameter("ICP"));
        MerBusInfo.setData("MerchType", request.getParameter("MerchType"));
        MerBusInfo.setData("WebsiteURL", request.getParameter("WebsiteURL"));
        MerBusInfo.setData("MerchAddr", request.getParameter("MerchAddr"));
        MerBusInfo.setData("ContacterName", request.getParameter("ContacterName"));
        MerBusInfo.setData("PhoneNo", request.getParameter("PhoneNo"));
        MerBusInfo.setData("MobileNo", request.getParameter("MobileNo"));
        MerBusInfo.setData("EmailAddr", request.getParameter("EmailAddr"));
        MerBusInfo.setData("MerchDetailInfo", request.getParameter("MerchDetailInfo"));
        MerBusInfo.setData("SubMerMemId", request.getParameter("SubMerMemId"));
        MerBusInfo.setData("MerchMemo", request.getParameter("MerchMemo"));
        client.setData("MerBusInfo", MerBusInfo.toString());

        BocomDataMap MerPtcInfo = new BocomDataMap();
        MerPtcInfo.setData("FeePeriod", request.getParameter("FeePeriod"));
        MerPtcInfo.setData("ReturnFeeFlg", request.getParameter("ReturnFeeFlg"));
        MerPtcInfo.setData("FeeGroupId", request.getParameter("FeeGroupId"));
        MerPtcInfo.setData("PtcMemo", request.getParameter("PtcMemo"));
        client.setData("MerPtcInfo", MerPtcInfo.toString());

        BocomDataMap MerAccSettInfo = new BocomDataMap();
        MerAccSettInfo.setData("Purpose", request.getParameter("Purpose1"));
        MerAccSettInfo.setData("Account", request.getParameter("Account1"));
        MerAccSettInfo.setData("CurrType", request.getParameter("CurrType1"));
        MerAccSettInfo.setData("AccName", request.getParameter("AccName1"));
        MerAccSettInfo.setData("AccType", request.getParameter("AccType1"));
        MerAccSettInfo.setData("BankId", request.getParameter("BankId1"));
        MerAccSettInfo.setData("BranchId", request.getParameter("BranchId1"));
        MerAccSettInfo.setData("BranchName", request.getParameter("BranchName1"));
        MerAccSettInfo.setData("DeptNo", request.getParameter("DeptNo1"));
        MerAccSettInfo.setData("DeptName", request.getParameter("DeptName1"));
        MerAccSettInfo.setData("AccMemo", request.getParameter("AccMemo1"));
        client.setData("MerAccList.MerAccInfo", MerAccSettInfo);

        BocomDataMap MerAccRefundInfo = new BocomDataMap();
        MerAccRefundInfo.setData("Purpose", request.getParameter("Purpose2"));
        MerAccRefundInfo.setData("Account", request.getParameter("Account2"));
        MerAccRefundInfo.setData("CurrType", request.getParameter("CurrType2"));
        MerAccRefundInfo.setData("AccName", request.getParameter("AccName2"));
        MerAccRefundInfo.setData("AccType", request.getParameter("AccType2"));
        MerAccRefundInfo.setData("BankId", request.getParameter("BankId2"));
        MerAccRefundInfo.setData("BranchId", request.getParameter("BranchId2"));
        MerAccRefundInfo.setData("BranchName", request.getParameter("BranchName2"));
        MerAccRefundInfo.setData("DeptNo", request.getParameter("DeptNo2"));
        MerAccRefundInfo.setData("DeptName", request.getParameter("DeptName2"));
        MerAccRefundInfo.setData("AccMemo", request.getParameter("AccMemo2"));
        client.setData("MerAccList.MerAccInfo", MerAccRefundInfo);

        BocomDataMap MerAccFeeInfo = new BocomDataMap();
        MerAccFeeInfo.setData("Purpose", request.getParameter("Purpose3"));
        MerAccFeeInfo.setData("Account", request.getParameter("Account3"));
        MerAccFeeInfo.setData("CurrType", request.getParameter("CurrType3"));
        MerAccFeeInfo.setData("AccName", request.getParameter("AccName3"));
        MerAccFeeInfo.setData("AccType", request.getParameter("AccType3"));
        MerAccFeeInfo.setData("BankId", request.getParameter("BankId3"));
        MerAccFeeInfo.setData("BranchId", request.getParameter("BranchId3"));
        MerAccFeeInfo.setData("BranchName", request.getParameter("BranchName3"));
        MerAccFeeInfo.setData("DeptNo", request.getParameter("DeptNo3"));
        MerAccFeeInfo.setData("DeptName", request.getParameter("DeptName3"));
        MerAccFeeInfo.setData("AccMemo", request.getParameter("AccMemo3"));
        client.setData("MerAccList.MerAccInfo", MerAccFeeInfo);

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
            out.println("**********二级商户签约信息新增(BPAYPY4028)开始打印**********" + "<br>");
            out.println("一级商户协议号：" + client.changeNull(client.getData("MerPtcId")) + "<br>");
            out.println("二级商户协议号：" + client.changeNull(client.getData("SubMerPtcId")) + "<br>");
            out.println("**********二级商户签约信息新增(BPAYPY4028)结束打印**********" + "<br>");
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
