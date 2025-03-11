package com.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import com.Crud;
import com.Data.Booking;
import com.Data.Bus;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class MyBookingsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {
		Cookie cookies[] = ServletActionContext.getRequest().getCookies();
		String username = Crud.validateCookie(cookies);
		if(username.equals("")) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setStatus(401);
			return "error";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json charset=utf-8");


		List<Booking> mybookings = Crud.myBookings(username);

		JsonArray resultArray = new JsonArray();

		for (Booking booking : mybookings) {

			List<Bus> buses;
			Bus bus = null;
			buses = Crud.fetchBusesWithId(booking.getBusId());
			if (!buses.isEmpty()) {
				bus = buses.get(0);
			} else {
				System.out.println("fetchBusesWithId " + booking.getBusId() + " is empty");
			}
			JsonObject combined = new JsonObject();
			combined.addProperty("bookingId", booking.getBooking_id());
			combined.addProperty("travelDate", booking.getDate().toString());
			combined.addProperty("seatType", booking.getSeatType());
			combined.addProperty("username", booking.getUsername());
			combined.addProperty("status", booking.getStatus());
			combined.addProperty("busId", booking.getBusId());

			if (bus != null) {
				combined.addProperty("bus_name", bus.getBus_name());
				combined.addProperty("from_location", bus.getFrom_location());
				combined.addProperty("to_location", bus.getTo_location());
				combined.addProperty("arrival_time", bus.getArrival_time().toString());
				combined.addProperty("departure_time", bus.getDeparture_time().toString());

			} else {
				System.out.println("fetchBusesWithId bus is empty");
			}

			resultArray.add(combined);
		}

		Gson gson = new Gson();
		String jsonData = gson.toJson(resultArray);

		try (PrintWriter out = response.getWriter()) {
			out.print(jsonData);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null; // Important: returning null to prevent Struts from forwarding
	}
}
