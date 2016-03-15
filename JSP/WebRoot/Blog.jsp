<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String error = request.getParameter("error");
if(error!=null)
  if(error.equals("yes"))
  out.print("<h1>用户名或密码错误！！</h1>");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Blog.jsp' starting page</title>
    
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
  <form action="servlet/BlogLogins"method="post">
    <table > 
      <tr> 
        <td style="height: 44px; ">名称>>><input type="text" name="user"></td>
      </tr>
      <tr> 
        <td style="height: 43px; ">密码>>><input type="password" name="password"></td>
      </tr>
      <tr> 
        <td><span><input type="button" value="注册" onclick="javascript:window.location.href='/JSP/register.jsp';" style="height: 30px; width: 60px; margin:0px 0px 6px 80px;cursor:pointer;">
       <input type="submit" value="登录" style="height: 30px; width: 60px; margin:0px 0px 6px 40px;cursor:pointer;"></span></td><!-- background: url(images/qq.gif) -->
      </tr>
      
    </table>
  </form>
  </body>
</html>
