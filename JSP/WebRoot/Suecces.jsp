<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
HttpSession pu = request.getSession(true);
String name = (String)pu.getAttribute("name");
String error = request.getParameter("error");
   //使用之前先要导入包  <%@ page import="自定义类的路径"% >  导包貌似出错了
   //SqlHelper h = new SqlHelper();
   
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<script >
<%-- function insert(){
    var name = <%=name%>;
	var data=((document.getElementById("user")).value);
	var now = new Date();//创建一个时间对象

} --%>
  if("<%=error%>" == "nulls" ){
     alert("发送内容不能为空");
  }

</script>
    <title>My JSP 'Suecces.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
    <link href="css/font.css" rel="stylesheet" type="text/css" />
          <!-- 使用外部的CSS样式 -->
  </head>
  
  <body>
  
   <img src='/JSP/images/Blog2.jpg' style='height: 45px; width: 130px'/>
  <form action='servlet/Data' method='post' name='po'>
  <table background='/JSP/images/BlogPush2.PNG' width='600px' height='161px' border=0px>
  <tr>
   <td>
     <textarea name="msg"  style='height:86px;width: 585px;margin:30px 0px 0px 7px;'>请输入......</textarea>
     <!-- 这两个标签之间不能有空格或者换行，会显示一部分空格出来的  应该大多数都是，要注意这个问题-->
   </td>
    <!-- <td height=82px;width=580px;><input type='text' name='msg' id='msg'style='height:86px;width: 585px;margin:30px 0px 0px 7px;'>
    </td> -->
  </tr>
  <tr>
     <td  valign='bottom' height=28px;width=590px; style='height:28px;width: 580px;'>
       <span onclick='po.submit()'style='margin:0px 0px 0px 500px;cursor:pointer;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       </span>
     </td>
  </tr>
    </table>
    </form>
     <p class="font">用户：<%=name%></p>     <br>
     <a href='Blog.jsp?error=logout'>注销</a>
     <a href='Blog.jsp'>登录</a>
      <a href='servlet/Data'>全部动态</a>
      <a href='servlet/Data?user=me'>个人主页</a>
  </body>
</html>
