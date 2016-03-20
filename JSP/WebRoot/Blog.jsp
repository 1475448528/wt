<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String error = request.getParameter("error");

//if(error!=null)
  //if(error.equals("yes"))
     //out.print("<h1>用户名或密码错误！！</h1>");
Cookie[] cookies = request.getCookies();
 int i = 0;
   if(cookies !=null && error==null )
		for(Cookie cookie:cookies){
		  i++;
		  if(! cookie.getName().equals("JSESSIONID")){
		    String name = cookie.getName();
		    String value = cookie.getValue();
		    System.out.print("\n的第"+i+"次是:"+name+"-"+value+"\n");
		    
		    if("Myth".equals(name) && "ad".equals(value)){
			    if("logout".equals(error)){
	               //cookie.setMaxAge(0);  这句话是不起作用的，因为cookie在创建之前才设定好时间，创建后就！！
	               //System.out.println("删除");
	            }
		      request.setAttribute(name, value);
		      //要使用forward才能把request的属性发过去，使用重定向的话，request就不会发过去了
		      request.getRequestDispatcher("servlet/BlogLogins?name="+name+"").forward(request, response);
		      //手贱  加上单引号干嘛，又不是数据库！
		      //response.sendRedirect("servlet/BlogLogins");
		      return;
		    }else{
		   //response.sendRedirect("/JSP/Blog.jsp?error=1");
		    System.out.print("\n获取失败");
		    }
		}else{
		 System.out.print("\n第"+i+"次获取到的是Session");
		 //continue; // 如果break了就只获取了一次cookie就退出了不再获取了
		}
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script >

  if("<%=error%>" == "yes" ){
     alert("用户名或密码错误！！");
    /* 显示不了，因为后面一直在重定向  */
    <%if(error!=null&&!"logout".equals(error)) response.sendRedirect("/JSP/Blog.jsp");%>
    
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
	<link rel="stylesheet" type="text/css" href="css/font.css">

  </head>
  
  <body>
  <form action="servlet/BlogLogins"method="post" name ="logins">
    <table class="login" background="images/Zhou.jpgs" width="800px" height="200px" > 
    <tr>
    <td></td>
    <td><span><img src="images/Blog2.jpg"style="height: 140px; width: 398px"/></span><td>
    <td></td>
    </tr>
    
     <tr> 
        <td style="width: 220px; "></td>
        <td style="height: 44px; " >User:<input type="text" name="user" style="width: 160px; "></td>
        <td></td>
     </tr>
      <tr > 
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
        <td style="height: 63px; "><span><input type="button" value="Register" onclick="javascript:window.location.href='/JSP/register.jsp';" style="height: 30px; width: 60px; margin:0px 0px 6px 40px;cursor:pointer;"class="button">
       <input type="submit"  value="Login" style="height: 30px; width: 60px; margin:0px 0px 6px 30px;cursor:pointer;"class="button"></span></td><!-- background: url(images/qq.gif) -->
       <td></td>
      </tr>
      
    </table>
  </form>
  </body>
  
</html>
