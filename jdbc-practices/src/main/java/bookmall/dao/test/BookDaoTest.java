package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;


public class BookDaoTest {

	public static void main(String[] args) {
		findAllTest();
		insertTest();
		deleteTest();

	}
	
	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		BookVo vo = null;
		BookDao dao = new BookDao();
		
		vo = new BookVo();
		vo.setTitle("스테파니메이어");
		vo.setPrice(20000);
		vo.setCategoryNo(1);
		dao.insert(vo); 	
	}
	
	private static void deleteTest() {
		BookDao dao = new BookDao();
		dao.delete(0);
	}

}
