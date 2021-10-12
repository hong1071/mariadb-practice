package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderVo;

public class OrderDao {
	
	public static boolean insert(OrderVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getconnection();
			
			// SQL 구문을 준비한다.
			String sql = "insert into `order` values (null, ?, ?, ?, ?)";		//? 자리에 바인딩을 한다.
			pstmt = conn.prepareStatement(sql);
			
			// 바인딩(Binding)을 한다.
			pstmt.setInt(1, vo.getPayment());
			pstmt.setString(2, vo.getDestination());
			pstmt.setInt(3, vo.getMemberNo());
			pstmt.setInt(4, vo.getOrderNo());
			
			// SQL 구문을 실행한다.
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
	
	public List<OrderVo> findAll(){
		List<OrderVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getconnection();
			
			// SQL 구문을 준비한다.
			String sql = "select no, payment, destination, member_no, order_no from `order`";
			pstmt = conn.prepareStatement(sql);
			
			// SQL 구문을 실행한다.
			rs = pstmt.executeQuery();
			while(rs.next()){
				int no = rs.getInt(1);
				int payment = rs.getInt(2);
				String destination= rs.getString(3);
				int memberNo = rs.getInt(4);
				int orderNo = rs.getInt(5);
				
				OrderVo vo = new OrderVo();
				vo.setNo(no);
				vo.setPayment(payment);
				vo.setDestination(destination);
				vo.setMemberNo(memberNo);
				vo.setOrderNo(orderNo);
				
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
			
			// Statement를 생성한다.
			String sql = "delete from `order` where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// binding
			pstmt.setLong(1, no);
			
			// SQL 구문을 실행한다.
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
			// JDBC Driver을 로딩한다.
			Class.forName("org.mariadb.jdbc.Driver");
			
			// SQL과 연결한다.
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
