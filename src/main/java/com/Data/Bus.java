package com.Data;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="buses")
public class Bus {
	@Id
	private int id;
	private String bus_name;
	private String from_location;
	private String to_location;
	private Time departure_time;
	private Time arrival_time;
	private double price;
	private int seats_available;
	private int berths_available;
	private boolean sleeper;
	private boolean semi_sleeper;
	private boolean ac;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBus_name() {
		return bus_name;
	}
	public void setBus_name(String bus_name) {
		this.bus_name = bus_name;
	}
	public String getFrom_location() {
		return from_location;
	}
	public void setFrom_location(String from_location) {
		this.from_location = from_location;
	}
	public String getTo_location() {
		return to_location;
	}
	public void setTo_location(String to_location) {
		this.to_location = to_location;
	}
	public Time getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}
	public Time getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(Time arrival_time) {
		this.arrival_time = arrival_time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSeats_available() {
		return seats_available;
	}
	public void setSeats_available(int seats_available) {
		this.seats_available = seats_available;
	}
	public int getBerths_available() {
		return berths_available;
	}
	public void setBerths_available(int berths_available) {
		this.berths_available = berths_available;
	}
	public boolean isSleeper() {
		return sleeper;
	}
	public void setSleeper(boolean sleeper) {
		this.sleeper = sleeper;
	}
	public boolean isSemi_sleeper() {
		return semi_sleeper;
	}
	public void setSemi_sleeper(boolean semi_sleeper) {
		this.semi_sleeper = semi_sleeper;
	}
	public boolean isAc() {
		return ac;
	}
	public void setAc(boolean ac) {
		this.ac = ac;
	}
	@Override
	public String toString() {
		return "Bus [id=" + id + ", bus_name=" + bus_name + ", from_location=" + from_location + ", to_location="
				+ to_location + ", departure_time=" + departure_time + ", arrival_time=" + arrival_time + ", price="
				+ price + ", seats_available=" + seats_available + ", berths_available=" + berths_available
				+ ", sleeper=" + sleeper + ", semi_sleeper=" + semi_sleeper + ", ac=" + ac + "]";
	}



}
