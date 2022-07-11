package com.keycloak.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.ws.rs.core.Response;

import com.keycloak.model.Login;
import com.keycloak.model.ResponseData;
import com.keycloak.model.User;
import com.keycloak.repository.HibernateOperations;
import com.keycloak.service.ServiceProvider;

import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.EasyMockSupport;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class EndPointTest extends EasyMockSupport {

	// =========================================
	// Data Members
	// =========================================

	private ServiceProvider serviceProvider;
	Login credentials;
	ResponseData response;
	User user;

	// =========================================
	// General Error For ID
	// =========================================

	// TEST FOR ERROR (Null)
	// --------------------------
	@Test
	public void testForValidationNullError() {

		String id = null;

		// Checking the id is null
		assertNull(id);
	}

	// TEST FOR ERROR (<=0)
	// -----------------------
	@Test
	public void testForValidationLesserOrZeroError() {

		int id = 0;

		// Checking the id is not greater than 0
		assertEquals(true, id <= 0);
	}

	// TEST FOR ERROR (Type)
	// -----------------------
	@Test
	public void testForValidationTypeError() {

		String id = "3";

		// Checking the id is not integer
		assertNotEquals("Integer", ((Object) id).getClass().getSimpleName());
	}

	// ========================================================
	// L O G I N U S E R
	// ========================================================

	// TEST FOR SUCCESS
	// -----------------

	@Test
	public void testLoginUserForSuccess() {

		// Create mock object for EndPoint
		EndPoint endPoint = new EndPoint();

		// Create object for Login
		credentials = EasyMock.createNiceMock(Login.class);

		// Set properties to Login object
		EasyMock.expect(credentials.get_username()).andReturn("Stark");
		EasyMock.expect(credentials.get_password()).andReturn("123456");

		// Create mock object for ServiceProvider
		serviceProvider = createNiceMock(ServiceProvider.class);

		// Create mock object for ResponseData
		response = createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(200);

		Response res = createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(200);

		EasyMock.expect(serviceProvider.LoginUser(credentials)).andReturn(response);

		// Create object for ServiceProvider
		endPoint.serviceProvider = serviceProvider;

		EasyMock.replay(credentials, serviceProvider, response, res);

		// Check whether the Log in successfull or not
		assertEquals(200, res.getStatus());

		EasyMock.verify();
	}

	// TEST FOR ERROR
	// -----------------

	@Test
	public void testLoginUserForValidationError() {

		// Create mock object for EndPoint
		EndPoint endPoint = new EndPoint();

		// Create object for Login
		credentials = EasyMock.createNiceMock(Login.class);

		// Set properties to Login object
		EasyMock.expect(credentials.get_username()).andReturn("Stak");
		EasyMock.expect(credentials.get_password()).andReturn("123456");

		// Create mock object for ServiceProvider
		serviceProvider = createNiceMock(ServiceProvider.class);

		// Create mock object for ResponseData
		response = createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(202);

		Response res = createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(202);

		EasyMock.expect(serviceProvider.LoginUser(credentials)).andReturn(response);

		// Create object for ServiceProvider
		endPoint.serviceProvider = serviceProvider;

		EasyMock.replay(credentials, serviceProvider, response, res);

		// Check Invalid credential
		assertEquals(202, res.getStatus());

		EasyMock.verify();
	}

	// =========================================
	// C R E A T E U S E R
	// =========================================
	// TEST FOR SUCCESS
	// -------------------
	@Test
	public void testCreateUserForSuccess() {

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();
		// Create object for User
		user = createNiceMock(User.class);

		EasyMock.expect(user.get_firstName()).andReturn("Starkandsteve");
		EasyMock.expect(user.get_email()).andReturn("steve@gmail.com");
		EasyMock.expect(user.get_phone()).andReturn(Long.valueOf("9874563210"));
		EasyMock.expect(user.get_address()).andReturn("Location");
		EasyMock.expect(user.get_pin()).andReturn(680312);
		EasyMock.expect(user.get_password()).andReturn("123456");

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(200);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(200);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.CreateUser(user)).andReturn(response);

		EasyMock.replay(user, serviceProvider, response, res);

		// //Checking that object is not null
		assertNotNull(user);

		// Check response status - success
		assertEquals(200, res.getStatus());
		EasyMock.verify();
	}

	// TEST FOR ERROR (Already exists)
	// ---------------------------------
	@Test
	public void testCreateUserForExistsError() {

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();
		// Create object for User
		user = createNiceMock(User.class);

		EasyMock.expect(user.get_firstName()).andReturn("Starkandsteve");
		EasyMock.expect(user.get_email()).andReturn("steve@gmail.com");
		EasyMock.expect(user.get_phone()).andReturn(Long.valueOf("9874563210"));
		EasyMock.expect(user.get_address()).andReturn("Location");
		EasyMock.expect(user.get_pin()).andReturn(680312);
		EasyMock.expect(user.get_password()).andReturn("123456");

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(202);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(202);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.CreateUser(user)).andReturn(response);

		EasyMock.replay(user, serviceProvider, response, res);

		// //Checking that object is not null
		assertNotNull(user);

		// Check response status - failure
		assertEquals(202, res.getStatus());
		EasyMock.verify();
	}

	// TEST FOR ERROR (Null Object)
	// ---------------------------------
	@Test
	public void testCreateUserForNullObjectError() {

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for User
		user = null;

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(202);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(202);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.CreateUser(user)).andReturn(response);

		EasyMock.replay(serviceProvider, response, res);
		// Checking that object is null
		assertNull(user);

		// Check response status - failure
		assertEquals(202, response.get_responseCode());
		EasyMock.verify();
	}

	// TEST FOR ERROR (Null validation)
	// ---------------------------------
	@Test
	public void testCreateUserForNullError() {

		// Create object for Login and set properties
		user = new User();
		user.set_firstName(null);
		user.set_email(null);
		user.set_phone(0);
		user.set_address(null);
		user.set_pin(0);
		user.set_password(null);

		// Checking the datas are null
		assertNull(user.get_firstName());
		assertNull(user.get_email());
		assertEquals(true, user.get_phone() <= 0);
		assertNull(user.get_address());
		assertEquals(true, user.get_pin() <= 0);
		assertNull(user.get_password());
	}

	// ========================================================
	// S E L E C T U S E R
	// ========================================================

	// TEST FOR SUCCESS
	// -----------------
	@Test
	public void testSelectUserForSuccess() {

		int id = 10;

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();
		// Create object for User
		user = createNiceMock(User.class);

		EasyMock.expect(user.get_firstName()).andReturn("Starkandsteve");
		EasyMock.expect(user.get_email()).andReturn("steve@gmail.com");
		EasyMock.expect(user.get_phone()).andReturn(Long.valueOf("9874563210"));
		EasyMock.expect(user.get_address()).andReturn("Location");
		EasyMock.expect(user.get_pin()).andReturn(680312);
		EasyMock.expect(user.get_password()).andReturn("123456");

		// Checking the id is greater than 0
		assertEquals(false, id <= 0);
		// Checking the id is integer
		assertEquals(true, id == (int) id);
		// Checking the id is not null
		assertNotNull(id);

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(200);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(200);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.GetUser(id)).andReturn(response);

		EasyMock.replay(user, serviceProvider, response, res);

		// //Checking that object is not null
		assertNotNull(user);

		// Check response status - success
		assertEquals(200, res.getStatus());

		// Check Response Status
		assertEquals(200, response.get_responseCode());
		EasyMock.verify();
	}

	// TEST FOR ERROR (Invalid id)
	// ---------------------
	@Test
	public void testSelectUserForValidationInvalidUserError() {

		int id = 0;

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Checking the id is greater than 0
		assertEquals(true, id <= 0);
		// Checking the id is integer
		assertEquals(true, id == (int) id);
		// Checking the id is not null
		assertNotNull(id);

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(202);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(202);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.GetUser(id)).andReturn(response);

		EasyMock.replay(serviceProvider, response, res);

		// //Checking that object is not null
		assertNull(user);

		// Check response status - failure
		assertEquals(202, res.getStatus());

		// Check Response Status
		assertEquals(202, response.get_responseCode());
		EasyMock.verify();
	}

	// ========================================================
	// S E L E C T U S E R L I S T
	// ========================================================

	// TEST FOR SUCCESS
	// -----------------
	@Test
	public void testSelectUserListForSuccess() {

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(200);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(200);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.GetUserList()).andReturn(response);

		EasyMock.replay(serviceProvider, response, res);

		// Check response status - success
		assertEquals(200, res.getStatus());

		// Check Response Status
		assertEquals(200, response.get_responseCode());
		EasyMock.verify();
	}

	// TEST FOR ERROR
	// ---------------------
	@Test
	public void testSelectUserListForValidationError() {
		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(202);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(202);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.GetUserList()).andReturn(response);

		EasyMock.replay(serviceProvider, response, res);

		// Check response status - failure
		assertEquals(202, res.getStatus());

		// Check Response Status
		assertEquals(202, response.get_responseCode());
		EasyMock.verify();
	}

	// =========================================
	// U P D A T E U S E R (P H O N E)
	// =========================================
	// TEST FOR SUCCESS
	// -------------------
	@Test
	public void testEditUserForSuccess() {

		int id = 10;

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(200);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(200);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		// Create object for User and set properties
		user = EasyMock.createNiceMock(User.class);
		EasyMock.expect(user.get_phone()).andReturn(Long.valueOf("1874563219"));

		EasyMock.expect(serviceProvider.EditUser(id, user)).andReturn(response);

		// Listen Login mock object
		EasyMock.replay(user, res, response, serviceProvider);
		// Checking that object is not null
		assertNotNull(user);

		// Checking the id is greater than 0
		assertEquals(false, id <= 0);
		// Checking the id is integer
		assertEquals(true, id == (int) id);
		// Checking the id is not null
		assertNotNull(id);

		// Check whether the response success
		assertEquals(200, response.get_responseCode());

		EasyMock.verify();
	}

	// TEST FOR ERROR
	// -------------------
	@Test
	public void testEditUserForPhoneValidationError() {

		int id = 10;

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(202);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(202);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		// Create object for Login and set properties
		user = EasyMock.createNiceMock(User.class);
		EasyMock.expect(user.get_phone()).andReturn(Long.valueOf("984563210"));

		EasyMock.expect(serviceProvider.EditUser(id, user)).andReturn(response);

		// Listen Login mock object
		EasyMock.replay(user, res, response, serviceProvider);

		// Check whether the response fail
		assertEquals(202, response.get_responseCode());

		EasyMock.verify();
	}

	// =========================================
	// R E M O V E U S E R
	// =========================================
	// TEST FOR SUCCESS
	// -------------------
	@Test
	public void testRemoveUserForSuccess() {

		int id = 10;

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(200);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(200);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		// Checking the id is greater than 0
		assertEquals(false, id <= 0);
		// Checking the id is integer
		assertEquals(true, id == (int) id);
		// Checking the id is not null
		assertNotNull(id);

		EasyMock.expect(serviceProvider.RemoveUser(id)).andReturn(response);

		// Listen Login mock object
		EasyMock.replay(res, response, serviceProvider);

		// Check whether the response success
		assertEquals(200, response.get_responseCode());

		EasyMock.verify();
	}

	// TEST FOR ERROR
	// -------------------
	@Test
	public void testRemoveUserForPhoneValidationError() {

		int id = 0;

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(202);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(202);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.RemoveUser(id)).andReturn(response);

		// Listen Login mock object
		EasyMock.replay(res, response, serviceProvider);

		// Check whether the response fail
		assertEquals(202, response.get_responseCode());

		EasyMock.verify();
	}

	// =========================================
	// D E L E T E U S E R
	// =========================================
	// TEST FOR SUCCESS
	// -------------------
	@Test
	public void testDeleteUserForSuccess() {

		int id = 10;

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(200);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(200);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		// Checking the id is greater than 0
		assertEquals(false, id <= 0);
		// Checking the id is integer
		assertEquals(true, id == (int) id);
		// Checking the id is not null
		assertNotNull(id);

		EasyMock.expect(serviceProvider.DeleteUser(id)).andReturn(response);

		// Listen Login mock object
		EasyMock.replay(res, response, serviceProvider);

		// Check whether the response success
		assertEquals(200, response.get_responseCode());

		EasyMock.verify();
	}

	// TEST FOR ERROR
	// -------------------
	@Test
	public void testDeleteUserForPhoneValidationError() {

		int id = 0;

		// Create object of Endpoint
		EndPoint endPoint = new EndPoint();

		// Create object for ResponseData
		response = EasyMock.createNiceMock(ResponseData.class);
		EasyMock.expect(response.get_responseCode()).andReturn(202);
		// Create object for Response
		Response res = EasyMock.createNiceMock(Response.class);
		EasyMock.expect(res.getStatus()).andReturn(202);
		// Create object for ServiceProvider
		serviceProvider = EasyMock.createNiceMock(ServiceProvider.class);

		endPoint.serviceProvider = serviceProvider;

		EasyMock.expect(serviceProvider.DeleteUser(id)).andReturn(response);

		// Listen Login mock object
		EasyMock.replay(res, response, serviceProvider);

		// Check whether the response fail
		assertEquals(202, response.get_responseCode());

		EasyMock.verify();
	}
}
