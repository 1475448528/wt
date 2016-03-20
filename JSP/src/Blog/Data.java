package Blog;
/**
 * 显示所有各个用户发过的微博  这需要数据库的支持！
 * 界面急需美化，CSS急需入门！！！
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

		doPost(request, response);
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the GET method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
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
		out.println("<link href='css/font.css'rel='stylesheet' type='text/css' />");
		out.println("  <BODY>");
		
		out.println("<img src='/JSP/images/Blog2.jpg' style='height: 45px; width: 130px'/>");
		out.print("<a href='/JSP/Suecces.jsp'>首页</a><a href='Data'>刷新</a>");
		/** 这里的用户名还没有解决，感觉是使用Session来完成功能*/
//		String username = "Myth";
		String msg = request.getParameter("msg");
		SqlHelper h = new SqlHelper();
		ResultSet rs = null;
		/**使用Session来传递用户名*/
		//得到和request相关联的session，如果没有就创建一个
		HttpSession pu = request.getSession(true);
		String name = (String)pu.getAttribute("name");//获取登陆的用户名
		//分页的属性
		int pageSize = 4;//一页显示几条记录
		int pageNow = 1;//希望显示第几页
		int rowCount = 0;//一共有几条记录(查表)
		int pageCount = 0;//一共有几页
		
				/**为了给四个参数初始化 做的查询操作*/
				rs = h.SelectAll("select count(*) from data where user='"+name+"'");
				try {
					if(rs.next()){
						rowCount = rs.getInt(1);
						System.out.println("总共有："+rowCount+"行记录");
					}
					if(rowCount%pageSize == 0){//计算有多少页
						pageCount = rowCount/pageSize;
					}else{
						pageCount = rowCount/pageSize+1;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
		System.out.println("msg:"+msg);
		boolean y=false;
		if(msg!=null)
			if(msg.length()>=1){
			   y= h.updSQL("insert into data values('"+name+"','"+msg+"',curdate(),curtime())");
			}else{
				response.sendRedirect("/JSP/Suecces.jsp?error=nulls");
			}
//		if(y) System.out.println("成功插入");
//		else System.out.println("插入记录失败");
		
		String [] info = new String[30];
		String [] date = new String[30];
		String [] time = new String[30];
/**接收参数 来控制显示第几个页面*/
		String page = request.getParameter("pagenow");
		if(page!=null){
			pageNow =Integer.parseInt(page);
		}
		
		//分页的查询记录
		if((rowCount-(pageNow-1)*pageSize)< pageSize)pageSize=(rowCount-(pageNow-1)*pageSize);
		rs = h.SelectAll("select * from data where user='"+name+"' order by Pushdate desc,time desc limit "+(2*pageNow-2)+","+(pageSize)+"");
		int i=0;
		try {
			int u=0;
			while(rs.next()){
				u++;
				info[i] = rs.getString(2);
				date[i] = rs.getString(3);
				time[i] = rs.getString(4);
				i++;
				
			}//if(!rs.next()) 如果这样写了 放while前面就会少一条记录，后面就会数组越界，因为每一次next都是相当于指针的移动
			if(u==0) out.println("<br>&nbsp;&nbsp;<strong>该用户尚未发布任何动态</strong>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
			if(rs!=null) rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		
		for(int k=0;k<info.length ;k++){
			
			String msgs = info[k];
			String dates = date[k];
			String times = time[k];
			
			if(msgs!=null){
		        out.println("<div style='border:1px solid green;'><strong>"+name+":</strong><br><p >&nbsp;&nbsp;&nbsp;&nbsp;"+msgs+"</p>");
//		        System.out.println((dates.substring(8, 10)).equals(new java.util.Date().getDate()+""));
		        if((dates.substring(8, 10)).equals(new java.util.Date().getDate()+""))
		            out.println("<span style='margin:0px 0px 0px 1000px'>今天: &nbsp "+times+"</span></div><br />");
		        else{
		        	out.println("<span style='margin:0px 0px 0px 1000px'>"+dates+": &nbsp "+times+"</span></div><br />");
		        }
			}
			
		}
		
		out.println("<br>");
		out.println("<a href='Data?pagenow=1'>第一页</a>");
		for(int k=1;k<pageCount;k++){
			out.println("<a href='Data?pagenow="+(k+1)+"'>["+(k+1)+"]</a>");
		}
		if(pageNow<pageCount){
			out.println("<a href='Data?pagenow="+(pageNow+1)+"'>下一页</a>");
		}else{
			out.println("<a href='Data?pagenow="+pageNow+"'>最后一页</a>");
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
