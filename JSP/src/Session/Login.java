package Session;
/**
 * 
 *   ����һ����¼����
 *   
 *   
 * ˢ��һ�βŻᴴ��һ��Sessionʵ��
 *  SessionҲ���������������ݣ������Ըߵ����ݣ���
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
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
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Login JDBC</TITLE></HEAD>");
		out.println("  <BODY>");
//		
		//�õ���request�������session�����û�оʹ���һ��
		HttpSession pu = request.getSession(true);
		//��Session������һ�����ԣ�String���͵ģ�
		pu.setAttribute("name", "myth");
		//ָ��ʱ����30s ��û�ж��������ʱ�䣬������һ������ʼ��ʱ��20s
		pu.setMaxInactiveInterval(20);
		
		String sessionId = pu.getId();
		
		String re = request.getParameter("error");
		
		
		if(re!=null){
			out.println("<h2>�û�����������������������룡��</h2>");
		}
		out.println("Login ��id�ǣ�"+sessionId);	
		
//		out.println("<form action=Session1 method=post>");//��֤һ��Session
		out.println("<form action=LoginCl method=post>");//�������ݿ�
		
		out.println("�û�����<input type=text name=username><br>");
		out.println("���룺<input type=password name=pass><br>");
		out.println("<input type=submit value=Login><br>");
		
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