package Blog;
/**
 * ʹ�õ���Student���ݿ��µ�Bloguser��
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlogLogins extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BlogLogins() {
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
		
		    response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>Logins</TITLE></HEAD>");
			out.println("  <BODY>");
			
			String username = request.getParameter("user");//���ǽ��ܵĲ���
			String passwords = request.getParameter("password");
			String auto = request.getParameter("auto");
			
			Connection cn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String url="jdbc:mysql://localhost:3306/student";
			String user="myth" ;
			String password="ad";
			
			boolean flag=true;
			//�������ݿ�
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				cn  = DriverManager.getConnection(url, user, password);
//				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?user=myth&password=ad&userUnicode=true&characterEncoding=UTF-8");
				
//				ps = cn.prepareStatement("select * from student");
//				rs = ps.executeQuery();
//				out.println("ѧ��\t\t����\t\t�Ա�<br>");
//				while(rs.next()){
//					out.println(rs.getString(1)+"\t\t "+rs.getString(2)+"\t\t"+rs.getString(3)+"<br>");
//				}
				ps = cn.prepareStatement("select * from Bloguser where name=?");
				ps.setString(1, username);
				rs = ps.executeQuery();
				if(rs.next()){
					
					String name = rs.getString(1);
					String pass = rs.getString(2);
					
//					System.out.println("ϵͳ���������ȷ �û������룺 name:"+name+"<br>pass:"+pass+"<br>");
					
//					if(name.equals("admin") && pass.equals("ad")){
//						out.print("<h3>��¼�ɹ�����</h3>");
//					}
					if(name.equals(username) && pass.equals(passwords)){
//						out.print("<h3>��¼�ɹ�����</h3>");
//						response.sendRedirect("LoginSuccess?name="+name);
					}else{
						flag=false;
					}
				}
				else{//�����ݿ���Ҳ�����¼
					flag=false;
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
			
			if(!flag)
			  response.sendRedirect("/JSP/Blog.jsp?error=yes");
			
			if("auto".equals(auto)){//�����ѡ���Զ���¼�ĵ�ѡ��,�ʹ���һ��cookie
				Cookie cookie = new Cookie("Myth","ad");
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
			}
//			out.println(auto);
			out.println("<img src='/JSP/images/Blog2.jpg' style='height: 45px; width: 130px'/>");
//			out.println(""+username+" ���");
			out.println("<form action='Data' method='post' name='po'><table background='/JSP/images/BlogPush2.PNG' width='600px' height='161px' border=0px>");
			//�Լ����ģ�û��ʹ��CSS ʵ���ǲ�������
//			out.println("<tr>��ʲô����������ߴ�ң� &nbsp&nbsp&nbsp&nbsp&nbsp  <a href='http://pay.vip.weibo.com/vippay?from=pc140'>��Ա���Է�������140�ֵ�΢��������ͨ��Ա�������� ��</a></tr>");
//			out.println("<tr><td style='height:300px;width: 50px;'>"+"��"+"</td><td><input type='text' name='inputs'style='height:300px;width: 500px;' ></td></tr>");
//			
//			out.println("<tr><td></td><td><span>����&nbsp&nbsp&nbsp&nbsp</span><span>&nbsp&nbsp&nbsp&nbspͼƬ&nbsp&nbsp&nbsp&nbsp</span><span>&nbsp&nbsp&nbsp&nbsp��Ƶ&nbsp&nbsp&nbsp&nbsp</span><span>&nbsp&nbsp&nbsp&nbsp����"
//					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><span>&nbsp&nbsp"
//					+ "<input type='submit' value='����'></span></td></tr>");
			
//			out.println("<img src='/JSP/images/BlogPush2.PNG'>");
			out.println("<tr><td height=82px;width=580px;><input type='text' name='msg'style='height:86px;width: 580px;margin:30px 0px 0px 7px;'></td></tr>");
			out.println("<tr><td  valign='bottom' height=28px;width=580px; style='height:28px;width: 580px;'><span onclick='po.submit()'style='margin:0px 0px 0px 500px;cursor:pointer;'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></td></tr>");
//			out.println("<tr><td valign='bottom'><span onclick='po.submit()' style='margin:0px 0px 6px 560px;cursor:pointer;width:75px;height:20px'>&nbsp</span></td></tr>");
			
			out.println("</table></form>");
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
