package com.booking;

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
		if(username.equals("")) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setStatus(401);
			return "error";
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
