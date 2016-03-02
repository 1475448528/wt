<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>With Image Logins</title>
    
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
  <!-- border="1px" bordercolor="red" 加上帮助的分割线 -->
  <form action="servlet/Logins" name="login">
  <table background="images/Local.png" width="960" height="720">
  
  <tr>
     <td style="width: 402px; "></td> 
     <td style="height: 297px; "></td> 
     <td></td>
  </tr>
  
  <tr>
     <td></td>
    <td style="padding-left:0px;border:none;color:none;background:none; height: 34px;width:26px;">
          <input type="text"name="user"size="26">
     </td>
     <td></td>
      
     
  </tr>
  
  <tr>
     <td ></td>
     <td style="padding-left:0px;border:none;color:none;background:none; height: 10px ;width:26px">
          <input type="password"name="password"size="26">
     </td>
     <td ></td>
  </tr>
  
  <tr>
     <td width="25" height="46">
    
     </td>
     <td >
        <input type="button"value="登录"onclick="login.submit()"style="width:50px;height:30px;margin-left:10px"><!--  padding-left:30px只能调整文字的初始位置 -->
     </td>
     <td ></td>
  </tr>
  
 <!--  空白区 -->
   <tr>
     <td ></td>
     <td ></td>
     <td ></td>
  </tr>
  </table>
  </form>
   
  </body>
</html>
