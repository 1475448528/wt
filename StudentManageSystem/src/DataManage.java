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
/**
 * ���ҳ����ǵ�½�ɹ��󣬿���������ҳ��
 *  �϶�����Servletʵ�֣�JSP����ʵ�֣���
 *  
 *      ����չʾ����ҳ���ܵ�ʵ��
 *      
 * @package   
 * @author    Myth
 * @date      2016��3��11��
 * @version   v1.0
 */

public class DataManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DataManage() {
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
		manage(request,response);
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
		manage(request,response);
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	public void manage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ҳ������
		int pageSize = 3;//һҳ��ʾ������¼
		int pageNow = 1;//ϣ����ʾ�ڼ�ҳ
		int rowCount = 0;//һ���м�����¼(���)
		int pageCount = 0;//һ���м�ҳ
		
		Connection cn;
		PreparedStatement ps;
		ResultSet rs ;
		
		String url="jdbc:mysql://localhost:3306/student",user="myth" ,password="ad";
		
		//�õ�rowCount
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn  = DriverManager.getConnection(url, user, password);
			//һ�������
//			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?user=myth&password=ad&userUnicode=true&characterEncoding=UTF-8");
			ps = cn.prepareStatement("select count(*) from student");
			
			rs = ps.executeQuery();
			if(rs.next()){
				rowCount = rs.getInt(1);
				
			}
			if(rowCount%pageSize == 0){
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Message Page</TITLE></HEAD>");
		out.println("  <BODY>");
	
		out.println("using the Manage method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
