package com.login;


import org.apache.struts2.ActionSupport;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

public class Login extends ActionSupport{

	private String username;
    private String password;

    @Override
    public String execute() {

        if ("a".equals(username) && "a".equals(password)) {

            return "success";
        } else {

            return "error";
        }

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
