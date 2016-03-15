/**
�����������ϴ��ļ� Ĭ�Ͻ��ϴ����ļ�������E:TestĿ¼��  
 
     ���⣺HTMLҳ�����������ַ���ʱ���ͱ���MyHtml.html��������UTF-8����ͻ����룬chrome���ᣬ����gbkʱ�෴
     ���������UTF-16������������
 *   
 *   
 *   ���⣺��������ֻ����룬��������û�д�����Ϊ�����ֽ�����ȡ���ļ�???
 *   �²⣺1.�²��Ƿ���Ϊ�������ֻ�ܴ���Ӣ���������ܴ��������� 
 *        1-1���²���ȷ��ʹ��Ӣ���������������ܹ���ȷ�����������ģ������������� ���Ǳ�������⣿��ô���ã�
 *         2.��ô�ƣ�
 *   �����
 *   
 *   ���⣺�����ϴ�һ��1��G���ļ��Ļ����ͻ��׳��̴߳���???
 *   �²⣺���� Tomcat ���Web���� �������WebӦ��ʱ �ܴ������ڴ�ռ� ����Web�����滮�ģ��ļ�̫��ͻ��ڴ����������
 *         �ǰٶ��ƹܼ�ʹ�õ�ʲô������
 *         �ϴ����ļ��ļ�����Ӧ���Ƿ��������һ����һ���ִ���.......
 *   �����
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
//һ���򵥵��ڲ���
	class Position{
		int begin;
		int end;
		Position(int begin,int end){
			this.begin = begin;
			this.end = end;
		}
		
	}
	
	private Position getFilePosition(HttpServletRequest request,String textBody) throws IOException{
//��ȡ�ļ��߽���Ϣ
		String contentType = request.getContentType();
		String boundaryText = contentType.substring(contentType.lastIndexOf("=")+1,contentType.length());
//ȡ��ʵ���ϴ��ļ�����ʼ�����λ��
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
//���п���Ҫ�׳��쳣
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













