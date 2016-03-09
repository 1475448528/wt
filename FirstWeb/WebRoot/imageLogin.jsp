<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
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

	   <script type="text/javascript" language="javascript">
	   function fade (){
	   if(((document.getElementById("user")).value)=="旷小希" && ((document.getElementById("password")).value)=="11") document.login.submit();
	   else{
	   alert("用户名或密码错误！！");
	   return true;
	   }
	   //document.getElementsByName("user").value
	   
	   document.login.submit();
	   //用id才能获取到那个值，name就会说未定义，获取不到
	   //alert((document.getElementById("user")).value);
	   }
	   </script>
  </head>
  
  <body>
  <!-- border="1px" bordercolor="red" 加上帮助的分割线 -->
  
  <form action="servlet/Logins" name="login" onsubmit="fade()" method="post">
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
    
        <span onclick="fade()" style="margin:0px 0px 6px 10px;cursor:pointer;width:75px;height:20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
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
  </body>
</html>
<!-- 做好了登录页面的基本功能，不得不说曲折。。。 -->

<!--   关于valgin的属性 -->
  <!-- <table border="1">
  <tr>
    <th>Month</th>
    <th>Savings</th>
  </tr>
  <tr>
    <td valign="top">January</td>
    <td valign="top">$100</td>
  </tr>
  <tr>
    <td valign="bottom">February</td>
    <td valign="bottom">$80</td>
  </tr>
</table> -->
<!-- 关于鼠标某一区域的监听 -->
<!-- </form>
  
  <form action="" name="login">
  <table background="image/web.jpg"width="994"height="554">
  <tr><td wdith="50"height="5"><input type="text"name="name"></td></tr>
  <tr><td valign="bottom">
    <span onclick="login.submint()" style="cursor:hand;width:30px;height:50px"> 5</span>
     提交给自己
  </td></tr>
  </table>
    -->