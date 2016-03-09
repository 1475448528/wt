/**
 * 登录页面 Servlet
 */
package Page46;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logins extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Logins() {
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

		response.setContentType("text/html;charset=UTF-8");//设置输出为UTF-8编码，可以输出中文
		
		PrintWriter out = response.getWriter();
		
		out.println("<h1>当代</h1>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		

		request.setCharacterEncoding("UTF-8");//接收中文的方法，就是在处理request之前设置编码为utf-8
		
		
		String user=request.getParameter("user");
		String pass=request.getParameter("password");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Login yanzheng</TITLE></HEAD>");
		out.println("  <BODY>");
		
		out.println("<h3>user:</h3>"+user+"");
		out.println("<h3>password:</h3>"+pass+"");
		
		//验证用户名和密码：
		if(user.equals("旷小希") && pass.equals("11")){
			//将用户登录成功的信息写入Session里面，防止用户直接输入URL来访问到我登录之后的页面
			
			
			out.print("<h2>Login Suessful.....</h2>");
			out.print("<img src='http://images.517best.com/UploadFiles/images/s/20120704/20120704195258320107323.jpg'width='600'height='161'> ");
			
		}else{
			//失败，关于这个 报错的弹窗  功能提问都是10年左右的，难道这个已经失去潮流了---
//			response.getWriter().print("<script type='text/javascript' lanuage='javascript'>alert('Password Wrong ! ! ');window.location.href='/FirstWeb/imageLogin.jsp';</script>;");
//		    System.out.println("lose");
			response.sendRedirect("/FirstWeb/imageLogin.jsp");
			
			
		}
		
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	
}

// Web调用数据库 和普通的类调用数据库不同，可能有特别的地方，暂时不会连接Mysql数据库
/*PreparedStatement ps = null;
Connection cn = null;
ResultSet rs = null;

try{
	//初始化我们的对象
	//1 加载驱动
	Class.forName("com.mysql.jdbc.Driver");
	//2 得到连接
	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login?user=myth&password=ad&userUnicode=true&characterEncoding=UTF8");
	//创建一个preparedStatement对象用于发送
	ps=cn.prepareStatement("select * from user");
	rs = ps.executeQuery();
	System.out.println("Name\tPassword");
	while(rs.next()){
		System.out.println(rs.getString(1)+" "+rs.getString(2));
	}
	
}catch(Exception e){
	e.printStackTrace();
	System.out.println("驱动加载错误");
}finally {
	//先开后关 关闭资源
		try {
			if(rs!=null)
			    rs.close();
			if(ps!=null)
				ps.close();
			if(cn!=null)
				cn.close();
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("资源关闭异常");
		}
	
}
*/






