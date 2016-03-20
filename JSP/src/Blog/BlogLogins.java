package Blog;
/**
 * 使用的是Student数据库下的Bloguser表
 *   关于自动登录的cookie功能实现尚未找到思路。。。
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BlogLogins extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BlogLogins() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		PrintWriter out = response.getWriter();
		String username=request.getParameter("name");
//		System.out.println(username);
		String passwords = (String)(request.getAttribute(username.toString()));
//		System.out.println("获取的密码是："+passwords);
		String auto=null;
		
		
		myth(request,response,out,username,passwords,auto);
		 
//		response.setContentType("text/html");
//		
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>Get</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.println("User:"+request.getAttribute("Myth"));
//		out.println(" using the GET method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		    response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>Logins</TITLE></HEAD>");
			out.println("  <BODY>");
			
			String username = request.getParameter("user");//这是接受的参数
			String passwords = request.getParameter("password");
			String auto = request.getParameter("auto");
			
			myth(request,response,out,username,passwords,auto);
		}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	/**
	 * @param out 输出流
	 * @param username 用来查询的
	 * @param passwords 用来查询的
	 */
	public void myth(HttpServletRequest request, HttpServletResponse response,PrintWriter out,String username 
			,String passwords,String auto)
			throws ServletException, IOException {
		
//		System.out.println("发送来的参数："+username+":"+passwords);
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/student";
		String user="myth" ;
		String password="ad";
		
		boolean flag=true;
		//连接数据库
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			cn  = DriverManager.getConnection(url, user, password);
//			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?user=myth&password=ad&userUnicode=true&characterEncoding=UTF-8");
			
//			ps = cn.prepareStatement("select * from student");
//			rs = ps.executeQuery();
//			out.println("学号\t\t姓名\t\t性别<br>");
//			while(rs.next()){
//				out.println(rs.getString(1)+"\t\t "+rs.getString(2)+"\t\t"+rs.getString(3)+"<br>");
//			}
			ps = cn.prepareStatement("select * from Bloguser where name=? and password=?");
			ps.setString(1, username);
			ps.setString(2, passwords);
			rs = ps.executeQuery();
			if(rs.next()){
				
				String name = rs.getString(1);
				String pass = rs.getString(2);
				
//				System.out.println("系统查出来的正确 用户和密码： name:"+name+"<br>pass:"+pass+"<br>");
				
//				if(name.equals("admin") && pass.equals("ad")){
//					out.print("<h3>登录成功！！</h3>");
//				}
				if(!(name.equals(username) && pass.equals(passwords))){
					flag=false;
				}
				
				
			}else{//查数据库查找不到记录
				flag=false;
			}
		}catch(Exception y){
			y.printStackTrace();
		}finally{
			//关闭资源 先开后关
			try {
				if(rs!=null)  rs.close();
				if(ps!=null)  ps.close();
				if(cn!=null)  cn.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
		
		if(!flag){
			System.out.println("登录失败");
		  response.sendRedirect("/JSP/Blog.jsp?error=yes");//比对身份和密码
		}
		
/**创建一个Cookie*/
		if("auto".equals(auto)){//如果勾选了自动登录的单选框,就创建一个cookie
			Cookie cookie = new Cookie("Myth","ad");//键值对
			cookie.setPath("/");//困扰了几天之久的cookie 终于解决了，是的没错，又是路径问题！！！
			cookie.setMaxAge(5*60);//单位是Second 秒
			response.addCookie(cookie);
			request.setAttribute("Myth", "ad");
			System.out.println("\nCookie创建成功");
			
			
		}
		/**获取一个Session*/
		//得到和request相关联的session，如果没有就创建一个
		HttpSession pu = request.getSession(true);
		//向Session中添加一个属性（String类型的）
		pu.setAttribute("name", username);
		//指定时间是30s 是没有动浏览器的时间，而不是一创建后开始计时的20s
		pu.setMaxInactiveInterval(200);
		//输出id 就会发现，该Session是保存在cookie里的
//		System.out.println("ID:"+pu.getId());
		
		out.println(auto);
		if(flag){//因为所有代码都会执行，除非是return了，所以在已经重定向后，是不能再进行forward引用的
			System.out.println("登录成功");
		    request.getRequestDispatcher("/Suecces.jsp").forward(request, response);//引用 一个页面
		}
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
/**
 *                        页面
 *                        
 * /*out.println("<img src='/JSP/images/Blog2.jpg' style='height: 45px; width: 130px'/>");
//		out.println(""+username+" 你好");
		out.println("<form action='Data' method='post' name='po'><table background='/JSP/images/BlogPush2.PNG' width='600px' height='161px' border=0px>");
		//自己做的，没有使用CSS 实在是不好美化
//		out.println("<tr>有什么新鲜事想告诉大家？ &nbsp&nbsp&nbsp&nbsp&nbsp  <a href='http://pay.vip.weibo.com/vippay?from=pc140'>会员可以发布超过140字的微博啦，开通会员立即体验 》</a></tr>");
//		out.println("<tr><td style='height:300px;width: 50px;'>"+"框"+"</td><td><input type='text' name='inputs'style='height:300px;width: 500px;' ></td></tr>");
//		
//		out.println("<tr><td></td><td><span>表情&nbsp&nbsp&nbsp&nbsp</span><span>&nbsp&nbsp&nbsp&nbsp图片&nbsp&nbsp&nbsp&nbsp</span><span>&nbsp&nbsp&nbsp&nbsp视频&nbsp&nbsp&nbsp&nbsp</span><span>&nbsp&nbsp&nbsp&nbsp话题"
//				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><span>&nbsp&nbsp"
//				+ "<input type='submit' value='发布'></span></td></tr>");
		
//		out.println("<img src='/JSP/images/BlogPush2.PNG'>");
		out.println("<tr><td height=82px;width=580px;><input type='text' name='msg'style='height:86px;width: 580px;margin:30px 0px 0px 7px;'></td></tr>");
		out.println("<tr><td  valign='bottom' height=28px;width=580px; style='height:28px;width: 580px;'><span onclick='po.submit()'style='margin:0px 0px 0px 500px;cursor:pointer;'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></td></tr>");
//		out.println("<tr><td valign='bottom'><span onclick='po.submit()' style='margin:0px 0px 6px 560px;cursor:pointer;width:75px;height:20px'>&nbsp</span></td></tr>");
		
		out.println("</table></form>");
		
		                   获取Cookie
		                   
           Cookie[] cookies = request.getCookies();
			 int i=0;
			if(cookies !=null )
			for(Cookie cookiee:cookies){
			    String name = cookiee.getName();
			    String value = cookiee.getValue();
			    System.out.println("i="+i++);
			    System.out.print("value:"+value+" name:"+name);
			    
			    if("Myth".equals(name) && "ad".equals(value)){
			      request.setAttribute(name, value);
//			      System.out.println("myth");
			      return;
			    }else{
			    System.out.print("比对失败");
			    }
			}
//			response.sendRedirect("/JSP/Blog.jsp");
*/
