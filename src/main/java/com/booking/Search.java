package com.booking;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import com.Crud;
import com.Data.Bus;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletResponse;


public class Search extends ActionSupport {

    private static final long serialVersionUID = 1L;


	private String from;
	private String to;
	private String date;
	private List<Bus> buses;
	private String jsonData;
//	public String getFrom() {
//		return from;
//	}
//	@StrutsParameter
//	public void setFrom(String from) {
//		this.from = from;
//	}
//	public String getTo() {
//		return to;
//	}
//	@StrutsParameter
//	public void setTo(String to) {
//		this.to = to;
//	}
//	public String getDate() {
//		return date;
//	}
//	@StrutsParameter
//	public void setDate(String date) {
//		this.date = date;
//	}
//
//	public List<Bus> getBuses() {
//		return buses;
//	}
	 @Override
	    public String execute() {
		 
		    HttpServletResponse response = ServletActionContext.getResponse();
	        response.setContentType("application/json charset=utf-8");
	        
	        from = ServletActionContext.getRequest().getParameter("from");
	        to = ServletActionContext.getRequest().getParameter("to");
	        date = ServletActionContext.getRequest().getParameter("date");
	        
	        buses=Crud.fetch(from, to);
	        
			Gson gson = new Gson();
			jsonData = gson.toJson(buses);
			
	        try (PrintWriter out = response.getWriter()) {
	            out.print(jsonData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null; // Important: returning null to prevent Struts from forwarding
	    }

}




