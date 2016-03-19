/**
 * 最底层的直接操作数据库的类
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {

	PreparedStatement ps = null;
	Connection cn = null;
	ResultSet rs = null;
	
	String Driver = "com.mysql.jdbc.Driver";
	String URL="jdbc:mysql://localhost:3306/student?user=myth&password=ad&userUnicode=true&characterEncoding=UTF8";

/**查询全部的操作*/
	public ResultSet SelectAll(String sql){
//		System.out.println(sql);仅仅是查询语句的student拼错了，就要这么麻烦的调试，都不报语法错误！
		try {
			//1 加载驱动
			Class.forName(Driver);
			//2 得到连接
			cn = DriverManager.getConnection(URL);
			ps=cn.prepareStatement(sql);
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//不能关闭
//			this.closeAll();
		}
//		System.out.println("返回的："+rs);
		return rs;
	}
/**把增删改 合在一起*/
	public boolean updSQL(String sql){
		//为了防止注入式攻击，可以传入形参数组（但是形参个数是不知道的，就要写多个问号和多个Set函数）
		
		boolean flag = true;
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			
			ps=cn.prepareStatement(sql);
			int i=ps.executeUpdate();
			if(i==1)System.out.print("增删改查 成功-->");
			else {
				System.out.print("增删改查 失败-->");
				flag=false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}finally {
			this.closeAll();
		}
		return flag;
	}
	//关闭数据库资源
	public void closeAll(){
		//关闭资源 后打开先关闭
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(cn!=null) cn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		System.out.println("正常-关闭资源");
	}
}
