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
        <title>报表文件下载(BPAYPY4282)测试</title>
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
     
        TranCode = request.getParameter("TranCode");
        MerPtcId = request.getParameter("MerPtcId");
        TranDate = request.getParameter("TranDate");
        TranTime = request.getParameter("TranTime");
       
        //?????
        client.setHead("TranCode",TranCode);
		client.setHead("MerPtcId",MerPtcId);
		client.setHead("TranDate",TranDate);
		client.setHead("TranTime",TranTime);
		
		//?????
		client.setData("FileType",request.getParameter("FileType"));
		client.setData("FileFormat",request.getParameter("FileFormat"));
		client.setData("SettDate",request.getParameter("SettDate"));
				
		String rst = client.execute(MerCertID, TranCode);
  
  		if (rst == null){  
			String err = client.getLastErr();
			out.print("交易错误信息："+err + "<br>");
		}
		//下载交易的响应只有报文头
		else{
//开始打印
			out.println("响应类型：" + client.changeNull(client.getHead("RspType"))+ "<br>");
         	out.println("交易返回码：" + client.changeNull(client.getHead("RspCode"))+ "<br>");
         	out.println("交易返回信息：" + client.changeNull(client.getHead("RspMsg"))+ "<br>");
         	out.println("响应日期：" + client.changeNull(client.getHead("RspDate"))+ "<br>");
         	out.println("响应时间：" + client.changeNull(client.getHead("RspTime"))+ "<br>");
         }
//结束打印
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
