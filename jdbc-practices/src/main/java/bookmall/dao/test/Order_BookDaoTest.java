package bookmall.dao.test;

import java.util.List;

import bookmall.dao.Order_BookDao;
import bookmall.vo.Order_BookVo;


public class Order_BookDaoTest {

	public static void main(String[] args) {
		//findAllTest();
		//insertTest();
		//deleteTest();
		//updateTest();

	}
	
	private static void findAllTest() {
		List<Order_BookVo> list = new Order_BookDao().findAll();
		for(Order_BookVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {

		Order_BookDao dao = new Order_BookDao();
		
		Order_BookVo vo1 =  new Order_BookVo();
		vo1.setAmount(1);
		vo1.setPrice(30000);
		vo1.setOrderNo(1);
		vo1.setBookNo(1);
		dao.insert(vo1); 
		
		Order_BookVo vo2 =  new Order_BookVo();
		vo2.setAmount(1);
		vo2.setPrice(20000);
		vo2.setOrderNo(1);
		vo2.setBookNo(2);
		dao.insert(vo2); 
		
		findAllTest();
		
	}
	
	private static void deleteTest() {
		// delete(주문번호, 책번호) : 두 변수를 동시에 입력해야 정확하게 삭제 가능
		Order_BookDao dao = new Order_BookDao();
		dao.delete(1, 1);
		dao.delete(2, 1);
		
		findAllTest();
	}

}
