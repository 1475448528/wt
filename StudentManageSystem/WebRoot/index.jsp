<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ManageSystem Logins</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	   <script type="text/javascript" language="javascript">
	   function fade (){
	   if(((document.getElementById("user")).value)=="旷小希" && ((document.getElementById("password")).value)=="11") 
	        document.login.submit();
	   else{
	      alert("用户名或密码错误！！");
	      return true;
	   }
	   //document.getElementsByName("user").value
	   
	  
	   //用id才能获取到那个值，name就会说未定义，获取不到
	   //alert((document.getElementById("user")).value);
	   
	   //接收Servlet发送过来的指令来确定是否弹窗
	   /* function Error(){
	     String re = request.getParameter("error");
         if(re!=null){
         
         } */
	   }
	   </script>
  </head>
  
  <body>
  <!-- border="1px" bordercolor="red" 加上帮助的分割线 -->
  
  <form action="servlet/compare" name="login" method="post">
  <table background="images/Local.png" width="960" height="720">
  
  <tr>
     <td style="width: 402px; "></td> 
     <td style="height: 297px; "></td> 
     <td></td>
  </tr>
  
  <tr>
     <td></td>
<!-- 用户名窗口 -->
    <td style="border:none;color:none;background:none; height: 34px;width:26px;">
          <input type="text" name="user" id="user" size="26">
     </td>
     <td></td>

  </tr>
  
  <tr>
     <td ></td>
<!-- 密码窗口 -->
     <td style="border:none;color:none;background:none; height:11px ;width:27px">
          <input type="password"name="password"id="password"size="26">
     </td>
     <td ></td>
  </tr>
  
  <tr>
     <td width="25" height="46">
     </td>
     <!-- <td >
        <input type="button"value="登录"onclick="login.submit()"style="width:50px;height:30px;margin-left:10px"> padding-left:30px只能调整文字的初始位置
     </td> -->
<!-- 十字架： cursor:crosshair 手：hand（不存在！）login.submit()（火狐中 手：pointer） 帮助：help 忙，等待wait-->
     <td valign="bottom">
      <!-- fade() -->
        <span onclick="login.submit()" style="margin:0px 0px 6px 10px;cursor:pointer;width:75px;height:20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
         <!-- 提交给自己 -->
         <span onclick="login.reset()" style="margin:0px 0px 6px 10px;cursor:pointer;width:75px;height:20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
     </td>
     <td></td>
  </tr>
  
 <!--  以下是空白区 -->
   <tr>
     <td ></td>
     <td ></td>
     <td ></td>
  </tr>
  </table>

  </form>
  
  
 <%--  <%  String re = request.getParameter("error");
      if(re!=null){
	     out.println("<h2>用户名或者密码错误，请重新输入！！</h2>");
	     //错误弹窗的写法，尚未解决
	     //out.print("<script type='text/javascript' lanuage='javascript'>alert('Password Wrong ! ! ');window.location.href='/FirstWeb/imageLogin.jsp';</script>;");
	  } 
  %> --%>
  </body>
</html>

<!-- 由Servlet发回一个参数，赋值给这个页面上的一个参数，再由JS获取该参数，弹窗警告 -->