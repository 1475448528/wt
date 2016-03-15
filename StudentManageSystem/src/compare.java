/**
 * ���������жϵ�һ����  ����Ŀʹ�õ���Student���ݿ��Student��
 * 
 *   �ɹ� ��ת�������ݴ���ҳ��
 *   ʧ�� ��ת�ص���½ҳ�棬���ҷ���һ������������� ����JS�����Ƶ�����
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


public class compare extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public compare() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("user");
		String passwords = request.getParameter("password");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>JDBC</TITLE></HEAD>");
		out.println("  <BODY>");
		
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/student";
		String user="myth" ;
		String password="ad";
		
		//�������ݿ�
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			cn  = DriverManager.getConnection(url, user, password);
//			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?user=myth&password=ad&userUnicode=true&characterEncoding=UTF-8");
			
//			ps = cn.prepareStatement("select * from student");
//			rs = ps.executeQuery();
//			out.println("ѧ��\t\t����\t\t�Ա�<br>");
//			while(rs.next()){
//				out.println(rs.getString(1)+"\t\t "+rs.getString(2)+"\t\t"+rs.getString(3)+"<br>");
//			}
			ps = cn.prepareStatement("select * from student limit 0,1");
			rs = ps.executeQuery();
			if(rs.next()){
				
				String name = rs.getString(2);
				String pass = rs.getString(6);
				
//				System.out.println("ϵͳ���������ȷ �û������룺 name:"+name+"<br>pass:"+pass+"<br>");
				
//				if(name.equals("admin") && pass.equals("ad")){
//					out.print("<h3>��¼�ɹ�����</h3>");
//				}
				if(name.equals(username) && pass.equals(passwords)){
					out.print("<h3>��¼�ɹ�����</h3>");
					//���ݴ����servlet
					response.sendRedirect("DataManage");
				}else{
//					out.println("��¼ʧ��");
					response.sendRedirect("/StudentManageSystem?error=yes");
				}
			}
			
		}catch(Exception y){
			y.printStackTrace();
		}finally{
			//�ر���Դ �ȿ����
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
