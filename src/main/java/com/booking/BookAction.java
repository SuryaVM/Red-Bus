package com.booking;

import java.io.IOException;
import java.text.ParseException;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import com.Crud;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class BookAction extends ActionSupport{
	private static final long serialVersionUID = 1L;



	@Override
	public String execute() throws ParseException {

//        from = ServletActionContext.getRequest().getParameter("from");
//        to = ServletActionContext.getRequest().getParameter("to");
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
        String date = ServletActionContext.getRequest().getParameter("date");
        String busId = ServletActionContext.getRequest().getParameter("busId");
        String seatType = ServletActionContext.getRequest().getParameter("seatType");


        boolean isSleeper = "sleeper".equals(seatType);

        Crud.bookBus(date,busId,seatType,username);
        Crud.updateSeatCount(Integer.parseInt(busId), isSleeper, !isSleeper, false);

        return null; // Important: returning null to prevent Struts from forwarding
    }
}
