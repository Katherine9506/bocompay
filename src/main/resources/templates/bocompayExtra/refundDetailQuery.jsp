<%@ page import="com.bocom.bocompay.BocomClient" %>
<%@ page import="com.bocom.bocompay.OpList" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>退款明细查询(BPAYPY4197)测试</title>
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
        String MerOrderNo;
        String MerTranSerialNo;
        String StartDate;
        String EndDate;

        TranCode = request.getParameter("TranCode");
        MerPtcId = request.getParameter("MerPtcId");
        TranDate = request.getParameter("TranDate");
        TranTime = request.getParameter("TranTime");
        MerOrderNo = request.getParameter("MerOrderNo");
        MerTranSerialNo = request.getParameter("MerTranSerialNo");
        StartDate = request.getParameter("StartDate");
        EndDate = request.getParameter("EndDate");


        //?????
        client.setHead("TranCode", TranCode);
        client.setHead("MerPtcId", MerPtcId);
        client.setHead("TranDate", TranDate);
        client.setHead("TranTime", TranTime);

        //?????
        client.setData("MerOrderNo", MerOrderNo);
        client.setData("MerTranSerialNo", MerTranSerialNo);
        client.setData("StartDate", StartDate);
        client.setData("EndDate", EndDate);
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
            out.println("**********退款明细查询(BPAYPY4197)开始打印**********" + "<br>");
            out.println("一级商户签约协议：" + client.changeNull(client.getData("MerPtcId")) + "<br>");
            out.println("一级商户简称：" + client.changeNull(client.getData("MerPtcName")) + "<br>");
            out.println("所属分行：" + client.changeNull(client.getData("OpenBra")) + "<br>");

            out.println("平台商户订单号：" + client.changeNull(client.getData("MerOrderNo")) + "<br>");
            out.println("创建时间：" + client.changeNull(client.getData("GmtCreate")) + "<br>");
            out.println("交易时间：" + client.changeNull(client.getData("GmtModify")) + "<br>");
            out.println("交易订单号：" + client.changeNull(client.getData("TradeOrderNo")) + "<br>");
            out.println("付款单据号：" + client.changeNull(client.getData("PayNo")) + "<br>");

            Map opListMap = client.getOpListMap();
            int index;
            int iNum;
            OpList SettingList = (OpList) opListMap.get("SettingList");
            iNum = SettingList.getOpInfoNum();
            String defaultStr = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            for (index = 0; index <= iNum - 1; index++) {
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SettingList" + (index + 1) + "开始打印" + "<br>");
                out.println(defaultStr + "商户对账流水号：" + client.changeNull(SettingList.getInfoValueByName(index, "MerTranSerialNo")) + "<br>");
                out.println(defaultStr + "流水号：" + client.changeNull(SettingList.getInfoValueByName(index, "WaterNo")) + "<br>");
                out.println(defaultStr + "退款状态：" + client.changeNull(SettingList.getInfoValueByName(index, "RefundState")) + "<br>");
                out.println(defaultStr + "通道接口编号：" + client.changeNull(SettingList.getInfoValueByName(index, "ChannelApi")) + "<br>");
                out.println(defaultStr + "退款金额：" + client.changeNull(SettingList.getInfoValueByName(index, "TranAmt")) + "<br>");
                out.println(defaultStr + "退款币种：" + client.changeNull(SettingList.getInfoValueByName(index, "TranCry")) + "<br>");
                out.println(defaultStr + "退还手续费：" + client.changeNull(SettingList.getInfoValueByName(index, "ReturnFee")) + "<br>");
                out.println(defaultStr + "退款账号：" + client.changeNull(SettingList.getInfoValueByName(index, "RefundAccNo")) + "<br>");
                out.println(defaultStr + "退款户名：" + client.changeNull(SettingList.getInfoValueByName(index, "RefundAccName")) + "<br>");
                out.println(defaultStr + "收款账号：" + client.changeNull(SettingList.getInfoValueByName(index, "RecAccNo")) + "<br>");
                out.println(defaultStr + "收款户名：" + client.changeNull(SettingList.getInfoValueByName(index, "RecAccName")) + "<br>");
                out.println(defaultStr + "收款银行名称：" + client.changeNull(SettingList.getInfoValueByName(index, "RecBankName")) + "<br>");
                out.println(defaultStr + "收款账户网点：" + client.changeNull(SettingList.getInfoValueByName(index, "RecBranchId")) + "<br>");
                out.println(defaultStr + "批次号：" + client.changeNull(SettingList.getInfoValueByName(index, "BatchNo")) + "<br>");
                out.println(defaultStr + "退款备注：" + client.changeNull(SettingList.getInfoValueByName(index, "RefundMemo")) + "<br>");
            }
            out.println("**********退款明细查询(BPAYPY4197)结束打印**********" + "<br>");
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
