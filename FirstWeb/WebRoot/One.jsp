<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My One.jsp </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h4>you can see this page ,it is mark the kill you already complete use</h4>
    This is my One.JSP page. <br>
    <br><br>
    一个表格：
    <table>
    <tr><td style="width: 36px; height: 20px">0</td><td style="width: 52px; height: 38px">1</td><td style="height: 41px; width: 55px; ">79</td></tr>
    <tr><td>0</td><td>1</td><td style="height: 32px; width: 49px; ">666</td></tr>
    </table>
    <a></a>
  </body>
</html>
