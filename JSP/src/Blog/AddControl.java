package Blog;
/**
 * �ṩע�������࣬ʵ�����ݿ�Ĳ������
 * ÿ�ιرշ��񶼻ᵯ���ľ���
 *  appears to have started a thread named [Abandoned connection cleanup thread] but has failed to stop it. This is very likely to create a memory leak.
 * �ƺ��Ѿ�������һ����Ϊ[�������������߳�]���̣߳���δ����ֹ������ܿ��ܻ�����ڴ�й©��

 
 Tomcat7�ǰɣ� ��������һ���ڴ�й©�ļ�飬��Ҫ���ж�webӦ������ʱ��������������ж�������ö��ͷŸɾ���

һ�������ʾ���ܵ�ԭ��
1.JDBC����ע��
2.һЩ��־���
3.��ThreadLocal�б�����󣬵��ǲ���ȥɾ����
4.�������̣߳���û��ֹͣ��

����������ܾ��Ƕ�Ӧ��Ӧ����Ҫ��һ�� ServletContextListener ��Ȼ���� contextDestroyed ������ȥ�����ϵ���������������������õ���jdbc����ô��Ҫ�ֶ�ȥע��jdbc��threadlocal�ı���Ķ����������յ����ȵȵȵȡ�����

Ҫ������������������ȻҲ���Բ���ʲô���顣��ʵҲû�ж���ϵ�ġ�

������ڴ�й©ָ��Ҳ�����ڲ���������tomcat�������,reloadĳһ��Ӧ�ã��Ż�����ڴ�й©��������ֻҪÿ������Ӧ�ö���ͨ������tomcat���Ļ���������κ�Ӱ�졣
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddControl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddControl() {
		
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
		request.setCharacterEncoding("utf-8");//���ý������ı���
		
		String user = request.getParameter("user");
		String email = request.getParameter("email");
		String pass1 = request.getParameter("password1");
		String pass2 = request.getParameter("password2");
		
		//String test = ""; �����ַ�����ʼֵ�Ĳ���
		//System.out.println("_"+test+"_");���
		//System.out.println("user:_"+user+"_email:_"+email+"_pass1:_"+pass1+"_pass2:_"+pass2);

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Control</TITLE></HEAD>");
		out.println("  <BODY>");
		boolean flag;
		flag = true;
		
		/**���µ��ǶԱ��ύ�����ݽ��д��� ���ݲ�ͬ������� �ض����ȥע��ҳ�� */
		//��Ϊ�ж����ͬһ��������ֵ��response���ܻ�ظ�������Ϊ����servlet�����д��붼��ִ�У����ܷ��� ���� ��һ�������Ĵ�ֵ��response
		//���Ծ�Ҫ��flag���޶�ֻ����һ���Բ���error��ֵ��response ���������ϵĲ�������ķ�������
//		if(pass2==null || pass1==null || user==null || email==null){//�����Ƿ�Ϊ��
		if(pass2.equals("") || pass1.equals("") || user.equals("") || email.equals("")){	
		    response.sendRedirect("/JSP/register.jsp?error=isnulls");
		    //������ڱ�������д������ �� ����̨
//			System.out.println("user:_"+user+"_email:_"+email+"_pass1:_"+pass1+"_pass2:_"+pass2);
			flag=false;
		}
		if(!pass1.equals(pass2) && flag){//����һ���Լ���
			response.sendRedirect("/JSP/register.jsp?error=dis");
			flag=false;
		}
		if(user.getBytes().length>20 && flag){//���Ƴ��ȹ�������
			response.sendRedirect("/JSP/register.jsp?error=long");
			flag=false;
		}
		if(Email(email) && flag){//���Ƴ��ȹ�������
			response.sendRedirect("/JSP/register.jsp?error=emailmis");
			flag=false;
		}
		if(isStrong(pass1) && flag){//���볤���Լ�ǿ�ȼ���
			response.sendRedirect("/JSP/register.jsp?error=passmis");
			flag=false;
		}
		
		if(flag){
		
			Connection cn = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			String url="jdbc:mysql://localhost:3306/student";
			String name="myth",pass="ad";
			
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection(url,name,pass);
				
				//�������ӵĴ��룬�ɹ�������
	//			ps = cn.prepareStatement("select * from Bloguser limit 0,1");
	//			rs = ps.executeQuery();
	//			while(rs.next()){
	//				System.out.println("user:"+rs.getString(1)+" pass��"+rs.getString(2)+" email��"+rs.getString(3));
	//			}
				
				ps = cn.prepareStatement("insert into Bloguser values (?,?,?)");
				ps.setString(1, user);
				ps.setString(2, pass1);
				ps.setString(3, email);
				
				if(ps.executeUpdate()==1){
					out.println("<h2>ע��ɹ�</h2>");
					out.println("<h3>�𾴵� "+user+" �û�����ӭ���ĵ���</h3>");
					out.println("<a href='/JSP/Blog.jsp'>���ص�¼ҳ��</a>");
				}else{
					out.println("<h2>ע��ʧ��</h2>");
					out.println("<p>δ��д�ʼ����ʼ���ʽ����ȷ</p>");
					out.println("<p>��ȷ��������ϸ�ʽ���ٶ�ȷ������</p>");
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(cn!=null) cn.close();
					
				}catch(Exception r){
					r.printStackTrace();
				}
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
	
	public boolean isStrong(String password){//����ǿ�ȵļ���
		if(password.length()<6 || password.length()>16) 
			return false;
		else return true;
	}
	
	public boolean Email(String email){//������֤�����ַ�淶��������ʽ
		return email==null || !email.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$*");
		// ^ ��ʾ�ַ��� ��ʼ �Ķ�λ�� 
		// $ ��ʾ�ַ��� ���� �Ķ�λ��
		// * + ? �����޶���(�൱�ں�׺���η�)����ʾƥ��ǰ����ӱ��ʽ �������
		// () ���һ���ӱ��ʽ�Ŀ�ʼ�ͽ��� 
		// [] ���һ�������ű��ʽ�Ŀ�ʼ�ͽ���
		/**�������յĽ���ǣ�
		 * 
		 * ��ʼ ^
		 * 
		 *   �ַ�������ĸ�����ַ���ʼ��Ȼ����Խ� ���(һ�� . �����������ĸ�����ַ�)����ʽ���ַ�
		 *   ����һ�� @ 
		 *   �Ӷ��(һ�� . �����������ĸ�����ַ�)����ʽ���ַ�
		 *   
		 * ���� $ ���ܶ����
		 * */
	}

}
