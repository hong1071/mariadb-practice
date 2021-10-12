package bookmall.main;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.dao.Order_BookDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderVo;
import bookmall.vo.Order_BookVo;

public class BookMall {

	public static void main(String[] args) {
		
		// 데이터 삽입 순서: category, book, member, cart, order, order_book 
		// 1. category insert	
			CategoryVo CateVo1 = new CategoryVo();
			CateVo1.setName("국내소설");
			
			CategoryVo CateVo2 = new CategoryVo();
			CateVo2.setName("예술/문화");
			
			CategoryVo CateVo3 = new CategoryVo();
			CateVo3.setName("수험서");
			
			new CategoryDao().insert(CateVo1);
			new CategoryDao().insert(CateVo2);
			new CategoryDao().insert(CateVo3);
			
			new CategoryDao().findAll();
		
			
		//2. book insert
			
			BookVo Bvo1 = new BookVo();
			Bvo1.setTitle("칼의 노래");
			Bvo1.setPrice(20000);
			Bvo1.setCategoryNo(1);
			new BookDao().insert(Bvo1); 	
			
			BookVo Bvo2 = new BookVo();
			Bvo2.setTitle("프랑스 문화사");
			Bvo2.setPrice(34000);
			Bvo2.setCategoryNo(2);
			new BookDao().insert(Bvo2); 
			
			BookVo Bvo3 = new BookVo();
			Bvo3.setTitle("ncs 부산교통공사 모의고사 4회");
			Bvo3.setPrice(13000);
			Bvo3.setCategoryNo(3);
			new BookDao().insert(Bvo3); 
			
			new BookDao().findAll();
			
			
		//3. member insert
			MemberVo Mvo1 = new MemberVo();
			Mvo1.setName("홍길동");
			Mvo1.setEmail("hong1071@nate.com");
			Mvo1.setPassword("123456");
			Mvo1.setPhoneNo("01086521071");
			new MemberDao().insert(Mvo1); 
			
			MemberVo Mvo2 = new MemberVo();
			Mvo2.setName("전우치");
			Mvo2.setEmail("jeonWC@naver.com");
			Mvo2.setPassword("123456");
			Mvo2.setPhoneNo("01055674213");
			new MemberDao().insert(Mvo2); 
			
			new MemberDao().findAll();
			
			
		//4. cart insert	
			CartVo cartVo1 =  new CartVo();
			cartVo1.setBookNo(1);
			cartVo1.setMemberNo(1);
			cartVo1.setAmount(1);
			new CartDao().insert(cartVo1); 
			
			CartVo cartVo2 =  new CartVo();
			cartVo2.setBookNo(2);
			cartVo2.setMemberNo(2);
			cartVo2.setAmount(2);
			new CartDao().insert(cartVo2); 
			
			CartVo cartVo3 =  new CartVo();
			cartVo3.setBookNo(1);
			cartVo3.setMemberNo(2);
			cartVo3.setAmount(3);
			new CartDao().insert(cartVo3); 
			
			new CartDao().findAll();
			
			
		//5. order insert	
			OrderVo orderVo = new OrderVo();
			orderVo.setPayment(30000);
			orderVo.setDestination("부산시 수영구 민락동");
			orderVo.setMemberNo(1);
			orderVo.setOrderNo(1);
			new OrderDao().insert(orderVo); 
			
			new OrderDao().findAll();
			
			
		//6. order_book insert		
			Order_BookVo OBVo1 =  new Order_BookVo();
			OBVo1.setAmount(1);
			OBVo1.setPrice(30000);
			OBVo1.setOrderNo(1);
			OBVo1.setBookNo(1);
			new Order_BookDao().insert(OBVo1); 
			
			Order_BookVo OBVo2 =  new Order_BookVo();
			OBVo2.setAmount(1);
			OBVo2.setPrice(20000);
			OBVo2.setOrderNo(1);
			OBVo2.setBookNo(2);
			new Order_BookDao().insert(OBVo2); 
			
			new Order_BookDao().findAll();
			
			
		// 데이터 업데이트
		// 1. category update		
			CategoryVo CateVo4 = new CategoryVo();
			CateVo4.setName("해외소설");
			CateVo4.setNo(1);
			new CategoryDao().update(CateVo4);
			
			new CategoryDao().findAll();
			
			
		// 2. member update	
			MemberVo Mvo3 = new MemberVo();
			Mvo3.setPhoneNo("01086521076");
			Mvo3.setNo(1);
			new MemberDao().update(Mvo3);
			
			new MemberDao().findAll();
			

		// 데이터 삭제 순서: order_book, order, cart, member, book
		// 1. order_book delete
			new Order_BookDao().delete(1, 1);
			new Order_BookDao().delete(2, 1);
			
			new Order_BookDao().findAll();
			
		// 2. order delete
			new OrderDao().delete(1);
			
			new OrderDao().findAll();
		
			
		// 3. cart delete
			new CartDao().delete(1);
			
			new CartDao().findAll();
			
			
		// 4. member delete
			new MemberDao().delete(1);
			
			new MemberDao().findAll();
			
			
		// 5. book delete
			new BookDao().delete(1);
			
			new BookDao().findAll();

	}
}
