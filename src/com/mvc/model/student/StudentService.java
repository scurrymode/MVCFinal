//모델파트의 수장~! 여러 객체에 일시키는 관리자
//이 객체의 존재에 의해, 컨트롤러가 본연의 역할에 집중할 수 있다.
package com.mvc.model.student;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mvc.mybatis.ConfigManager;

import common.exception.RegistFailException;

public class StudentService {
	ConfigManager manager = ConfigManager.getInstance();
	
	StudentDAO studentDAO;
	PhysicalDAO physicalDAO;
	
	
	
	public StudentService() {
		studentDAO = new StudentDAO();
		physicalDAO = new PhysicalDAO();
	}
	
	//학생등록 (Student + Physical)
	public int regist(Student student, Physical physical) throws RegistFailException {
		int result = 0;
		
		SqlSession session = manager.getSession();
		studentDAO.setSession(session);
		physicalDAO.setSession(session);
		
		//강제되는 예외가 컴파일 예외, 그렇지 않은 예외가 런타임 예외
		//여기서는 런타임 예외다.
		try {
			int student_id = studentDAO.insert(student);
			physical.setStudent_id(student_id);
			physicalDAO.insert(physical);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new RegistFailException("학생등록에 실패하였습니다."); 
		}

		return result;
	}
	
	public List showList(){
		SqlSession session = manager.getSession();
		studentDAO.setSession(session);
		List list = studentDAO.selectJoin();
		session.close();
		return list;
	}
	

}
