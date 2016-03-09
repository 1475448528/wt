package Session;
/**
 *       ������ ��ֹ�û�ͨ���Ƿ���·�����ʵ���½�����Դҳ��  ����
 *     
 *     
 *     
 * ���ﴴ��һ��Session ���Ǹ�Session2�����ȡSession������� �������ʱ�䵽�ˣ��ͻᱻ���
 * ˵ʵ�������Session������һ����ʱ��Map��һ��,�䷽��������
 * 
 *    ͨ��Login����һ��session��Ȼ��Session1����֤�������ͽ���˷Ƿ�����URL��ֱַ�ӵ�¼������
 *    ͨ������Session������ʱ��������Session���е����ݴ������
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
		
		
		
		/**��֤Session*/
		//�õ���request�������session�����û�оʹ���һ��
		HttpSession pu = request.getSession(true);
		//�õ�Session��id�����������������
		String sessionId = pu.getId();
		out.println("Session1 ��id�ǣ�"+sessionId);
		//�õ�Session��ĳ������
		String name = (String)pu.getAttribute("name");
		

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");

		if(name!=null){
			out.println("<h3>Login������session�е�name="+name+"</h3>");
			String user = request.getParameter("username");
			String pass = request.getParameter("pass");
			
			out.println("����name:"+user+"<br>����password:"+pass);
			
			//������ת��һ��HTML�ļ��Ĵ���
//			response.sendRedirect("Session/Success.html");
			
		}else{
			
			out.println("<h3>�Ƿ���¼��ҳ�棡����</h3>");
			
		}
		
/**����Session*/
//		//�õ���request�������session�����û�оʹ���һ��
//		HttpSession pu = request.getSession(true);
//		//�õ�Session��id�����������������
//		String sessionId = pu.getId();
//		//��Session�����һ�����ԣ�String���͵ģ�
//		pu.setAttribute("name", "myth");
//		//�����ָ��ʱ�䣬��Session����Ч����30min
//		//ָ��ʱ����30s
//		pu.setMaxInactiveInterval(30);
////		���Ҫɾ������Session ������ʱ��Ϊ0�Ϳ�����
//		//ɾ������
//		pu.setAttribute("you", "Eason");
//		out.println("<br>û��ɾ��ǰ you ��ֵ�ǣ�"+pu.getAttribute("you"));
//		pu.removeAttribute("you");
//		out.println("<br>ɾ���� you ��ֵ�ǣ�"+pu.getAttribute("you"));
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
