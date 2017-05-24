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
		
		//파라미터 받기
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
		
		//3단계: 알맞는 로직 객체에 일 시키기
		try {
			service.regist(student, physical);
			isForward=false;
			viewPage="/result/student/regist";
		} catch (RegistFailException e) {
			e.printStackTrace();
			
			//클라이언트까지 가져가기 위해 에러객체를 저장해보자~!
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
