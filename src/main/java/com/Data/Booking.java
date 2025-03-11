package com.Data;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Database-managed auto-increment
	@Column(name = "booking_id")
	private int booking_id;
	@Column(name = "travel_date")
	private Date date;
	@Column(name = "bus_id")
	private int busId;
	@Column(name = "seat_number")
	private String seatType;
	private String username;
	private String status;

	public Booking() {
	}

	public Booking(String username, String seatType, Date date, int busId) {
		this.username = username;
		this.seatType = seatType;
		this.date = date;
		this.busId = busId;
		this.status = "booked";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
