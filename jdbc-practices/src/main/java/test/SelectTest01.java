package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest01 {
	
	public static void main(String[] args) {
		search("pat");
	}

	public static void search(String keyword) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver을 로딩한다.
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. SQL과 연결한다.
			String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8"; // 드라이버종류, ip주소:포트번호, 데이터베이스 이름
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			//3. Statement를 생성한다.
			stmt = conn.createStatement();
			
			//4. SQL 구문을 실행한다.
			String sql = "select emp_no, first_name " + 
					 	 "from employees " + 
						 "where first_name like '%" + keyword + "%'";
			rs = stmt.executeQuery(sql); 				//select는 executeQuery함수 사용
			while(rs.next()) {
				Long empNo = rs.getLong(1);
				String firstName = rs.getString(2);
				System.out.println(empNo + ":" + firstName);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// clean up
			try {
				if(rs != null) {
					rs.close();
				}
				
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
		

	}

}
