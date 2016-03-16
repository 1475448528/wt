package Blog;
/**
 * 提供注册服务的类，实现数据库的插入操作
 * 每次关闭服务都会弹出的警告
 *  appears to have started a thread named [Abandoned connection cleanup thread] but has failed to stop it. This is very likely to create a memory leak.
 * 似乎已经启动了一个名为[放弃连接清理线程]的线程，但未能阻止它。这很可能会造成内存泄漏。

 
 Tomcat7是吧？ 它增加了一个内存泄漏的检查，主要是判断web应用重载时，类加载器中所有对象的引用都释放干净。

一般出现提示可能的原因：
1.JDBC驱动注册
2.一些日志框架
3.在ThreadLocal中保存对象，但是并不去删除它
4.启动了线程，但没有停止它

解决方法可能就是对应的应用需要加一个 ServletContextListener ，然后在 contextDestroyed 方法里去把以上的事情做掉。比如如果你用到了jdbc，那么需要手动去注销jdbc。threadlocal的保存的对象把它们清空掉。等等等等。。。

要具体问题具体分析。当然也可以不做什么事情。其实也没有多大关系的。

这里的内存泄漏指的也是你在不重新启动tomcat的情况下,reload某一个应用，才会造成内存泄漏。所以你只要每次重启应用都是通过重启tomcat来的话不会造成任何影响。
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
		request.setCharacterEncoding("utf-8");//设置接受中文编码
		
		String user = request.getParameter("user");
		String email = request.getParameter("email");
		String pass1 = request.getParameter("password1");
		String pass2 = request.getParameter("password2");
		
		//String test = ""; 关于字符串初始值的测试
		//System.out.println("_"+test+"_");如果
		//System.out.println("user:_"+user+"_email:_"+email+"_pass1:_"+pass1+"_pass2:_"+pass2);

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Control</TITLE></HEAD>");
		out.println("  <BODY>");
		boolean flag;
		flag = true;
		
		/**以下的是对表单提交的数据进行处理 根据不同的情况来 重定向回去注册页面 */
		//因为有多个对同一个参数传值的response可能会回复，又因为整个servlet类所有代码都会执行，不能发送 两个 对一个参数的传值的response
		//所以就要用flag来限定只发送一个对参数error传值的response 所以用书上的参数数组的方法更好
//		if(pass2==null || pass1==null || user==null || email==null){//检验是否为空
		if(pass2.equals("") || pass1.equals("") || user.equals("") || email.equals("")){	
		    response.sendRedirect("/JSP/register.jsp?error=isnulls");
		    //输出我在表单里面填写的数据 到 控制台
//			System.out.println("user:_"+user+"_email:_"+email+"_pass1:_"+pass1+"_pass2:_"+pass2);
			flag=false;
		}
		if(!pass1.equals(pass2) && flag){//密码一致性检验
			response.sendRedirect("/JSP/register.jsp?error=dis");
			flag=false;
		}
		if(user.getBytes().length>20 && flag){//名称长度过长检验
			response.sendRedirect("/JSP/register.jsp?error=long");
			flag=false;
		}
		if(Email(email) && flag){//名称长度过长检验
			response.sendRedirect("/JSP/register.jsp?error=emailmis");
			flag=false;
		}
		if(isStrong(pass1) && flag){//密码长度以及强度检验
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
				
				//测试连接的代码，成功连接了
	//			ps = cn.prepareStatement("select * from Bloguser limit 0,1");
	//			rs = ps.executeQuery();
	//			while(rs.next()){
	//				System.out.println("user:"+rs.getString(1)+" pass："+rs.getString(2)+" email："+rs.getString(3));
	//			}
				
				ps = cn.prepareStatement("insert into Bloguser values (?,?,?)");
				ps.setString(1, user);
				ps.setString(2, pass1);
				ps.setString(3, email);
				
				if(ps.executeUpdate()==1){
					out.println("<h2>注册成功</h2>");
					out.println("<h3>尊敬的 "+user+" 用户，欢迎您的到来</h3>");
					out.println("<a href='/JSP/Blog.jsp'>返回登录页面</a>");
				}else{
					out.println("<h2>注册失败</h2>");
					out.println("<p>未填写邮件或邮件格式不正确</p>");
					out.println("<p>请确认密码符合格式并再度确认密码</p>");
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
	
	public boolean isStrong(String password){//密码强度的检验
		if(password.length()<6 || password.length()>16) 
			return false;
		else return true;
	}
	
	public boolean Email(String email){//用来验证邮箱地址规范的正则表达式
		return email==null || !email.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$*");
		// ^ 表示字符串 开始 的定位符 
		// $ 表示字符串 结束 的定位符
		// * + ? 都是限定符(相当于后缀修饰符)，表示匹配前面的子表达式 零个或多个
		// () 标记一个子表达式的开始和结束 
		// [] 标记一个中括号表达式的开始和结束
		/**所以最终的结果是：
		 * 
		 * 开始 ^
		 * 
		 *   字符串以字母数字字符开始，然后可以接 多个(一个 . 加上任意个字母数字字符)这样式的字符
		 *   接上一个 @ 
		 *   接多个(一个 . 加上任意个字母数字字符)这样式的字符
		 *   
		 * 结束 $ 可能多个？
		 * */
	}

}
