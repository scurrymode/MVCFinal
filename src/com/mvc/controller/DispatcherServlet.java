package com.mvc.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DispatcherServlet extends HttpServlet{
	JSONParser parser;
	JSONObject jsonObject;
	FileInputStream fis;
	InputStreamReader reader;
	BufferedReader buffr;
	
	public void init(ServletConfig config) throws ServletException {
		//json mapping파일 읽어들여서 StringBuffer에 담아두고, 그걸 JSON객체로 만들어 읽자~!
		String configLocation = config.getInitParameter("configLocation");
		ServletContext context = config.getServletContext();
		String realPath = context.getRealPath(configLocation);
		StringBuffer sb = new StringBuffer();
		
		try {
			fis = new FileInputStream(realPath);
			reader = new InputStreamReader(fis);
			buffr = new BufferedReader(reader);
			String str = null;
			while(true){
				str = buffr.readLine();
				if(str==null)break;
				sb.append(str);
			}
			System.out.println(sb);
			parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(sb.toString());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if(buffr!=null){
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doRequest(req, resp);
	}

	public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//2단계: 요청을 분석한다!
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		JSONObject controllerObj = (JSONObject) jsonObject.get("controllerMapping");
		String uri = req.getRequestURI();
		String controllerName = (String) controllerObj.get(uri);
		System.out.println(controllerName);
		
		
		//클래스 동적 로드!
		Controller controller = null;
		try {
			Class controllerClass = Class.forName(controllerName);
			controller = (Controller)controllerClass.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//5단계 결과보여주기!
		String resultKey = controller.execute(req, resp); //부모의 메서드처럼 부르지만, 실제로는 오버라이드된 자식의 메서드가 실행되는 것은 다형성!
		JSONObject viewObj = (JSONObject) jsonObject.get("viewMapping");
		String viewName = (String) viewObj.get(resultKey);
		
		if(controller.isForward()){
			RequestDispatcher dis = req.getRequestDispatcher(viewName);
			dis.forward(req, resp);
		}else{
			resp.sendRedirect(viewName);
		}
		
	}
}
