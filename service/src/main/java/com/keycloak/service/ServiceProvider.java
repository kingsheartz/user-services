package com.keycloak.service;


import javax.inject.Inject;

import com.keycloak.model.Login;
import com.keycloak.model.ResponseData;
import com.keycloak.model.User;
import com.keycloak.repository.HibernateOperations;
import java.lang.System;

public class ServiceProvider {

	public ServiceProvider() {}
	
	//=========================================
	//	Data Members
	//=========================================

	protected ResponseData res;
	@Inject
	protected HibernateOperations hibernateOperations;	
	
	//=========================================
	//	L O G I N   U S E R
	//=========================================
	
	public ResponseData LoginUser(Login credentials) {

		System.out.println("Reached Service Provider");
		//Response object
		res=hibernateOperations.LoginUser(credentials);
		return res;	
	}

	//=========================================
	//	G E T   U S E R
	//=========================================
		
	public ResponseData GetUser(int id) {

		//Response object
		res=hibernateOperations.SelectUser(id);
		return res;	
	}
		
	//=========================================
	//	G E T   U S E R   L I S T
	//=========================================
		
	public ResponseData GetUserList() {

		//Response object
		res=hibernateOperations.SelectUserList();
		return res;	
	}
	
	//=========================================
	//	C R E A T E    U S E R
	//=========================================
	
	public ResponseData CreateUser(User user) {

		//Response object
		System.out.println("serviceProvider");
		res=hibernateOperations.CreateUser(user);
		return res;
	}

	//=========================================
	//	U P D A T E    U S E R   (P H O N E)
	//=========================================
	
	public ResponseData EditUser( int id, User user) {
		//Response object
		res=hibernateOperations.EditUser(id,user);
		return res;
	}	
	
	//=========================================
	//		R E M O V E    U S E R 
	//=========================================
	
	public ResponseData RemoveUser( int id) {

		//Response object
		res=hibernateOperations.RemoveUser(id);
		return res;
	}	
		
	//=========================================
	//		D E L E T E    U S E R
	//=========================================
	
	public ResponseData DeleteUser(int id) {

		//Response object
		res=hibernateOperations.DeleteUser(id);
		return res;
	}	
}
