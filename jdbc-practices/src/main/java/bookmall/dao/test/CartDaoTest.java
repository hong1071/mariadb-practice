package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		findAllTest();
		insertTest();
		deleteTest();
		updateTest();

	}
	
	private static void findAllTest() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		CartVo vo = null;
		CartDao dao = new CartDao();
		
		vo = new CartVo();
		vo.setBookNo(1);
		vo.setMemberNo(2);
		vo.setAmount(1);
		dao.insert(vo); 
		
	}
	
	private static void deleteTest() {
		CartDao dao = new CartDao();
		dao.delete(1);
	}
	
	private static void updateTest() {
		CartVo vo = null;
		CartDao dao = new CartDao();
		
		vo = new CartVo();
		vo.setAmount(1);
		vo.setBookNo(1);
		vo.setMemberNo(1);
		dao.update(vo);
		
	}


}
