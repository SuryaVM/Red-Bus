package com.booking;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import com.Crud;
import com.Data.Bus;
import com.google.gson.Gson;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

//Action class of Search

public class SearchAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

	 @Override
	    public String execute() {
		 Cookie cookies[] = ServletActionContext.getRequest().getCookies();
			String username = Crud.validateCookie(cookies);
			System.out.println("username from cookie: "+username);
			if(username.equals("")) {
				HttpServletResponse response = ServletActionContext.getResponse();
				return "error";
			}
		    HttpServletResponse response = ServletActionContext.getResponse();
	        response.setContentType("application/json charset=utf-8");

	        String from = ServletActionContext.getRequest().getParameter("from");
	        String to = ServletActionContext.getRequest().getParameter("to");
	        String date = ServletActionContext.getRequest().getParameter("date");

	        List<Bus> buses=Crud.fetchBuses(from, to);

			Gson gson = new Gson();
			String jsonData = gson.toJson(buses);

	        try (PrintWriter out = response.getWriter()) {
	            out.print(jsonData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null; // Important: returning null to prevent Struts from forwarding
	    }

}




