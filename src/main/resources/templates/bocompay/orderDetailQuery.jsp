<%@ page import="com.bocom.bocompay.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单明细查询(BPAYPY4192)测试</title>
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

        TranCode = request.getParameter("TranCode");
        MerPtcId = request.getParameter("MerPtcId");
        TranDate = request.getParameter("TranDate");
        TranTime = request.getParameter("TranTime");
        MerOrderNo = request.getParameter("MerOrderNo");


        //?????
        client.setHead("TranCode", TranCode);
        client.setHead("MerPtcId", MerPtcId);
        client.setHead("TranDate", TranDate);
        client.setHead("TranTime", TranTime);

        //?????
        client.setData("MerOrderNo", MerOrderNo);
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
            out.println("**********订单明细查询(BPAYPY4192)开始打印**********" + "<br>");
            out.println("交易订单号：" + client.changeNull(client.getData("TradeOrderNo")) + "<br>");
            out.println("付款流水号：" + client.changeNull(client.getData("PayOrderNo")) + "<br>");
            out.println("交易状态：" + client.changeNull(client.getData("TradeState")) + "<br>");
            out.println("新增交易状态：" + client.changeNull(client.getData("NTradeState")) + "<br>");
            out.println("订单状态：" + client.changeNull(client.getData("OrderState")) + "<br>");
            out.println("交易模式：" + client.changeNull(client.getData("TranModeId")) + "<br>");
            out.println("订单创建时间：" + client.changeNull(client.getData("CreateDateTime")) + "<br>");
            out.println("订单关闭时间：" + client.changeNull(client.getData("GmtCloseDateTime")) + "<br>");

            out.println("一级商户签约协议：" + client.changeNull(client.getData("MerPtcId")) + "<br>");
            out.println("一级商户简称：" + client.changeNull(client.getData("MerNameCN")) + "<br>");
            out.println("一级商户所属分行：" + client.changeNull(client.getData("MerOpenBranch")) + "<br>");
            out.println("二级商户签约协议：" + client.changeNull(client.getData("SubMerPtcId")) + "<br>");
            out.println("二级商户昵称：" + client.changeNull(client.getData("SubMerNameCN")) + "<br>");

            out.println("平台商户订单号：" + client.changeNull(client.getData("MerOrderNo")) + "<br>");
            out.println("平台商订单备注：" + client.changeNull(client.getData("PlatMemo")) + "<br>");
            out.println("电子回单校验码：" + client.changeNull(client.getData("ReceiptCode")) + "<br>");
            out.println("银行备注：" + client.changeNull(client.getData("BankMemo")) + "<br>");
            out.println("订单金额：" + client.changeNull(client.getData("TranAmt")) + "<br>");

            out.println("订单币种：" + client.changeNull(client.getData("TranCry")) + "<br>");
            out.println("实际需付款金额：" + client.changeNull(client.getData("PayAmt")) + "<br>");
            out.println("实际付款币种：" + client.changeNull(client.getData("PayCry")) + "<br>");
            out.println("已支付总金额：" + client.changeNull(client.getData("PaiedSum")) + "<br>");
            out.println("已退款总金额：" + client.changeNull(client.getData("RefundSum")) + "<br>");

            out.println("已交付金额：" + client.changeNull(client.getData("ConfirmSum")) + "<br>");
            out.println("订单简称：" + client.changeNull(client.getData("GoodsTxt")) + "<br>");
            out.println("订单名称：" + client.changeNull(client.getData("GoodsName")) + "<br>");
            out.println("商品详情：" + client.changeNull(client.getData("GoodsDesc")) + "<br>");
            out.println("买方会员ID：" + client.changeNull(client.getData("BuyerId")) + "<br>");

            out.println("买方名称：" + client.changeNull(client.getData("BuyerName")) + "<br>");
            out.println("买方备注：" + client.changeNull(client.getData("BuyerMemo")) + "<br>");
            out.println("卖方会员ID：" + client.changeNull(client.getData("SellerId")) + "<br>");
            out.println("卖方名称：" + client.changeNull(client.getData("SellerName")) + "<br>");
            out.println("卖方备注：" + client.changeNull(client.getData("SellerMemo")) + "<br>");

            out.println("安全域：" + client.changeNull(client.getData("SafeReserved")) + "<br>");
            out.println("订单有效期：" + client.changeNull(client.getData("ValidPeriod")) + "<br>");

            Map opListMap = client.getOpListMap();
            int index;
            int iNum;
            OpList SettingList = (OpList) opListMap.get("SettingList");
            iNum = SettingList.getOpInfoNum();
            String defaultStr = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            for (index = 0; index <= iNum - 1; index++) {
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SettingList" + (index + 1) + "开始打印" + "<br>");
                out.println(defaultStr + "商户交易流水号：" + client.changeNull(SettingList.getInfoValueByName(index, "MerTranSerialNo")) + "<br>");
                out.println(defaultStr + "流水号：" + client.changeNull(SettingList.getInfoValueByName(index, "PayNo")) + "<br>");
                out.println(defaultStr + "流水时间：" + client.changeNull(SettingList.getInfoValueByName(index, "PayDate")) + "<br>");
                out.println(defaultStr + "成功时间：" + client.changeNull(SettingList.getInfoValueByName(index, "SuccessTime")) + "<br>");
                out.println(defaultStr + "流水类型：" + client.changeNull(SettingList.getInfoValueByName(index, "PayType")) + "<br>");
                out.println(defaultStr + "流水金额：" + client.changeNull(SettingList.getInfoValueByName(index, "PayAmt")) + "<br>");
                out.println(defaultStr + "流水状态：" + client.changeNull(SettingList.getInfoValueByName(index, "PayState")) + "<br>");
                out.println(defaultStr + "新增流水状态：" + client.changeNull(SettingList.getInfoValueByName(index, "NPayState")) + "<br>");
                out.println(defaultStr + "商户流水备注：" + client.changeNull(SettingList.getInfoValueByName(index, "PayMemo")) + "<br>");
                out.println(defaultStr + "银行流水备注：" + client.changeNull(SettingList.getInfoValueByName(index, "PayBankMemo")) + "<br>");
                out.println(defaultStr + "支付类型：" + client.changeNull(SettingList.getInfoValueByName(index, "ChannelType")) + "<br>");
                out.println(defaultStr + "所属银行名称：" + client.changeNull(SettingList.getInfoValueByName(index, "PayBankName")) + "<br>");
            }
            out.println("**********订单明细查询(BPAYPY4192)结束打印**********" + "<br>");
//结束打印
            out.println("succeed!");
        }

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
