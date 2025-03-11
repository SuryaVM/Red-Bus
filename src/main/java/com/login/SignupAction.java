package com.login;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import com.Crud;

//Action class of Signup

public class SignupAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String email;
	private String full_name;
	public String getUsername() {
		return username;
	}
	@StrutsParameter
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	@StrutsParameter
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	@StrutsParameter
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFull_name() {
		return full_name;
	}
	@StrutsParameter
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}


	@Override
	public String execute() {
		boolean isvalid=Crud.addUser(username,password,email,full_name);
		return isvalid ? "success" : "error";
	}

}
