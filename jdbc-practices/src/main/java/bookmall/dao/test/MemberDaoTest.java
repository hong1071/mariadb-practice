package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.dao.MemberDao;
import bookmall.vo.CartVo;
import bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		findAllTest();
		//insertTest();
		//deleteTest();
		//updateTest();

	}
	
	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		MemberVo vo = null;
		MemberDao dao = new MemberDao();
		
		vo = new MemberVo();
		vo.setName("스테파니메이어");
		vo.setEmail("hong1071@nate.com");
		vo.setPassword(123456);
		vo.setPhoneNo("01086521071");
		dao.insert(vo); 
		
	}
	
	private static void deleteTest() {
		MemberDao dao = new MemberDao();
		dao.delete(0);
	}
	
	private static void updateTest() {
		MemberVo vo = null;
		MemberDao dao = new MemberDao();
		
		vo = new MemberVo();
		vo.setName("나쁜생각");
		vo.setEmail("hong1072@nate.com");
		vo.setPassword(345678);
		vo.setPhoneNo("01086521072");
		dao.update(vo);
		
	}

}
