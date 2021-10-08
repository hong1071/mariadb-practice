package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		findAllTest();
		//insertTest();
		//updateTest();

	}
	
	private static void findAllTest() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		CategoryDao dao = new CategoryDao();
		
		CategoryVo vo1 = new CategoryVo();
		vo1.setName("국내소설");
		dao.insert(vo1); 
		
		CategoryVo vo2 = new CategoryVo();
		vo2.setName("예술/문화");
		dao.insert(vo2);
		
		CategoryVo vo3 = new CategoryVo();
		vo3.setName("수험서");
		dao.insert(vo3);
		
		findAllTest();
	}

	private static void updateTest() {
		CategoryVo vo = null;
		CategoryDao dao = new CategoryDao();
		
		vo = new CategoryVo();
		vo.setName("해외소설");
		vo.setNo(1);
		dao.update(vo);
		
		findAllTest();
		
	}

}
