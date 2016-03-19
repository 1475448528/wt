/**
 * ��ײ��ֱ�Ӳ������ݿ����
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

/**��ѯȫ���Ĳ���*/
	public ResultSet SelectAll(String sql){
//		System.out.println(sql);�����ǲ�ѯ����studentƴ���ˣ���Ҫ��ô�鷳�ĵ��ԣ��������﷨����
		try {
			//1 ��������
			Class.forName(Driver);
			//2 �õ�����
			cn = DriverManager.getConnection(URL);
			ps=cn.prepareStatement(sql);
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//���ܹر�
//			this.closeAll();
		}
//		System.out.println("���صģ�"+rs);
		return rs;
	}
/**����ɾ�� ����һ��*/
	public boolean updSQL(String sql){
		//Ϊ�˷�ֹע��ʽ���������Դ����β����飨�����βθ����ǲ�֪���ģ���Ҫд����ʺźͶ��Set������
		
		boolean flag = true;
		try{
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL);
			
			ps=cn.prepareStatement(sql);
			int i=ps.executeUpdate();
			if(i==1)System.out.print("��ɾ�Ĳ� �ɹ�-->");
			else {
				System.out.print("��ɾ�Ĳ� ʧ��-->");
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
	//�ر����ݿ���Դ
	public void closeAll(){
		//�ر���Դ ����ȹر�
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(cn!=null) cn.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		System.out.println("����-�ر���Դ");
	}
}
