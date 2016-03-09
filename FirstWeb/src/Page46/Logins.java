/**
 * ��¼ҳ�� Servlet
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

		response.setContentType("text/html;charset=UTF-8");//�������ΪUTF-8���룬�����������
		
		PrintWriter out = response.getWriter();
		
		out.println("<h1>����</h1>");
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
		

		request.setCharacterEncoding("UTF-8");//�������ĵķ����������ڴ���request֮ǰ���ñ���Ϊutf-8
		
		
		String user=request.getParameter("user");
		String pass=request.getParameter("password");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Login yanzheng</TITLE></HEAD>");
		out.println("  <BODY>");
		
		out.println("<h3>user:</h3>"+user+"");
		out.println("<h3>password:</h3>"+pass+"");
		
		//��֤�û��������룺
		if(user.equals("��Сϣ") && pass.equals("11")){
			//���û���¼�ɹ�����Ϣд��Session���棬��ֹ�û�ֱ������URL�����ʵ��ҵ�¼֮���ҳ��
			
			
			out.print("<h2>Login Suessful.....</h2>");
			out.print("<img src='http://images.517best.com/UploadFiles/images/s/20120704/20120704195258320107323.jpg'width='600'height='161'> ");
			
		}else{
			//ʧ�ܣ�������� ����ĵ���  �������ʶ���10�����ҵģ��ѵ�����Ѿ�ʧȥ������---
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

// Web�������ݿ� ����ͨ����������ݿⲻͬ���������ر�ĵط�����ʱ��������Mysql���ݿ�
/*PreparedStatement ps = null;
Connection cn = null;
ResultSet rs = null;

try{
	//��ʼ�����ǵĶ���
	//1 ��������
	Class.forName("com.mysql.jdbc.Driver");
	//2 �õ�����
	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login?user=myth&password=ad&userUnicode=true&characterEncoding=UTF8");
	//����һ��preparedStatement�������ڷ���
	ps=cn.prepareStatement("select * from user");
	rs = ps.executeQuery();
	System.out.println("Name\tPassword");
	while(rs.next()){
		System.out.println(rs.getString(1)+" "+rs.getString(2));
	}
	
}catch(Exception e){
	e.printStackTrace();
	System.out.println("�������ش���");
}finally {
	//�ȿ���� �ر���Դ
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
			System.out.println("��Դ�ر��쳣");
		}
	
}
*/






