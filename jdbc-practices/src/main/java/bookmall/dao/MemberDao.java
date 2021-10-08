package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;


public class MemberDao {
	
	public static boolean insert(MemberVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 구문을 준비한다.
			String sql = "insert into member values(null, ?, ?, ?, ?)";		//? 자리에 바인딩을 한다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(Binding)을 한다.
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getPhoneNo());
			
			//5. SQL 구문을 실행한다.
			pstmt.executeUpdate();
			
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
	
	public List<MemberVo> findAll(){
		List<MemberVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 구문을 준비한다.
			String sql = "select no, name, email, password, phone_no from member";		//? 자리에 바인딩을 한다.
			pstmt = conn.prepareStatement(sql);
			
			//4. SQL 구문을 실행한다.
			rs = pstmt.executeQuery();
			while(rs.next()){
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				String phoneNo = rs.getString(5);
				
				MemberVo vo = new MemberVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setPassword(password);
				vo.setPhoneNo(phoneNo);
				
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
	
	public static Boolean delete(int no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getconnection();
			
			//3. Statement를 생성한다.
			String sql = "delete from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setInt(1, no);
			
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
	
	public static Boolean update(MemberVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getconnection();

			//3. Statement를 생성한다.
			String sql = "update member " + 
					"set email = ifnull(?, email) " + 
					"	, password = ifnull(?, password) " + 
					"   , phone_no = ifnull(?, phone_no) " + 
					"where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			System.out.println(vo.getEmail());
			System.out.println(vo.getPassword());
			System.out.println(vo.getPhoneNo());
			System.out.println(vo.getNo());
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getPhoneNo());
			pstmt.setInt(4, vo.getNo());
			
			//5. SQL 구문을 실행한다.
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		}  catch (SQLException e) {
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
			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf8"; // 드라이버종류, ip주소:포트번호, 데이터베이스 이름
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			
			
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
