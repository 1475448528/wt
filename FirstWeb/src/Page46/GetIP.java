/**
 * ���ϵ�46ҳ��Ŀ
 *   1:��ȡ�ͻ��˵�IP�ͷ���ʱ��һ�����Ĳ�ѯ�ַ���
 *        ����ڽ���web����ʱ��������request.getRemoteAddr()��ȡ��ֵΪ0:0:0:0:0:0:0:1��
 *        ����Ϊʲô�أ��յ�����Ӧ����127.0.0.1�Ŷԣ�Ϊʲô�����ȡ��ֵ�����ipv6���أ�
 *        �����ҷ����������ֻ���ڷ������Ϳͻ��˶���ͬһ̨�����ϲŻ���֣�������localhost���ʵ�ʱ��Ż���֣��������������˲�ԭ��
 *        ԭ����/etc/hosts�����������(��windows��Ӧ����C:\Windows\system32\drivers\etc\ hosts����ļ�)��ֻ��Ҫע�͵��ļ��е� # ::1     localhost  
 *        ��һ�м��ɽ�����⡣����localhost����ļ������ã��������������Լ�����Ŀ��������� 192.168.0.212 myweb ������
 *        ���������ԭ��ֻ��ʹ��192.168.0.212�����ʵģ�������ʹ��myweb�������滻��
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
		
		out.println("����IP:"+request.getLocalAddr()+"<br>");
		out.println("�����ʵ�ʱ���ǣ�"+new java.util.Date());
		out.println("<br>�㷢�͵��ַ����ǣ�"+pp);
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
