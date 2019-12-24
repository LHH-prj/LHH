package Login.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Login.Member;

public class DatabaseService implements DatabaseServiceInter{
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String url = "jdbc:oracle:thin:@192.168.100.10:1521:xe";
	final String INSERTSQL = "insert into member(id,pw,name,gender,age,cash)" + " values(?,?,?,?,?,?)";
	final String LOGINSQL = "SELECT COUNT(id) "+"FROM member "+"WHERE id = ? AND pw = ?";
	final String CENSORSHIP = "SELECT COUNT(id) "+"FROM member "+"WHERE id = ?";
	private Connection dbConn;
	
	static { 
		try { Class.forName(DRIVER);
			System.out.println("드라이버 로딩 성공");
		} 
		catch(Exception e) { e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} 
	} 
	
	
	public boolean open()  {
		// TODO Auto-generated method stub
		System.out.println("db open() 실행");
		try {
			
			String id= "dlengud";
			String pw= "dlengud";
			dbConn = DriverManager.getConnection(url,id,pw);
			System.out.println("오라클 연결 성공");
		} catch(SQLException e) { 
			e.printStackTrace(); 
			System.out.println("오라클 연결 실패");
			return false; 
		} 
		
		return true; 
	}

	public void Insert(Member member) {
		// TODO Auto-generated method stub
		
		System.out.println("db Insert() 실행");
		
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(INSERTSQL);
			prep.setString(1, member.getId());
			prep.setString(2, member.getPw());
			prep.setString(3, member.getName());
			prep.setString(4, member.isGender());
			prep.setString(5, member.getAge());
			prep.setInt(6, member.getCash());
			
			System.out.println("등록완료");
			prep.executeUpdate();
			System.out.println("prep.executeUpdate();");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("인설트 캐치로 넘어감");
		}
	}
	
	public boolean Select(String id) {
		// TODO Auto-generated method stub
		boolean result = true;
		
		try {
			PreparedStatement prep = dbConn.prepareStatement(CENSORSHIP);
			prep.setString(1, id);
			
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)==0)
					result = false;
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean Select(String id, String pw) {
		// TODO Auto-generated method stub
		boolean result = true;
		
		try {
			PreparedStatement prep = dbConn.prepareStatement(LOGINSQL);
			prep.setString(1, id);
			prep.setString(2, pw);
			
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)==0)
					result = false;
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
}
