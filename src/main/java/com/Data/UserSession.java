package com.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_sessions")
public class UserSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Database-managed auto-increment
	int id;
	String session_id;
	String username;
	Timestamp created_at;
	Timestamp expires_at;

	public UserSession() {
	}

	public UserSession(String session_id,String username) {
		this.session_id=session_id;
		this.username=username;
		this.created_at = Timestamp.from(Instant.now());
	    this.expires_at = Timestamp.from(Instant.now().plus(30, ChronoUnit.MINUTES));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(Timestamp expires_at) {
		this.expires_at = expires_at;
	}

}
