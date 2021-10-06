package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.AuthorVo;
import hr.EmployeeVo;

public class AuthorDao {
	
	public List<AuthorVo> findAll(){
		List<AuthorVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 구문을 준비한다.
			String sql = "select no, name from author";		//? 자리에 바인딩을 한다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(Binding)을 한다.
			
			//5. SQL 구문을 실행한다.
			rs = pstmt.executeQuery();
			while(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				
				result.add(vo);
			}
			
		} catch(SQLException e) {
			
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
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean insert(AuthorVo vo) {
			
			boolean result = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getconnection();
				
				//3. SQL 구문을 준비한다.
				String sql = "insert into author values(null, ?)";		//? 자리에 바인딩을 한다.
				pstmt = conn.prepareStatement(sql);
				
				//4. 바인딩(Binding)을 한다.
				pstmt.setString(1, vo.getName());
				
				//5. SQL 구문을 실행한다.
				int count = pstmt.executeUpdate();
				result = count == 1;
				
			} catch (SQLException e) {
				System.out.println("error: " + e);
			} finally {
				// clean up
				try {
					if(pstmt != null) {
						pstmt.close();
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

	private static Connection getconnection() throws SQLException{
		Connection conn  = null;
		
		try {
			//1. JDBC Driver을 로딩한다.
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. SQL과 연결한다.
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8"; // 드라이버종류, ip주소:포트번호, 데이터베이스 이름
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
