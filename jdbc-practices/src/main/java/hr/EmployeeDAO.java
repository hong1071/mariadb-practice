package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	
	public List<EmployeeVo> findByName(String name) {
		List<EmployeeVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver을 로딩한다.
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. SQL과 연결한다.
			String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8"; // 드라이버종류, ip주소:포트번호, 데이터베이스 이름
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			//3. Sql 준비
			String sql = "select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d') "
						+ "from employees "
						+ "where first_name like ? "
						+ "		or last_name like ? ";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + name + "%");
			
			//5. SQL 구문을 실행한다.
			rs = pstmt.executeQuery(); 				//select는 executeQuery함수 사용
			while(rs.next()) {
				Long empNo = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(empNo);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);
				
				result.add(vo);
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
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("오류 발생: " + e);
			}
		}
		return result;
	}

	public List<EmployeeVo> findBySalary(int minSalary, int maxSalary) {
		List<EmployeeVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver을 로딩한다.
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. SQL과 연결한다.
			String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8"; // 드라이버종류, ip주소:포트번호, 데이터베이스 이름
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			//3. Sql 준비
			String sql = "select t1.emp_no, t2.first_name, t2.last_name, t1.salary "
					+ "from salaries t1, employees t2 "
					+ "where t1.emp_no = t2.emp_no "
					+ "	and to_date = '9999-01-01' "
					+ "	and salary between ? and ? ";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setInt(1, minSalary);
			pstmt.setInt(2, maxSalary);
			
			//5. SQL 구문을 실행한다.
			rs = pstmt.executeQuery(); 				//select는 executeQuery함수 사용
			while(rs.next()) {
				Long No = rs.getLong(1);
				String firstName= rs.getString(2);
				String LastName = rs.getString(3);
				int Salary = rs.getInt(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(No);
				vo.setSalary(Salary);
				vo.setFirstName(firstName);
				vo.setLastName(LastName);
				
				result.add(vo);
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
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("오류 발생: " + e);
			}
		}
		return result;
	}

}
