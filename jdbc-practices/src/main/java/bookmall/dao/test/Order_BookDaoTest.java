package bookmall.dao.test;

import java.util.List;

import bookmall.dao.Order_BookDao;
import bookmall.vo.Order_BookVo;


public class Order_BookDaoTest {

	public static void main(String[] args) {
		findAllTest();
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
		Order_BookVo vo = null;
		Order_BookDao dao = new Order_BookDao();
		
		vo = new Order_BookVo();
		vo.setAmount(1);
		vo.setPrice(30000);
		vo.setOrderNo(1);
		vo.setBookNo(1);
		dao.insert(vo); 
		
	}
	
	private static void deleteTest() {
		Order_BookDao dao = new Order_BookDao();
		dao.delete(0);
	}
	
	private static void updateTest() {
		Order_BookVo vo = null;
		Order_BookDao dao = new Order_BookDao();
		
		vo = new Order_BookVo();
		vo.setAmount(1);
		vo.setPrice(20000);
		vo.setBookNo(1);
		vo.setOrderNo(1);
		dao.equals(vo);
		
	}

}
