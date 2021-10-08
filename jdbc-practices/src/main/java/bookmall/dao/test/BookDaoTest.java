package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;


public class BookDaoTest {

	public static void main(String[] args) {
		//findAllTest();
		//insertTest();
		//deleteTest();

	}
	
	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		BookDao dao = new BookDao();
		
		BookVo vo1 = new BookVo();
		vo1.setTitle("칼의 노래");
		vo1.setPrice(20000);
		vo1.setCategoryNo(1);
		dao.insert(vo1); 	
		
		BookVo vo2 = new BookVo();
		vo2.setTitle("프랑스 문화사");
		vo2.setPrice(34000);
		vo2.setCategoryNo(2);
		dao.insert(vo2); 
		
		BookVo vo3 = new BookVo();
		vo3.setTitle("ncs 부산교통공사 모의고사 4회");
		vo3.setPrice(13000);
		vo3.setCategoryNo(3);
		dao.insert(vo3); 
		
		findAllTest();
	}
	
	private static void deleteTest() {
		BookDao dao = new BookDao();
		dao.delete(1);
		
		findAllTest();
	}

}
