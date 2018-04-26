<%@ page import="com.bocom.bocompay.*" %>
<%@ page import = "java.util.Map"%>
<%@ page import = "java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
        <title>接收银行端发起的前台通知</title>
    </head>

    <body bgcolor = "#FFFFFF" text = "#000000">
        <%
        
        String MerCertID = "301310063009501";
        
        String webDeployPath = application.getRealPath("/");
        if( ! File.separator.equals(webDeployPath.substring(webDeployPath.length() - 1))){
			webDeployPath = webDeployPath + File.separatorChar;
		}
        String xmlConfigPath = webDeployPath + "bocommjava/ini/BocompayMerchant.xml";
        
        BocomClient client = new BocomClient();
		
        int ret = client.initialize(xmlConfigPath); //该代码只需调用一次
		if (ret != 0){  //初始化失败
			out.print("初始化失败,错误信息："+client.getLastErr());
		}
		else {
        	out.print("商户结果页面"+"<br>");
        	out.print("--------------------------"+"<br>");
        	String notifyMsg = request.getParameter("notifyMsg");   //获取银行通知结果
        	//notifyMsg = "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";
        	String rst = client.executeReply(MerCertID, notifyMsg);
        	if (rst == null){  
				String err = client.getLastErr();
				out.print("交易错误信息："+err + "<br>");
			}
			else{
//开始打印
			out.println("一级商户协议号：" + client.changeNull(client.getHead("MerPtcId"))+ "<br>");
			out.println("交易码：" + client.changeNull(client.getHead("TranCode"))+ "<br>");
         	out.println("交易日期：" + client.changeNull(client.getHead("TranDate"))+ "<br>");
         	out.println("交易时间：" + client.changeNull(client.getHead("TranTime"))+ "<br>");
			out.println("**********通知商户支付结果信息开始打印**********"+ "<br>");
			out.println("商户交易流水号：" + client.changeNull(client.getData("MerTranSerialNo"))+ "<br>");
			out.println("交易状态：" + client.changeNull(client.getData("TradeState"))+ "<br>");
			Map opListMap = client.getOpListMap();
    		int index;
    		int iNum;
    		OpList MerOrderList = (OpList)opListMap.get("MerOrderList");
			iNum  = MerOrderList.getOpInfoNum();
				for (index = 0; index <= iNum - 1; index++) {
					String MerOrderNo 			= MerOrderList.getInfoValueByName(index,"MerOrderNo");
					if(iNum==1){
						out.println("商户外部订单号：" + client.changeNull(MerOrderNo)+ "<br>");
						out.println("已支付金额：" + MerOrderList.getInfoValueByName(index,"PaiedSum")+ "<br>");
						out.println("支付币种：" + MerOrderList.getInfoValueByName(index,"PaiedSumCry")+ "<br>");
					}
					else{
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商户外部订单号"+(index+1)+"："+ client.changeNull(MerOrderNo)+ "<br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已支付金额："+ MerOrderList.getInfoValueByName(index,"PaiedSum")+ "<br>");
						out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付币种："+ MerOrderList.getInfoValueByName(index,"PaiedSumCry")+ "<br>");
					}
				}
				out.println("**********通知商户支付结果信息结束打印**********"+ "<br>");    		
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
