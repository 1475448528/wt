<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 上面的都是配置信息 -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    This is my JSP page. <br>
    
    <h4>===form 表单===</h4>
    <form method=post> 
   
   第一行：
   <input type="text" name="第一行">
   <br>
   第二行：
   <input type="text" name="第二行">
   <br>
   密码：
   <input type="password" name="密码">
   <br>
   <h4>===单选框===</h4>
   <input type="radio" name="Sex" value="男">男
   <input type="radio" name="Sex" value="女">女
   <h4>===复选框====</h4>
   <input type="checkbox" name="java"> java
   <input type="checkbox" name="c">C
   <br>
  <h4>===按钮===</h4>
   <button type="button">Click Me!</button>
   <h4>===图像===</h4>http://img.win7china.com/NewsUploadFiles/20090823_121319_375_u.jpg<br>
  <img src="http://img.win7china.com/NewsUploadFiles/20090823_121319_375_u.jpg"width="150"height="130"> 
  <pre> 
		这是：预格式文本。它保留了      空格
		        和换行。
    </pre>
   </form>
   <h4>一个关于表单密码的栗子：</h4>

<input type=*>
<input type=* value=**>
<form action=/cgi-bin/post-query method=POST>
您的姓名: 
<input type=text name=姓名><br>
您的主页的网址: 
<input type=text name=网址 value=http:// /><br>
密码: 
<input type=password name=密码><br>
<input type=submit value="发送"><input type=reset value="重设">
</form>

   
    <a href = "www.baidu.com">Two</a>
    
  </body>
</html>
