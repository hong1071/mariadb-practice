package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		//findAllTest();
		//insertTest();
		//deleteTest();

	}
	
	private static void findAllTest() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {

		CartDao dao = new CartDao();
		
		CartVo vo1 =  new CartVo();
		vo1.setBookNo(1);
		vo1.setMemberNo(1);
		vo1.setAmount(1);
		dao.insert(vo1); 
		
		CartVo vo2 =  new CartVo();
		vo2.setBookNo(2);
		vo2.setMemberNo(2);
		vo2.setAmount(2);
		dao.insert(vo2); 
		
		CartVo vo3 =  new CartVo();
		vo3.setBookNo(1);
		vo3.setMemberNo(2);
		vo3.setAmount(3);
		dao.insert(vo3); 
		
		findAllTest();
		
	}
	
	private static void deleteTest() {
		// 회원번호로 삭제
		CartDao dao = new CartDao();
		dao.delete(1);
		
		findAllTest();
	}

}
