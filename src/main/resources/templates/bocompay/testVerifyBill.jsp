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
        <title>验签测试</title>
    </head>

    <body bgcolor = "#FFFFFF" text = "#000000">
        <%
        
		String MerCertID = "301310063009501";         
        //读文件获取文件所有内容装入fileContent变量//
        String fileContent = "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";
        
        
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
 		
 			fileContent = client.plainText(fileContent, MerCertID);
  		 	out.println("验签所得明文如下：" + "<br>" );
  		 	out.println(fileContent);
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
