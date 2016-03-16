<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String error = request.getParameter("error");
//if(error!=null)
  //if(error.equals("yes"))
     //out.print("<h1>用户名或密码错误！！</h1>");
/* Cookie[] cookies = request.getCookies();
if(cookies !=null)
for(Cookie cookie:cookies){
    String name = cookie.getName();
    String value = cookie.getValue();
    if("Myth".equals(name) && "ad".equals(value)){
      request.setAttribute(name, value);
      System.out.println("myth");
      response.sendRedirect("servlet/BlogLogins");
    }
    //response.sendRedirect("/JSP/Blog.jsp");
} */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script >

  if("<%=error%>" == "yes"){
    alert("用户名或密码错误！！");
  }
</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>微博登录</title>
    
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
  <form action="servlet/BlogLogins"method="post" name ="logins">
    <table background="images/Zhou.jpgs" width="800px" height="200px"> 
    <tr>
    <td></td>
    <td><span><img src="images/Blog2.jpg"style="height: 140px; width: 398px"/></span><td>
    <td></td>
    </tr>
    
     <tr> 
        <td style="width: 220px; "></td>
        <td style="height: 44px; ">User:<input type="text" name="user" style="width: 160px; "></td>
        <td></td>
     </tr>
      <tr> 
      <td></td>
        <td style="height: 44px; ">Pass:<input type="password" name="password" style="width: 160px; "></td>
        <td></td>
      </tr>
      <tr>
      <td></td>
        <td>
         auto<input type="checkbox" name="auto" value="auto"><!-- value属性是指选中框时的值 -->
        </td>
        <td></td>
      </tr>
      <tr> 
      <td></td>
        <td style="height: 63px; "><span><input type="button" value="Register" onclick="javascript:window.location.href='/JSP/register.jsp';" style="height: 30px; width: 60px; margin:0px 0px 6px 40px;cursor:pointer;">
       <input type="submit"  value="Login" style="height: 30px; width: 60px; margin:0px 0px 6px 30px;cursor:pointer;"></span></td><!-- background: url(images/qq.gif) -->
       <td></td>
      </tr>
      
    </table>
  </form>
  </body>
  
</html>
