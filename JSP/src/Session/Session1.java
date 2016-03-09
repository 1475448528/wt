package Session;
/**
 *       ！！！ 防止用户通过非法的路径访问到登陆后的资源页面  ！！
 *     
 *     
 *     
 * 这里创建一个Session 在那个Session2类里读取Session类的属性 如果属性时间到了，就会被清除
 * 说实话，这个Session就像是一个临时的Map表一样,其方法都类似
 * 
 *    通过Login创建一个session表然后Session1来验证，这样就解决了非法输入URL地址直接登录的问题
 *    通过设置Session的生存时间来控制Session表中的数据存在与否
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Session1 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Session1() {
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

		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		
		
		
		/**验证Session*/
		//得到和request相关联的session，如果没有就创建一个
		HttpSession pu = request.getSession(true);
		//得到Session的id，这是由容器分配的
		String sessionId = pu.getId();
		out.println("Session1 的id是："+sessionId);
		//得到Session的某个属性
		String name = (String)pu.getAttribute("name");
		

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");

		if(name!=null){
			out.println("<h3>Login创建的session中的name="+name+"</h3>");
			String user = request.getParameter("username");
			String pass = request.getParameter("pass");
			
			out.println("表单的name:"+user+"<br>表单的password:"+pass);
			
			//关于跳转到一个HTML文件的代码
//			response.sendRedirect("Session/Success.html");
			
		}else{
			
			out.println("<h3>非法登录该页面！！！</h3>");
			
		}
		
/**创建Session*/
//		//得到和request相关联的session，如果没有就创建一个
//		HttpSession pu = request.getSession(true);
//		//得到Session的id，这是由容器分配的
//		String sessionId = pu.getId();
//		//向Session中添加一个属性（String类型的）
//		pu.setAttribute("name", "myth");
//		//如果不指定时间，该Session的有效期是30min
//		//指定时间是30s
//		pu.setMaxInactiveInterval(30);
////		如果要删除整个Session 就设置时间为0就可以了
//		//删除属性
//		pu.setAttribute("you", "Eason");
//		out.println("<br>没有删除前 you 的值是："+pu.getAttribute("you"));
//		pu.removeAttribute("you");
//		out.println("<br>删除后 you 的值是："+pu.getAttribute("you"));
//		
//		out.println("<h5>sessionId:"+sessionId+"name:"+pu.getAttribute("name")+"</h5>");
		
		

		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
//		
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
