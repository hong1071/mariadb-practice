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
		MemberDao dao = new MemberDao();
		
		MemberVo vo1 = new MemberVo();
		vo1.setName("홍길동");
		vo1.setEmail("hong1071@nate.com");
		vo1.setPassword("123456");
		vo1.setPhoneNo("01086521071");
		dao.insert(vo1); 
		
		MemberVo vo2 = new MemberVo();
		vo2.setName("전우치");
		vo2.setEmail("jeonWC@naver.com");
		vo2.setPassword("123456");
		vo2.setPhoneNo("01055674213");
		dao.insert(vo2); 
		
		findAllTest();
	}
	
	private static void deleteTest() {
		MemberDao dao = new MemberDao();
		dao.delete(1);
		
		findAllTest();
	}
	
	private static void updateTest() {
		MemberVo vo = null;
		MemberDao dao = new MemberDao();
		
		vo = new MemberVo();
		vo.setPhoneNo("01086521076");
		vo.setNo(1);
		dao.update(vo);
		
		findAllTest();
	}

}
