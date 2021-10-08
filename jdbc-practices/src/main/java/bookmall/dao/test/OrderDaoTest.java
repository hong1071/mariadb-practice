package bookmall.dao.test;

import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		findAllTest();
		insertTest();
		deleteTest();

	}
	
	private static void findAllTest() {
		List<OrderVo> list = new OrderDao().findAll();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();
		
		vo = new OrderVo();
		vo.setPayment(30000);
		vo.setDestination("부산시 수영구 민락동");
		vo.setMemberNo(1);
		vo.setOrderNo(1);
		dao.insert(vo); 
		
	}
	
	private static void deleteTest() {
		OrderDao dao = new OrderDao();
		dao.delete(0);
	}

}
