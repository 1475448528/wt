<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'class_method.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head><!-- java 代码还是javascript代码？ -->
  <script> String action = request.getParameter("action");//获取前面文件传送回来的参数
     //对不同的参数进行处理
     document.write(request.getParameter("user"));
     
     if(action.equals("login")){
       user=request.getParameter("user");
       msg="欢迎"+user+"光临聊天室";
       session.setAttribute("user",user);
       response.sendRedirect("main.html");//转向主界面
     }
     if(action.equals("sendMsg")){//发送用户输入页面
       if((String)(request.getParameter("msg")).equals("")){
       msg=(String)msg+"<br>"+session.getAttribute("user")+":"+(String)request.getParameter("msg");
       response.sendRedirect("inputMsg.jsp");//转向用户输入页面
       }
     }
     if(action.equals("showMsg")){//显示用户在聊天室输入的信息
     out.println("loadContent.innerHTML=\""+msg+"\";");
     response.sendRedirect("showMsg.html");
      }
     
  </script>
  <body>
    This is my class_method page. <br>
  </body>
</html>
