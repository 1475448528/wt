package Session;
/**
 * 连接数据库 的操作，使用起来和 普通的类调用是一样的
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginCl() {
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
		doPost(request, response);
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
		
		String username = request.getParameter("username");
		String passwords = request.getParameter("pass");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>JDBC</TITLE></HEAD>");
		out.println("  <BODY>");
		
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/student",user="myth" ,password="ad";
		
		//连接数据库
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			cn  = DriverManager.getConnection(url, user, password);
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?user=myth&password=ad&userUnicode=true&characterEncoding=UTF-8");
			
//			ps = cn.prepareStatement("select * from student");
//			rs = ps.executeQuery();
//			out.println("学号\t\t姓名\t\t性别<br>");
//			while(rs.next()){
//				out.println(rs.getString(1)+"\t\t "+rs.getString(2)+"\t\t"+rs.getString(3)+"<br>");
//			}
			ps = cn.prepareStatement("select * from student limit 0,1");
			rs = ps.executeQuery();
			if(rs.next()){
				
				String name = rs.getString(2);
				String pass = rs.getString(6);
				
//				System.out.println("系统查出来的正确 用户和密码： name:"+name+"<br>pass:"+pass+"<br>");
				
//				if(name.equals("admin") && pass.equals("ad")){
//					out.print("<h3>登录成功！！</h3>");
//				}
				if(name.equals(username) && pass.equals(passwords)){
					out.print("<h3>登录成功！！</h3>");
				}else{
//					out.println("登录失败");
					response.sendRedirect("Login?error=yes");
				}
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
		
		
		
		out.println("  </BODY>");
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
