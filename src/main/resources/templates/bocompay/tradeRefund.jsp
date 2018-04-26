<%@ page import = "com.bocom.bocompay.*"%>
<%@ page import = "java.util.Map"%>
<%@ page import = "java.io.File"%>
<%@ page language = "java" contentType = "text/html; charset=UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>

<html>
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
        <title>交易退款(BPAYPY4107)</title>
    </head>

    <body bgcolor = "#FFFFFF" text = "#000000">
        <%
        
        String MerCertID = request.getParameter("MerCertID").trim();         
        
        String webDeployPath = application.getRealPath("/");
        if( ! File.separator.equals(webDeployPath.substring(webDeployPath.length() - 1))){
			webDeployPath = webDeployPath + File.separatorChar;
		}
        String xmlConfigPath = webDeployPath + "bocommjava/ini/BocompayMerchant.xml";
        
        BocomClient client = new BocomClient();
		
        int ret = client.initialize(xmlConfigPath); //

        if (ret != 0) {                                                                 //
            out.print("errorMessage:" + client.getLastErr());
        }

        else {
        
 		String TranCode;
        String MerPtcId;
        String TranDate;
        String TranTime;
        
        String MerOrderNo;
        String RefundAmt;
        String RefundCry;
        String CombineNo;
      
        TranCode = request.getParameter("TranCode");
        MerPtcId = request.getParameter("MerPtcId");
        TranDate = request.getParameter("TranDate");
        TranTime = request.getParameter("TranTime");
        
        client.setHead("TranCode",TranCode);
		client.setHead("MerPtcId",MerPtcId);
		client.setHead("TranDate",TranDate);
		client.setHead("TranTime",TranTime);
        
        //开始设置报文体
        MerOrderNo = request.getParameter("MerOrderNo");
        RefundAmt = request.getParameter("RefundAmt");
        RefundCry = request.getParameter("RefundCry");
        CombineNo = request.getParameter("CombineNo");
		
		//设这报文体数据 
		client.setData("MerTranSerialNo",request.getParameter("MerTranSerialNo"));
		BocomDataMap map2 = new BocomDataMap();//业务信息
		map2.setData("MerOrderNo",MerOrderNo);
		map2.setData("RefundAmt",RefundAmt);
		map2.setData("RefundCry",RefundCry);
		map2.setData("CombineNo",CombineNo);
		map2.setData("RefundMemo",request.getParameter("RefundMemo"));
		client.setData("BusiInfo",map2.toString());
		
		String rst = client.execute(MerCertID, TranCode);

		if (rst == null){  
			String err = client.getLastErr();
			out.print("交易错误信息："+err + "<br>");
		}
       	else if("E".equalsIgnoreCase(client.getHead("RspType"))){
         	out.println("响应类型：" + client.changeNull(client.getHead("RspType"))+ "<br>");
         	out.println("交易返回码：" + client.changeNull(client.getHead("RspCode"))+ "<br>");
         	out.println("交易返回信息：" + client.changeNull(client.getHead("RspMsg"))+ "<br>");
         	out.println("响应日期：" + client.changeNull(client.getHead("RspDate"))+ "<br>");
         	out.println("响应时间：" + client.changeNull(client.getHead("RspTime"))+ "<br>");
         }
         else{
//开始打印
			out.println("响应类型：" + client.changeNull(client.getHead("RspType"))+ "<br>");
         	out.println("交易返回码：" + client.changeNull(client.getHead("RspCode"))+ "<br>");
         	out.println("交易返回信息：" + client.changeNull(client.getHead("RspMsg"))+ "<br>");
         	out.println("响应日期：" + client.changeNull(client.getHead("RspDate"))+ "<br>");
         	out.println("响应时间：" + client.changeNull(client.getHead("RspTime"))+ "<br>");
    		out.println("**********交易退款(BPAYPY4107)开始打印**********"+ "<br>");
    		out.println("商户交易流水号：" + client.changeNull(client.getData("MerTranSerialNo"))+ "<br>");
    		out.println("交易处理码：" + client.changeNull(client.getData("TranRspCode"))+ "<br>");
    		out.println("交易处理描述：" + client.changeNull(client.getData("TranRspMsg"))+ "<br>");
    		out.println("交易状态：" + client.changeNull(client.getData("TranStt"))+ "<br>");
    		out.println("新增交易状态：" + client.changeNull(client.getData("NewTranStt"))+ "<br>");
    		out.println("平台商户（外部）订单号：" + client.changeNull(client.getData("MerOrderNo"))+ "<br>");
    		out.println("退款单据号：" + client.changeNull(client.getData("RefundOrderNo"))+ "<br>");
    		out.println("退款金额：" + client.changeNull(client.getData("RefundAmt"))+ "<br>");
    		out.println("退款币种：" + client.changeNull(client.getData("RefundCry"))+ "<br>");
    		out.println("支付类型：" + client.changeNull(client.getData("ChannelType"))+ "<br>");
    		out.println("**********交易退款(BPAYPY4107)结束打印**********"+ "<br>");
//结束打印
        	out.println("succeed!");       	
        }
             
		}
        %>

        <p>
            <a href = "index.htm">返回首页</a>
        </p>

        <p>
            &nbsp;
        </p>
    </body>
</html>
