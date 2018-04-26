<%@ page import="com.bocom.bocompay.BocomClient" %>
<%@ page import="com.bocom.bocompay.OpInfo" %>
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
    <title>一级商户签约信息查询(BPAYPY4022)测试</title>
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


        TranCode = request.getParameter("TranCode");
        MerPtcId = request.getParameter("MerPtcId");
        TranDate = request.getParameter("TranDate");
        TranTime = request.getParameter("TranTime");


        //?????
        client.setHead("TranCode", TranCode);
        client.setHead("MerPtcId", MerPtcId);
        client.setHead("TranDate", TranDate);
        client.setHead("TranTime", TranTime);

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
            out.println("**********一级商户签约信息查询(BPAYPY4022)开始打印**********" + "<br>");
            Map opInfoMap = client.getOpInfoMap();
            OpInfo MerBaseInfo = (OpInfo) opInfoMap.get("MerBaseInfo");
            out.println("一级商户基本信息......" + "<br>");
            out.println("商户编号：" + client.changeNull(MerBaseInfo.getValueByName("MerId")) + "<br>");
            out.println("企业网银客户号：" + client.changeNull(MerBaseInfo.getValueByName("CstNo")) + "<br>");
            out.println("核心客户号：" + client.changeNull(MerBaseInfo.getValueByName("HostNo")) + "<br>");
            out.println("企业中文名称：" + client.changeNull(MerBaseInfo.getValueByName("EnterNameCN")) + "<br>");
            out.println("企业英文名称：" + client.changeNull(MerBaseInfo.getValueByName("EnterNameEN")) + "<br>");
            out.println("证件类型：" + client.changeNull(MerBaseInfo.getValueByName("CertType")) + "<br>");
            out.println("证件号码：" + client.changeNull(MerBaseInfo.getValueByName("CertNo")) + "<br>");
            out.println("商户开户分行：" + client.changeNull(MerBaseInfo.getValueByName("OpenBra")) + "<br>");
            out.println("商户开户网点：" + client.changeNull(MerBaseInfo.getValueByName("OpenNode")) + "<br>");
            OpInfo MerBusInfo = (OpInfo) opInfoMap.get("MerBusInfo");
            out.println("一级商户业务信息......" + "<br>");
            out.println("商户中文名称：" + client.changeNull(MerBusInfo.getValueByName("MerchNameCN")) + "<br>");
            out.println("商户英文名称：" + client.changeNull(MerBusInfo.getValueByName("MerchNameEN")) + "<br>");
            out.println("ICP号：" + client.changeNull(MerBusInfo.getValueByName("ICP")) + "<br>");
            out.println("商户类别：" + client.changeNull(MerBusInfo.getValueByName("MerchType")) + "<br>");
            out.println("商户网站域名：" + client.changeNull(MerBusInfo.getValueByName("WebsiteURL")) + "<br>");
            out.println("商户地址：" + client.changeNull(MerBusInfo.getValueByName("MerchAddr")) + "<br>");
            out.println("联系人姓名：" + client.changeNull(MerBusInfo.getValueByName("ContacterName")) + "<br>");
            out.println("联系电话：" + client.changeNull(MerBusInfo.getValueByName("PhoneNo")) + "<br>");
            out.println("手机号码：" + client.changeNull(MerBusInfo.getValueByName("MobileNo")) + "<br>");
            out.println("电子邮箱地址：" + client.changeNull(MerBusInfo.getValueByName("EmailAddr")) + "<br>");
            out.println("商户情况说明：" + client.changeNull(MerBusInfo.getValueByName("MerchDetailInfo")) + "<br>");
            out.println("商户备注：" + client.changeNull(MerBusInfo.getValueByName("MerchMemo")) + "<br>");
            OpInfo MerPtcInfo = (OpInfo) opInfoMap.get("MerPtcInfo");
            out.println("一级商户协议信息......" + "<br>");
            out.println("商户协议号：" + client.changeNull(MerPtcInfo.getValueByName("MerPtcId")) + "<br>");
            out.println("交易类型：" + client.changeNull(MerPtcInfo.getValueByName("TranType")) + "<br>");
            out.println("商户协议总层级：" + client.changeNull(MerPtcInfo.getValueByName("TotalLayer")) + "<br>");
            out.println("交易模式：" + client.changeNull(MerPtcInfo.getValueByName("TranMode")) + "<br>");
            out.println("商城开通二级商户：" + client.changeNull(MerPtcInfo.getValueByName("SubPtcCreMod")) + "<br>");
            out.println("手续费收取对象：" + client.changeNull(MerPtcInfo.getValueByName("FeeChgObj")) + "<br>");
            out.println("手续费收取周期：" + client.changeNull(MerPtcInfo.getValueByName("FeePeriod")) + "<br>");
            out.println("退货返还手续费：" + client.changeNull(MerPtcInfo.getValueByName("ReturnFeeFlg")) + "<br>");
            out.println("手续费分类代码：" + client.changeNull(MerPtcInfo.getValueByName("FeeGroupId")) + "<br>");
            out.println("商户返回地址：" + client.changeNull(MerPtcInfo.getValueByName("ReturnURL")) + "<br>");
            out.println("后台通知地址：" + client.changeNull(MerPtcInfo.getValueByName("NotifyURL")) + "<br>");
            out.println("商户协议状态：" + client.changeNull(MerPtcInfo.getValueByName("PtcStatus")) + "<br>");
            out.println("商户协议备注：" + client.changeNull(MerPtcInfo.getValueByName("PtcMemo")) + "<br>");
            Map opListMap = client.getOpListMap();
            int index;
            int iNum;

            OpList ExtMerIdList = (OpList) opListMap.get("ExtMerIdList");
            iNum = ExtMerIdList.getOpInfoNum();
            for (index = 0; index <= iNum - 1; index++) {
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外部商户号列表" + (index + 1) + "开始打印" + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外部商户号：" + client.changeNull(ExtMerIdList.getInfoValueByName(index, "ExtMerId")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机构名称：" + client.changeNull(ExtMerIdList.getInfoValueByName(index, "InstType")) + "<br>");
            }

            OpList MerParaList = (OpList) opListMap.get("MerParaList");
            iNum = MerParaList.getOpInfoNum();
            for (index = 0; index <= iNum - 1; index++) {
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商户业务参数列表" + (index + 1) + "开始打印" + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商户签约参数类型：" + client.changeNull(MerParaList.getInfoValueByName(index, "MerParType")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商户签约参数值：" + client.changeNull(MerParaList.getInfoValueByName(index, "MerParValue")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商户签约参数分类：" + client.changeNull(MerParaList.getInfoValueByName(index, "MerParClass")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商户签约参数备注：" + client.changeNull(MerParaList.getInfoValueByName(index, "MerParMemo")) + "<br>");
            }
            OpList FeeList = (OpList) opListMap.get("FeeList");
            iNum = FeeList.getOpInfoNum();
            for (index = 0; index <= iNum - 1; index++) {
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手续费信息列表" + (index + 1) + "开始打印" + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手续费分类代码：" + client.changeNull(FeeList.getInfoValueByName(index, "FeeGroupId")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手续费分类名称：" + client.changeNull(FeeList.getInfoValueByName(index, "FeeGroupName")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交易类型：" + client.changeNull(FeeList.getInfoValueByName(index, "TranType")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付类型：" + client.changeNull(FeeList.getInfoValueByName(index, "PayType")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;付款账户类型：" + client.changeNull(FeeList.getInfoValueByName(index, "AccType")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费率模式ID：" + client.changeNull(FeeList.getInfoValueByName(index, "FeeModeId")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费率模式名称：" + client.changeNull(FeeList.getInfoValueByName(index, "FeeModeName")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费率模式备注：" + client.changeNull(FeeList.getInfoValueByName(index, "FeeModeMemo")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计费方式：" + client.changeNull(FeeList.getInfoValueByName(index, "ChargeMode")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手续费金额/比例：" + client.changeNull(FeeList.getInfoValueByName(index, "FeeRate")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手续费币种：" + client.changeNull(FeeList.getInfoValueByName(index, "FeeCry")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最大金额：" + client.changeNull(FeeList.getInfoValueByName(index, "MaxFeeAmount")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最小金额：" + client.changeNull(FeeList.getInfoValueByName(index, "MinFeeAmount")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手续费备注：" + client.changeNull(FeeList.getInfoValueByName(index, "FeeMemo")) + "<br>");
            }
            OpList MerAccList = (OpList) opListMap.get("MerAccList");
            iNum = MerAccList.getOpInfoNum();
            for (index = 0; index <= iNum - 1; index++) {
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帐户信息列表" + (index + 1) + "开始打印" + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账户用途：" + client.changeNull(MerAccList.getInfoValueByName(index, "Purpose")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号：" + client.changeNull(MerAccList.getInfoValueByName(index, "Account")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;币种：" + client.changeNull(MerAccList.getInfoValueByName(index, "CurrType")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号户名：" + client.changeNull(MerAccList.getInfoValueByName(index, "AccName")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账户类型：" + client.changeNull(MerAccList.getInfoValueByName(index, "AccType")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开户银行：" + client.changeNull(MerAccList.getInfoValueByName(index, "BankId")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属分行：" + client.changeNull(MerAccList.getInfoValueByName(index, "BranchId")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属分行名称：" + client.changeNull(MerAccList.getInfoValueByName(index, "BranchName")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开户网点：" + client.changeNull(MerAccList.getInfoValueByName(index, "DeptNo")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开户网点名称：" + client.changeNull(MerAccList.getInfoValueByName(index, "DeptName")) + "<br>");
                out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号备注：" + client.changeNull(MerAccList.getInfoValueByName(index, "AccMemo")) + "<br>");
            }
            out.println("**********一级商户签约信息查询(BPAYPY4022)结束打印**********" + "<br>");
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
