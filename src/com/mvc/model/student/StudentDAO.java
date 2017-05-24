package com.mvc.model.student;

import java.util.List;

import org.apache.ibatis.session.SqlSession;


public class StudentDAO {
	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	//등록
	public int insert(Student student){
		int result = 0;
		result = session.insert("Student.insert", student);
		int student_id = student.getStudent_id();
		return student_id;
	}
	
	//목록 보여주기
	public List selectJoin(){
		List list = null;
		list = session.selectList("Student.selectJoin");		
		return list;
	}
	

}
