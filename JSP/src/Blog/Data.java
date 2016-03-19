package Blog;
/**
 * ��ʾ���и����û�������΢��  ����Ҫ���ݿ��֧�֣�
 * ���漱��������CSS�������ţ�����
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.SqlHelper;

public class Data extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Data() {
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
		request.setCharacterEncoding("utf-8");
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Blog Data</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<img src='/JSP/images/Blog2.jpg' style='height: 45px; width: 130px'/>");
		/** ������û�����û�н�����о���ʹ��Session����ɹ���*/
//		String username = "Myth";
		String msg = request.getParameter("msg");
		SqlHelper h = new SqlHelper();
		ResultSet rs = null;
		/**ʹ��Session�������û���*/
		//�õ���request�������session�����û�оʹ���һ��
		HttpSession pu = request.getSession(true);
		String name = (String)pu.getAttribute("name");//��ȡ��½���û���
		
		boolean y=false;
		if(msg!=null){
		   y= h.updSQL("insert into data values('"+name+"','"+msg+"',curdate(),curtime())");
		}else{
			response.sendRedirect("/JSP/Suecces.jsp");
		}
		if(y) System.out.println("�ɹ�����");
		else System.out.println("�����¼ʧ��");
		
		String [] info = new String[30];
		String [] date = new String[30];
		String [] time = new String[30];
		
		
		rs = h.SelectAll("select * from data where user='"+name+"' order by Pushdate desc,time desc");
		int i=0;
		try {
			int u=0;
			while(rs.next()){
				u++;
				info[i] = rs.getString(2);
				date[i] = rs.getString(3);
				time[i] = rs.getString(4);
				i++;
				
			}//if(!rs.next()) �������д�� ��whileǰ��ͻ���һ����¼������ͻ�����Խ�磬��Ϊÿһ��next�����൱��ָ����ƶ�
			if(u==0) out.println("<br>&nbsp;&nbsp;<strong>���û���δ�����κζ�̬</strong>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.println("");
		for(int k=0;k<info.length ;k++){
			
			String msgs = info[k];
			String dates = date[k];
			String times = time[k];
			
			if(msgs!=null){
		        out.println("<div style='border:1px solid green;'><h4>"+name+":</h4><br><p >&nbsp;&nbsp;&nbsp;&nbsp;"+msgs+"</p>");
//		        System.out.println((dates.substring(8, 10)).equals(new java.util.Date().getDate()+""));
		        if((dates.substring(8, 10)).equals(new java.util.Date().getDate()+""))
		            out.println("<span style='margin:0px 0px 0px 1000px'>����: &nbsp "+times+"</span></div><br />");
		        else{
		        	out.println("<span style='margin:0px 0px 0px 1000px'>"+dates+": &nbsp "+times+"</span></div><br />");
		        }
			}
			
		}
		
		out.println("<br>");
		out.println();
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

	public void timesort(String [] date){
		
	}
}
