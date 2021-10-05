package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			//1. JDBC Driver을 로딩한다.
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결한다.
			String url = "jdbc:mysql://127.0.0.1:3306/webdb"; // 드라이버종류, ip주소:포트번호, 데이터베이스 이름
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("연결 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
