package com.keycloak.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class User implements Serializable{

	//===================================
	//		Data Members
	//===================================
	@Id
	@Column(name="id")
	@GeneratedValue
	private int _id;
	
	@Column(name="firstname")
	private String _firstName;
	
	@Column(name="address")
	private String _address;
	
	@Column(name="email")
	private String _email;
	
	@Column(name="phone")
	private long _phone;
	
	@Column(name="pin")
	private int _pin;
	
	@Column(name="password")
	private String _password;
	
	@Column(name="status")
	private Boolean _status;
	
	//===================================
	//		Properties
	//===================================	

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}
	
	public String get_firstName() {
		return _firstName;
	}

	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}

	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
	}
	
	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public long get_phone() {
		return _phone;
	}

	public void set_phone(long _phone) {
		this._phone = _phone;
	}

	public int get_pin() {
		return _pin;
	}

	public void set_pin(int _pin) {
		this._pin = _pin;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}
	
	public Boolean get_status() {
		return _status;
	}

	public void set_status(Boolean _status) {
		this._status = _status;
	}

	//===================================
	//		Default Constructor
	//===================================	
	
	public User() {}
	
	//===================================
	//		Parameterized Constructor
	//===================================

	public User(int _id, String _firstName, String _address, String _email, long _phone, int _pin, String _password,
			Boolean _status) {
		super();
		this._id = _id;
		this._firstName = _firstName;
		this._address = _address;
		this._email = _email;
		this._phone = _phone;
		this._pin = _pin;
		this._password = _password;
		this._status = _status;
	}
	
	//===================================
	//		Override Function
	//===================================
	
	@Override
	public String toString() {
		return "User [_id=" + _id + ", _firstName=" + _firstName + ", _address=" + _address + ", _email=" + _email
				+ ", _phone=" + _phone + ", _pin=" + _pin + ", _password=" + _password + ", _status=" + _status + "]";
	}


	
}
