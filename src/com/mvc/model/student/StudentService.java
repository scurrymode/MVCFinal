//����Ʈ�� ����~! ���� ��ü�� �Ͻ�Ű�� ������
//�� ��ü�� ���翡 ����, ��Ʈ�ѷ��� ������ ���ҿ� ������ �� �ִ�.
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
	
	//�л���� (Student + Physical)
	public int regist(Student student, Physical physical) throws RegistFailException {
		int result = 0;
		
		SqlSession session = manager.getSession();
		studentDAO.setSession(session);
		physicalDAO.setSession(session);
		
		//�����Ǵ� ���ܰ� ������ ����, �׷��� ���� ���ܰ� ��Ÿ�� ����
		//���⼭�� ��Ÿ�� ���ܴ�.
		try {
			int student_id = studentDAO.insert(student);
			physical.setStudent_id(student_id);
			physicalDAO.insert(physical);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new RegistFailException("�л���Ͽ� �����Ͽ����ϴ�."); 
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
