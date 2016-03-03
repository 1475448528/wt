<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
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
  <form method="post" action="servlet/Logins" onsubmit="return isYes()">
   <tr><td>用户名：<input type="text"name="user"></td></tr>
   <tr><td>密码：<input type="password"name="password"></td></tr>
   <tr><td><input type="submit" name="login"value="登录"></td></tr>
  </form>

    <h4>功能就是发送用户名和密码，action到Servlet然后展示出来<h4>
    This is my Login.jsp page<br>
  </body>
</html>
