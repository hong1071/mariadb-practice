package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		findAllTest();
		insertTest();
		updateTest();

	}
	
	private static void findAllTest() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		CategoryVo vo = null;
		CategoryDao dao = new CategoryDao();
		
		vo = new CategoryVo();
		vo.setName("국내소설");
		dao.insert(vo); 
		
	}

	private static void updateTest() {
		CategoryVo vo = null;
		CategoryDao dao = new CategoryDao();
		
		vo = new CategoryVo();
		vo.setName("좋은생각");
		vo.setNo(1);
		dao.update(vo);
		
	}

}
