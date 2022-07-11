package com.keycloak.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.keycloak.model.Login;
import com.keycloak.model.ResponseData;
import com.keycloak.model.User;


@Transactional(SUPPORTS)
public class HibernateOperations {
	
	//=========================================
	//	Data Members
	//=========================================

	private ResponseData res;
	private Session session;

	//=========================================
	//	L O G I N   U S E R
	//=========================================
	
	public ResponseData LoginUser(Login credential) {
		
		//Response object
		res=new ResponseData();
		System.out.println("Login reached");
		try {
			session=SessionStart();
			
			//Inline assigning of values
			//Query<User> q=session.createQuery("select u from User u where u._password = '"+credential.get_password()+"' and u._firstName = '"+credential.get_username()+"'");
			
			//Parameterized assigning of values
			Query<User> q=session.createQuery("select u from User u where u._password = :password and u._firstName = :username");
			
			//Settng up of parameters
			q.setParameter("password", credential.get_password());
			q.setParameter("username", credential.get_username());
			
			//Getting the result
			User user=q.getSingleResult(); 
			
			//If result is not empty getting the status of success
			if(user != null) {
				
				//Generate the response
				res.set_responseCode(200);
				res.set_responseMessage("User data retrieved successfully");
				System.out.println("User data retrieved successfully");
				res.set_responseObject(user);
			}else {
				
				//Generate the response
				res.set_responseCode(202);
				res.set_responseMessage("Invalid Credentials");
			}

		} catch (HibernateException e) {
			//Generate the response
			res.set_responseCode(500);
			res.set_responseMessage(e.getMessage());
		}
		
		//if no results matching the input credentials 'Not found' response is send back
		catch (NoResultException e) {
			//Generate the response
			res.set_responseCode(202);
			res.set_responseMessage("Invalid Credentials");
			System.out.println("Invalid Credentials");
		}
		return res;
	}
	
	//=========================================
	//		C R E A T E    U S E R 
	//=========================================
	
	public ResponseData CreateUser(User user) {
 
		//Response object
		res=new ResponseData();
         
		try {

			if(user != null) {
				
				session=SessionStart();

				//try to get user with the requested id and status true
				Query<User> q=session.createQuery("select u from User u where u._firstName = :userName");
				
				//Setting up of parameters
				q.setParameter("userName", user.get_firstName());
				
				User getUser=null;
				//Getting the result
				try {
					getUser=q.getSingleResult(); 

					if(getUser != null) {
						res.set_responseCode(202);
						res.set_responseMessage("User already exists");
						System.out.println("User already exits");
						return res;
					}
				}
				
				//if user removed
				catch(Exception e) {
					System.out.println(e.getMessage());
					if(getUser == null) {

						Transaction tx=session.beginTransaction();

						//Set status as active
						//user.set_id(Math.abs(new Random().nextInt()));
						user.set_status(true);

						//Save to DB
						session.save(user);
						tx.commit();
						
						//Generate the response
						res.set_responseCode(200);
						res.set_responseMessage("User Registered Successfully");
						System.out.println("User Registered Successfully");
						res.set_responseObject(user);
					}
				}
			}
			else{
				res.set_responseCode(202);
				res.set_responseMessage("Invalid User data");
				System.out.println("Invalid User data");
			}
		} catch (HibernateException e) {
			//Generate the response
			res.set_responseCode(500);
			res.set_responseMessage(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		//Return the response
		return res;

	}	
	
	//=========================================
	//		S E L E C T    U S E R 
	//=========================================
	public ResponseData SelectUser(int id) {
		
		//Response object
		res=new ResponseData();
		User user=null;
		try {
			session=SessionStart();
			
			
			//try to get user with the requested id
			user=session.get(User.class, id);
			
			if(user != null) {
				
				//try to get user with the requested id and status true
				Query<User> q=session.createQuery("select u from User u where u._id = :id and u._status = :status");
				
				//Setting up of parameters
				q.setParameter("id", id);
				q.setParameter("status", true);
				
				user=null;
				//Getting the result
				try {
					user=q.getSingleResult(); 
				}
				
				//if user removed
				catch(Exception e) {
					res.set_responseCode(202);
					res.set_responseMessage("User with ID "+id+" removed");
				}
				
			
				if(user != null) {
					
					//Generate the response
					res.set_responseCode(200);
					res.set_responseMessage("User data retrieved successfully");
					res.set_responseObject(user);
				}
			}
			else {
				
				//Generate the response
				res.set_responseCode(202);
				res.set_responseMessage("No User found with ID "+id);
				res.set_responseObject(user);
			}
			
		} catch (HibernateException e) {
			//Generate the response
			res.set_responseCode(500);
			res.set_responseMessage(e.getMessage());
		}
		
		return res;
	}

	//=========================================
	//	S E L E C T   U S E R   L I S T
	//=========================================
		
	public ResponseData SelectUserList() {
		//Response object
		res=new ResponseData();
		
		try {
			session=SessionStart();
			Query<User> q=session.createQuery("select u from User u");
		
			//Generate the response
			if(!(q.list().isEmpty())){
				res.set_responseCode(200);
				res.set_responseMessage("Users datas retrieved successfully");
				res.set_responseList(q.list());
			}
			else{
				res.set_responseCode(202);
				res.set_responseMessage("No user data found");
			}
		} catch (Exception e) {
			//Generate the response
			res.set_responseCode(500);
			res.set_responseMessage(e.getMessage());
		}
		return res;
	}
	
	//=========================================
	//	U P D A T E    U S E R   (P H O N E)
	//=========================================
	
	public ResponseData EditUser(int id,User user) {
		//Response object
		res=new ResponseData();
		
		try {
			session=SessionStart();
			//try to get user with the requested id
			User previousUserData=session.get(User.class, id);
			
			//Generate the response
			
			//If user exits
			if(previousUserData != null) {

				Long newPhoneNumber=user.get_phone();
				//Validate phone number
				if(String.valueOf(newPhoneNumber).length()==10){

					System.out.println(" new Phone number : "+String.valueOf(newPhoneNumber));
					
					Transaction tx=session.beginTransaction();
					previousUserData.set_phone(newPhoneNumber);
					session.saveOrUpdate(previousUserData);
					tx.commit();
					
					res.set_responseCode(200);
					res.set_responseMessage("Phone number Updated Successfully from "+previousUserData.get_phone() + " to " +user.get_phone());
					res.set_responseObject(previousUserData);
				}
				else{
					res.set_responseCode(202);
					res.set_responseMessage("Invalid Phone Number");
				}
			}
			//if user not found
			else {
				res.set_responseCode(202);
				res.set_responseMessage("No User found with ID "+id);
			}
			
		} catch (Exception e) {
			res.set_responseCode(500);
			res.set_responseMessage(e.getMessage());
		}
		return res;
	}
	
	//=========================================
	//		R E M O V E    U S E R 
	//=========================================
	
	public ResponseData RemoveUser(int id) {
		//Response object
		res=new ResponseData();
		User userData=null;
		try {
			session=SessionStart();
			
			
			//try to get user with the requested id
			userData=session.get(User.class, id);
			
			if(userData != null) {
				
				//try to get user with the requested id and status true
				Query<User> q=session.createQuery("select u from User u where u._id = :id and u._status = :status");
				
				//Setting up of parameters
				q.setParameter("id", id);
				q.setParameter("status", true);
				
				userData=null;
				//Getting the result
				try {
					userData=q.getSingleResult(); 
				}
				
				//if user already removed
				catch(Exception e) {
					res.set_responseCode(202);
					res.set_responseMessage("User with ID "+id+" already removed");
				}
				
				//Generate the response
				
				//If user exits
				if(userData != null) {
					
					Transaction tx=session.beginTransaction();
					userData.set_status(false);
					session.saveOrUpdate(userData);
					tx.commit();
					
					res.set_responseCode(200);
					res.set_responseMessage("User ("+ userData.get_id()+","+userData.get_firstName()+") Removed Successfully ");
					res.set_responseObject(userData);
				}
				
			}
			else {
				res.set_responseCode(202);
				res.set_responseMessage("No User found with ID "+id);
			}
			
		} 
		
		
		catch (HibernateException e) {
			res.set_responseCode(202);
			res.set_responseMessage("No User found with ID "+id);
			
		} 
		
		catch (Exception e) {
			res.set_responseCode(500);
			res.set_responseMessage(e.getMessage());
		}
		return res;
	}
	
	//=========================================
	//		D E L E T E   U S E R 
	//=========================================
	
	public ResponseData DeleteUser(int id) {
		//Response object
		res=new ResponseData();
		
		try {
			session=SessionStart();
			//try to get user with the requested id
			User userData=session.get(User.class, id);
			
			//Generate the response
			
			//If user exits
			if(userData != null) {
				
				Transaction tx=session.beginTransaction();
				session.delete(userData);
				tx.commit();
				
				res.set_responseCode(200);
				res.set_responseMessage("User ("+ userData.get_id()+","+userData.get_firstName()+") Deleted Successfully ");
				res.set_responseObject(userData);
			}
			//if user not found
			else {
				res.set_responseCode(202);
				res.set_responseMessage("No User found with ID "+id);
			}
			
		} catch (Exception e) {
			res.set_responseCode(500);
			res.set_responseMessage(e.getMessage());
		}
		return res;
	}
	
	//=========================================
	//		H I B E R N A T E   S E S S I O N 
	//=========================================
	public Session SessionStart() {

	System.out.println("Hibernate Operations Started");
	
	Session session=null;
	try {
		SessionFactory sessionFactory = new Configuration().addAnnotatedClass(User.class).configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Session Successful");
		
	} catch (HibernateException e) {
		System.out.println(e.getMessage());
		System.out.println("Session Failed");
	} 
	return session;
	}
}
