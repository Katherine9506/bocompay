<%@ page import="com.bocom.bocompay.BocomClient" %>
<%@ page import="com.bocom.bocompay.BocompayNetSignServer" %>
<%@ page import="com.bocom.bocompay.BocompaySetting" %>
<%@ page import="java.io.File" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page language = "java" contentType = "text/html; charset=UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>

<%
		
		String MerCertID = "301310063009501";
		
		String webDeployPath = application.getRealPath("/");
        if( ! File.separator.equals(webDeployPath.substring(webDeployPath.length() - 1))){
			webDeployPath = webDeployPath + File.separatorChar;
		}
        String xmlConfigPath = webDeployPath + "bocommjava/ini/BocompayMerchant.xml";
        
        BocomClient client = new BocomClient();
		
        int ret = client.initialize(xmlConfigPath); //
       
        if (ret != 0) {                                                                 //
            out.print("初始化失败,错误信息：" + client.getLastErr());
            return;
        }
        
        //商户端返回给银行端的响应参数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//获取当前时间
		Calendar cal = Calendar.getInstance();
		String dateAndTime = sdf.format(cal.getTime());
		
		String RspType = "N";
		String RspCode = "MAPIPY0000";
		String RspMsg = "交易成功";
		String RspDate = dateAndTime.substring(0,8);
		String RspTime = dateAndTime.substring(8);
		
		String retXML;
		StringBuffer sb = new StringBuffer("<?xml version='1.0' encoding='UTF-8'?><Document><Head>");
		sb.append("<RspType>").append(RspType).append("</RspType>")
		.append("<RspCode>").append(RspCode).append("</RspCode>")
		.append("<RspMsg>").append(RspMsg).append("</RspMsg>")
		.append("<RspDate>").append(RspDate).append("</RspDate>")
		.append("<RspTime>").append(RspTime).append("</RspTime>")
		.append("</Head></Document>");
		
		retXML = sb.toString();
        
		String srcData = retXML;//明文

		BocompayNetSignServer signServer = new BocompayNetSignServer();		
		signServer.NSSetPlainText(srcData.getBytes("UTF-8"));
		String DN = (String)BocompaySetting.dnmap.get(MerCertID); //获得证书的DN

		byte[] bSignData = null;				
		bSignData = signServer.NSAttachedSign(DN);//密文byte字节数组
		if (signServer.getLastErrnum() < 0) {
            out.print("ERROR:商户端签名失败");
            return;
        }
		String signData = new String(bSignData, "UTF-8");//密文
		out.print(signData);
%>