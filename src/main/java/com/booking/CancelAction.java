package com.booking;

import java.io.IOException;
import java.text.ParseException;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import com.Crud;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

//Action class of Cancelling

public class CancelAction extends ActionSupport {

	@Override
	public String execute() throws ParseException {
		Cookie cookies[] = ServletActionContext.getRequest().getCookies();
		String username = Crud.validateCookie(cookies);
		HttpServletResponse response = ServletActionContext.getResponse();
		if(username.equals("")) {
		    response.setContentType("application/json;charset=utf-8");
		    response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
		    try {
				response.getWriter().write("{\"error\": true, \"message\": \"Invalid session\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return null;
		}
		String busId = ServletActionContext.getRequest().getParameter("busId");
		String bookingId = ServletActionContext.getRequest().getParameter("bookingId");
		boolean isSleeper = "sleeper".equals(ServletActionContext.getRequest().getParameter("seatType"));
		System.out.println("busId: " + busId);
		System.out.println("bookingId: " + bookingId);
		System.out.println("username: " + username);
		Crud.cancelBus(username, Integer.parseInt(bookingId));
		Crud.updateSeatCount(Integer.parseInt(busId), isSleeper, !isSleeper, true);

		return null;
	}
}
