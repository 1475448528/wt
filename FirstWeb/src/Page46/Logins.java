/**
 * 登录页面
 */
package Page46;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String name=request.getParameter("user");
		String pass = request.getParameter("password");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>登录成功</TITLE></HEAD>");
		out.println("  <BODY>");
		
		if(name.equals("myth") && pass.equals("11")){
			out.print("<h2>Login Suessful.....</h2>");
			out.print("<img src='http://images.517best.com/UploadFiles/images/s/20120704/20120704195258320107323.jpg'width='600'height='161'> ");
			
		}else{
			//失败，关于这个 报错的弹窗  功能提问都是10年左右的，难道这个已经失去潮流了---
//			response.getWriter().print("<script lanuage='javascript'>alert('Password Wrong ! ! ');window.location.href='/FirstWeb/imageLogin.jsp';</script>;");
//		    System.out.println("lose");
			response.sendRedirect("/FirstWeb/imageLogin.jsp");
			
		}
		out.println("  </BODY>");
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String user=request.getParameter("user");
		out.println("<h3>user:"+user+"</h3>");
		String pass=request.getParameter("password");
		out.println("<h3>password:"+pass+"</h3>");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
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
