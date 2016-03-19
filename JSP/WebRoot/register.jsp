<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");//设置接受中文的语句
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String error = request.getParameter("error");
/* if(error!=null)//如果不加这句话，下面的error对象就会因为没有初始化，而报空指针异常
{
  if(error.equals("dis"))
    out.println("<h2 >！密码不一致，请重新输入！</h2>");
  if(error.equals("isnulls"))
    out.println("<h2 >属性值不能为空</h2>");
  if(error.equals("long"))
    out.println("<h2 >用户名长度过长</h2>");
  if(error.equals("passmis"))
    out.println("<h2 >密码不符合规范</h2>");
    if(error.equals("emailmis"))
    out.println("<h2 >邮件地址不符合规范</h2>");
} */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script >

  if("<%=error%>" == "dis"){
    alert("！密码不一致，请重新输入！");
  }
  if("<%=error%>" == "isnulls"){
    alert("属性值不能为空");
  }
  if("<%=error%>" == "/JSP/Blog.jsp"){
    alert("用户名长度过长");
  }
  if("<%=error%>" == "dis"){
    alert("密码不符合规范");
  }
  if("<%=error%>" == "emailmis"){
    alert("邮件地址不符合规范");
  }

</script>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
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
    <form action="servlet/AddControl" method="post">
     <table>
       
       <tr style="height: 40px; ">
         <td>名称：</td><td><input type="text"name="user"></td>
       </tr>
       <tr style="height: 40px; ">
         <td>密码： </td><td><input type="password"name="password1"></td>
       </tr>
       <tr style="height: 40px; ">
         <td>确认密码： </td><td><input type="password" name="password2"></td>
       </tr>
       <tr style="height: 40px; ">
         <td >邮件地址： </td><td><input type="text"name="email"></td>
       </tr>
       <tr style="height: 79px; ">
       <td></td>
       <td>
       <input type="submit" value="注册" style="width: 83px;  margin:0px 0px 6px 0px;cursor:pointer;">
        </td>
       </tr>
     </table>
    </form>
    <h4>注意：用户名不超过20个字符，密码由字母和数字构成，邮件符合规范</h4>
    <a href='/JSP/Blog.jsp'>返回登录页面</a>
  </body>
</html>
