package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest01 {

	public static void main(String[] args) {
		insert("영업");
		insert("개발");
		insert("기획");
	}
	
	private static boolean insert(String name) {
		
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//1. JDBC Driver을 로딩한다.
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. SQL과 연결한다.
			String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8"; // 드라이버종류, ip주소:포트번호, 데이터베이스 이름
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			//3. Statement를 생성한다.
			stmt = conn.createStatement();
			
			//4. SQL 구문을 실행한다.
			String sql = "insert into dept values(null, '" + name + "')";
			int count = stmt.executeUpdate(sql);
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// clean up
			try {
				if(stmt != null) {
					stmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
