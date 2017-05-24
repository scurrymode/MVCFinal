package com.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.model.student.Physical;
import com.mvc.model.student.Student;
import com.mvc.model.student.StudentService;

import common.exception.RegistFailException;

public class RegistController implements Controller {
	StudentService service = new StudentService();
	
	boolean isForward;
	String viewPage;

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//�Ķ���� �ޱ�
		String id = request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String blood=request.getParameter("blood");
		String weight=request.getParameter("weight");
		
		Student student = new Student();
		Physical physical = new Physical();
		
		student.setId(id);
		student.setPassword(password);
		student.setName(name);
		
		physical.setBlood(blood);
		physical.setWeight(Integer.parseInt(weight));
		
		//3�ܰ�: �˸´� ���� ��ü�� �� ��Ű��
		try {
			service.regist(student, physical);
			isForward=false;
			viewPage="/result/student/regist";
		} catch (RegistFailException e) {
			e.printStackTrace();
			
			//Ŭ���̾�Ʈ���� �������� ���� ������ü�� �����غ���~!
			request.setAttribute("registEx", e);
			isForward=true;
			viewPage="/error/student/regist";
		}
		return viewPage;
	}

	public boolean isForward() {
		return isForward;
	}

}
