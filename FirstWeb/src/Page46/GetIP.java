/**
 * 书上的46页题目
 *   1:获取客户端的IP和访问时间一发出的查询字符串
 *        最近在进行web开发时，遇到了request.getRemoteAddr()获取的值为0:0:0:0:0:0:0:1，
 *        这是为什么呢，照道理讲，应该是127.0.0.1才对，为什么这个获取的值变成了ipv6了呢，
 *        而且我发现这种情况只有在服务器和客户端都在同一台电脑上才会出现（例如用localhost访问的时候才会出现），后来上网查了查原因，
 *        原来是/etc/hosts这个东西作怪(在windows上应该是C:\Windows\system32\drivers\etc\ hosts这个文件)，只需要注释掉文件中的 # ::1     localhost  
 *        这一行即可解决问题。另外localhost这个文件很有用，这里你可以添加自己的条目，例如添加 192.168.0.212 myweb 这样子
 *        在浏览器中原来只能使用192.168.0.212来访问的，并可以使用myweb来进行替换。
 */
package Page46;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetIP extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetIP() {
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
		
		String pp = request.getParameter("query");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method<br>");
		
		out.println("您的IP:"+request.getLocalAddr()+"<br>");
		out.println("您访问的时间是："+new java.util.Date());
		out.println("<br>你发送的字符串是："+pp);
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
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
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
