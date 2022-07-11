package com.keycloak.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.logging.Level;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.keycloak.model.Log;
import com.keycloak.model.Login;
import com.keycloak.model.ResponseData;
import com.keycloak.model.ELKStack.ELKResponseData;
import com.keycloak.model.User;
import com.keycloak.model.ELKStack.Logger;
import com.keycloak.service.ElasticSearchServices;
import com.keycloak.service.ServiceProvider;


@Path("/services")
public class EndPoint{

	//=========================================
	//	Data Members
	//=========================================
	private ResponseData res;
	private Log my_log;
	@Inject
	protected ServiceProvider serviceProvider;
	
	
	//=========================================
	//	L O G I N   U S E R
	//=========================================
	
	@POST
	@Path("/login")
	@Consumes({APPLICATION_JSON,APPLICATION_XML})
	@Produces(APPLICATION_JSON)
	public Response LoginUser(@NotNull Login credential) {
		//Response object
		System.out.println("Reached Endpoint");
		res=serviceProvider.LoginUser(credential);
		
		try {
			my_log = new Log("logger");
			System.out.println("Try File creating");
			my_log.logger.setLevel(Level.INFO);
			// my_log.logger.info("Log in User");
			my_log.logger.info(res.get_responseMessage());
			// my_log.logger.info(res.toString());
			my_log.fh.close();
		}
		catch(Exception e) {}
		
		return Response		
			      .status(res.get_responseCode())
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			      .entity(res)
			      .build();
		
	}	
	
	//=========================================
	//	S E L E C T   U S E R
	//=========================================
	
	@GET
	@Path("/select/{id}")
	@Consumes({APPLICATION_JSON,APPLICATION_XML})
	@Produces(APPLICATION_JSON)
	public Response SelectUser(@QueryParam("id") @NotNull int id) {
		//Response object
		res=serviceProvider.GetUser(id);
		//return Response.ok(res).build();
		
		try {
			my_log = new Log("logger");
			System.out.println("Try File creating");
			my_log.logger.setLevel(Level.INFO);
			// my_log.logger.info("Select User");
			my_log.logger.info(res.get_responseMessage());
			// my_log.logger.info(res.toString());
			my_log.fh.close();
		}
		catch(Exception e) {
			
		}
		
		return Response		
	      .status(res.get_responseCode())
	      .header("Access-Control-Allow-Origin", "*")
	      .header("Access-Control-Allow-Credentials", "true")
	      .header("Access-Control-Allow-Headers",
	        "origin, content-type, accept, authorization")
	      .header("Access-Control-Allow-Methods", 
	        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	      .entity(res)
	      .build();
		
	}	
	

	//=========================================
	//	S E L E C T   U S E R   L I S T
	//=========================================
	
	@GET
	@Path("/selectusers")
	@Produces(APPLICATION_JSON)
	public Response SelectUserList() {
		//Response object
		res=serviceProvider.GetUserList();
		System.out.println(res);
		
		try {
			my_log = new Log("logger");
			System.out.println("Try File creating");
			my_log.logger.setLevel(Level.INFO);
			// my_log.logger.info("Select User List");
			my_log.logger.info(res.get_responseMessage());
			// my_log.logger.info(res.toString());
			my_log.fh.close();
		}
		catch(Exception e) {
			
		}
		
		return Response		
			      .status(res.get_responseCode())
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			      .entity(res)
			      .build();
	}	
		
	
	//=========================================
	//	C R E A T E    U S E R
	//=========================================
	
	@POST
	@Path("/create")
	@Consumes({APPLICATION_JSON,APPLICATION_XML})
	@Produces(APPLICATION_JSON)
	public Response CreateUser(User user) {
		//Response object
		res=serviceProvider.CreateUser(user);
		
		try {
			my_log = new Log("logger");
			System.out.println("Try File creating");
			my_log.logger.setLevel(Level.INFO);
			// my_log.logger.info("Created User");
			my_log.logger.info(res.get_responseMessage());
			// my_log.logger.info(res.toString());
			my_log.fh.close();
		}
		catch(Exception e) {
			
		}
		
		return Response.ok(res).build();
	}
	
	//=========================================
	//	U P D A T E    U S E R   (P H O N E)
	//=========================================
	
	@POST
	@Path("/update")
	@Consumes({APPLICATION_JSON,APPLICATION_XML})
	@Produces(APPLICATION_JSON)
	public Response EditUser(@QueryParam("id") @NotNull int id, User user) {
		//Response object
		res=serviceProvider.EditUser(id,user);
		
		try {
			my_log = new Log("logger");
			System.out.println("Try File creating");
			my_log.logger.setLevel(Level.INFO);
			// my_log.logger.info("Updated User Phone Number");
			my_log.logger.info(res.get_responseMessage());
			// my_log.logger.info(res.toString());
			my_log.fh.close();
		}
		catch(Exception e) {
			
		}
		
		return Response		
			      .status(res.get_responseCode())
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			      .entity(res)
			      .build();
		
	}	
	
	

	//=========================================
	//		R E M O V E    U S E R 
	//=========================================
	
	@POST
	@Path("/remove")
	@Consumes({APPLICATION_JSON,APPLICATION_XML})
	@Produces(APPLICATION_JSON)
	public Response RemoveUser(@QueryParam("id") @NotNull int id) {
		//Response object
		res=serviceProvider.RemoveUser(id);
		
		try {
			my_log = new Log("logger");
			System.out.println("Try File creating");
			my_log.logger.setLevel(Level.INFO);
			// my_log.logger.info("Remove User");
			my_log.logger.info(res.get_responseMessage());
			// my_log.logger.info(res.toString());
			my_log.fh.close();
		}
		catch(Exception e) {
			
		}
		
		return Response		
			      .status(res.get_responseCode())
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			      .entity(res)
			      .build();
	}	
	

	//=========================================
	//		D E L E T E    U S E R
	//=========================================
	
	@DELETE
	@Path("/delete")
	@Consumes({APPLICATION_JSON,APPLICATION_XML})
	@Produces(APPLICATION_JSON)
	public Response DeleteUser(@QueryParam("id") @NotNull int id) {
		//Response object
		res=serviceProvider.DeleteUser(id);
		
		try {
			my_log = new Log("logger");
			System.out.println("Try File creating");
			my_log.logger.setLevel(Level.INFO);
			// my_log.logger.info("Delete User");
			my_log.logger.info(res.get_responseMessage());
			// my_log.logger.info(res.toString());
			my_log.fh.close();
			
		}
		catch(Exception e) {
			
		}
		
		return Response		
			      .status(res.get_responseCode())
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			      .entity(res)
			      .build();
	}	



	//=========================================
	//		S E N D    I N D E X
	//=========================================

	@Path("/sendindex")
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response SendIndex(Logger iLogger){
		//Objects
		ElasticSearchServices elasticSearchServices=new ElasticSearchServices();
		ELKResponseData responseData=elasticSearchServices.SendIndex(iLogger);

		return Response.ok()
				.status(responseData.get_responseCode())
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Credentials", "true")
			    .header("Access-Control-Allow-Headers",
			    "origin, content-type, accept, authorization")
			    .header("Access-Control-Allow-Methods", 
			    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.entity(responseData)
				.build();
	}



	//=========================================
	//		G E T    I N D I C E S
	//=========================================

	@Path("/getindices")
	@GET
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response GetIndices(){
		//Objects
		ElasticSearchServices elasticSearchServices=new ElasticSearchServices();
		return elasticSearchServices.GetIndices();
		
	}

	//=========================================
	//		S E A R C H    E L E M E N T S 
	//=========================================

	@Path("/searchelements")
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response GetSearchElements(String indexData){
		//Objects
		System.out.println(indexData);
		ElasticSearchServices elasticSearchServices=new ElasticSearchServices();
		return elasticSearchServices.GetSearchElements(indexData);
		
	}



	//=========================================
	//		S E A R C H    E L E M E N T S 
	//=========================================
}
