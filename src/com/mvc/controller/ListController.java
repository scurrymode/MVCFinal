package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.model.student.StudentService;

public class ListController implements Controller{
	StudentService service = new StudentService();

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List list = service.showList();
		request.setAttribute("list", list);
		
		return "/result/student/list";
	}

	public boolean isForward() {
		return true;
	}
	

}
