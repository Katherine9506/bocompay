<%@ page import = "com.bocom.bocompay.*"%>
<%@ page import = "java.io.File"%>
<%@ page language = "java" contentType = "text/html; charset=UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>

<html>
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
        <title>一级商户订单通知维护(BPAYPY4020)</title>
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
		
        int ret = client.initialize(xmlConfigPath);

        if (ret != 0) {
            out.print("errorMessage:" + client.getLastErr());
        }

        else {
        
 		String TranCode;
        String MerPtcId;
        String TranDate;
        String TranTime;
      
        TranCode = request.getParameter("TranCode");
        MerPtcId = request.getParameter("MerPtcId");
        TranDate = request.getParameter("TranDate");
        TranTime = request.getParameter("TranTime");
        
        client.setHead("TranCode",TranCode);
		client.setHead("MerPtcId",MerPtcId);
		client.setHead("TranDate",TranDate);
		client.setHead("TranTime",TranTime);
        
		//设置报文体数据 
		client.setData("ReturnURL",request.getParameter("ReturnURL"));
		client.setData("NotifyURL",request.getParameter("NotifyURL"));
		
		//client.setData("AgreedReturnURL",request.getParameter("AgreedReturnURL"));
		//client.setData("AgreedNotifyURL",request.getParameter("AgreedNotifyURL"));
		
		String rst = client.execute(MerCertID,TranCode);
  
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
    		out.println("**********一级商户订单通知维护(BPAYPY4020)开始打印**********"+ "<br>");
    		out.println("一级商户协议号：" + client.changeNull(client.getData("MerPtcId"))+ "<br>");
    		out.println("前台通知地址：" + client.changeNull(client.getData("ReturnURL"))+ "<br>");
    		out.println("后台通知地址：" + client.changeNull(client.getData("NotifyURL"))+ "<br>");
    		//out.println("一键支付签约前台通知地址：" + client.changeNull(client.getData("AgreedReturnURL"))+ "<br>");
    		//out.println("一键支付签约后台通知地址：" + client.changeNull(client.getData("AgreedNotifyURL"))+ "<br>");
    		out.println("**********一级商户订单通知维护(BPAYPY4020)结束打印**********"+ "<br>");
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
