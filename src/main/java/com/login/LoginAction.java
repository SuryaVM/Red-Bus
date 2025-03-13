package com.login;

import java.util.UUID;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import com.Crud;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

//Action class of Login

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String error="";

	@Override
	public String execute() {
		boolean isvalid = Crud.validateUser(username, password);
		if (isvalid) {
			String fetched = Crud.fetchSession(username);
			String sessionId;
			if (fetched.equals("")) {
				sessionId = UUID.randomUUID().toString();
				Crud.addSession(sessionId, username);
			} else {
				sessionId=fetched;
			}

			Cookie sessionCookie = new Cookie("SESSION_ID", sessionId);
			sessionCookie.setHttpOnly(true); // Security: inaccessible to JavaScript
			sessionCookie.setSecure(true); // For HTTP testing; true for HTTPS
			sessionCookie.setMaxAge(60 * 10); // fr 10 mins
			sessionCookie.setPath("/"); // accessible fr all paths
			HttpServletResponse response = ServletActionContext.getResponse();
			response.addCookie(sessionCookie);

			return "success";
		}
		error="*Invalid Username or Password!";
		return "error";
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

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
}
