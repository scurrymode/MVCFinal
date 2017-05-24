package com.mvc.model.student;

public class Student {
	private int student_id;
	private String id;
	private String password;
	private String name;
	private Physical physical;

	public Physical getPhysical() {
		return physical;
	}

	public void setPhysical(Physical physical) {
		this.physical = physical;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
