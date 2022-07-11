package com.keycloak.model;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean
public class Login {

	 
	//===================================
	//		Data Members
	//===================================
	
	private String _username;
	private String _password;
	
	
	//===================================
	//		Properties
	//===================================	
	
	public String get_username() {
		return _username;
	}
	public void set_username(String _username) {
		this._username = _username;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String _password) {
		this._password = _password;
	}


	
	//===================================
	//		Default Constructor
	//===================================	
	
	public Login() {}
	
	//===================================
	//		Parameterized Constructor
	//===================================
	

	public Login(String _username, String _password) {
		this._username = _username;
		this._password = _password;
	}
	
	//===================================
	//		Override Function
	//===================================
	
	@Override
	public String toString() {
		return "Login [_username=" + _username + ", _password=" + _password + "]";
	}
	
	
	
}
