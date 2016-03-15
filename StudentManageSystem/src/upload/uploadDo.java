/**
范例：处理上传文件 默认将上传的文件保存在E:Test目录下  
 
     问题：HTML页面上有中文字符，时。就比如MyHtml.html里面设置UTF-8火狐就会乱码，chrome不会，设置gbk时相反
     解决：设置UTF-16就两个都好了
 *   
 *   
 *   问题：保存的名字会乱码，但是内容没有错误，因为是以字节流读取的文件???
 *   猜测：1.猜测是否因为这个代码只能处理英文名而不能处理中文名 
 *        1-1：猜测正确，使用英文名和数字名是能够正确处理并且命名的，中文名会乱码 还是编码的问题？怎么设置？
 *         2.怎么破？
 *   解决：
 *   
 *   问题：还有上传一个1个G的文件的话，就会抛出线程错误???
 *   猜测：关于 Tomcat 这个Web容器 处理这个Web应用时 能创建的内存空间 是由Web容器规划的，文件太大就会内存溢出而报错
 *         那百度云管家使用的什么技术？
 *         上传大文件的技术，应该是分批处理吧一部分一部分处理.......
 *   解决：
 */
package upload;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class uploadDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public uploadDo() {
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
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>uploadDo</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("Success upload");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
		
		byte[] body = readBody(request);
		String textBody = new String(body,"ISO-8859-1");
		String filename = getFilename(textBody);
		
		Position  p = getFilePosition(request,textBody);
		writeTo(filename,body,p);
//		System.out.println("Success upload");
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
//一个简单的内部类
	class Position{
		int begin;
		int end;
		Position(int begin,int end){
			this.begin = begin;
			this.end = end;
		}
		
	}
	
	private Position getFilePosition(HttpServletRequest request,String textBody) throws IOException{
//获取文件边界信息
		String contentType = request.getContentType();
		String boundaryText = contentType.substring(contentType.lastIndexOf("=")+1,contentType.length());
//取得实际上传文件的起始与结束位置
		int pos = textBody.indexOf("filename=\"");
		pos = textBody.indexOf("\n",pos)+1;
		pos = textBody.indexOf("\n",pos)+1;
		pos = textBody.indexOf("\n",pos)+1;
		int boundaryLoc = textBody.indexOf(boundaryText,pos)-4;
		int begin = (((String) textBody.subSequence(0, pos)).getBytes("ISO-8859-1")).length;
		int end = (((String) textBody.subSequence(0, boundaryLoc)).getBytes("ISO-8859-1")).length;
		return new Position(begin, end);
		
	}
	private void writeTo(String filename, byte[] body, Position p) throws FileNotFoundException,IOException{
		// TODO Auto-generated method stub
//很有可能要抛出异常
		FileOutputStream fileOutputStream = new FileOutputStream("E:/Test/"+filename);
		fileOutputStream.write(body,p.begin,(p.end-p.begin));
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	private String getFilename(String reqBody) {
		// TODO Auto-generated method stub
		String filename = reqBody.substring(reqBody.indexOf("filename=\"")+10);
		filename = filename.substring(0,filename.indexOf("\n"));
		filename = filename.substring(filename.lastIndexOf("\\")+1,filename.indexOf("\""));
		return filename;
	}

	private byte[] readBody(HttpServletRequest request) throws IOException {
		// TODO Auto-generated method stub
		int formDataLength = request.getContentLength();
		DataInputStream dataStream = new DataInputStream(request.getInputStream());
		byte body[] = new byte[formDataLength];
		int totalBytes = 0;
		while(totalBytes < formDataLength){
			int bytes = dataStream.read(body,totalBytes,formDataLength);
			totalBytes += bytes;
		}
		return body;
	}

}













