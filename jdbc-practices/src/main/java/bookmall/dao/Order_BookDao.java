package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.Order_BookVo;


public class Order_BookDao {
	
	public static boolean insert(Order_BookVo vo) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 구문을 준비한다.
			String sql = "insert into order_book values(?, ?, ?, ?)";		//? 자리에 바인딩을 한다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(Binding)을 한다.
			pstmt.setInt(1, vo.getAmount());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getBookNo());
			pstmt.setInt(4, vo.getOrderNo());
			
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
	
	public List<Order_BookVo> findAll(){
		List<Order_BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 구문을 준비한다.
			String sql = "select amount, price, order_no, book_no from order_book";		//? 자리에 바인딩을 한다.
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(Binding)을 한다.
			
			//5. SQL 구문을 실행한다.
			rs = pstmt.executeQuery();
			while(rs.next()){
				int amount = rs.getInt(1);
				int price = rs.getInt(2);
				int orderNo = rs.getInt(3);
				int bookBo = rs.getInt(4);
				
				Order_BookVo vo = new Order_BookVo();
				vo.setAmount(amount);
				vo.setPrice(price);
				vo.setOrderNo(orderNo);
				vo.setBookNo(bookBo);
				
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
	
	public static Boolean delete(int orderNo, int bookNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getconnection();
			
			//3. Statement를 생성한다.
			String sql = "delete from order_book where order_no = ? and book_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, bookNo);
			
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
