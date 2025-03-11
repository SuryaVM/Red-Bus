package com.Data;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	@Id
	private int user_id;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	private String password;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	private String full_name;
	private String phone_no;
	private Date date_of_birth;
	private Time created_at;
	private Time updated_at;
	private String salt;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public User() {
    }

	public User(String username,String hashedPassword,String email,String full_name,String salt){
		this.username=username;
		password=hashedPassword;
		this.email=email;
		this.full_name=full_name;
		this.salt=salt;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Time getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Time created_at) {
		this.created_at = created_at;
	}
	public Time getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Time updated_at) {
		this.updated_at = updated_at;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

}