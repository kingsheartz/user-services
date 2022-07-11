package com.keycloak.model;

import java.util.Arrays;
import java.util.List;

public class ResponseData {

	//===================================
	//		Data Members
	//===================================

	private int _responseCode;
	private String _responseMessage;
	private User _responseObject;
	private User[] _responseObjectArray;
	private List<User> _responseList;
	
	
	//===================================
	//		Properties
	//===================================	
	


	public int get_responseCode() {
		return _responseCode;
	}

	public void set_responseCode(int _responseCode) {
		this._responseCode = _responseCode;
	}

	public String get_responseMessage() {
		return _responseMessage;
	}

	public void set_responseMessage(String _responseMessage) {
		this._responseMessage = _responseMessage;
	}

	public User get_responseObject() {
		return _responseObject;
	}

	public void set_responseObject(User _responseObject) {
		this._responseObject = _responseObject;
	}
	
	public User[] get_responseObjectArray() {
		return _responseObjectArray;
	}

	public void set_responseObjectArray(User[] _responseObjectArray) {
		this._responseObjectArray = _responseObjectArray;
	}

	public List<User> get_responseList() {
		return _responseList;
	}

	public void set_responseList(List<User> _responseList) {
		this._responseList = _responseList;
	}
	//===================================
	//		Default Constructor
	//===================================	
	
	public ResponseData() {}

	@Override
	public String toString() {
		return "ResponseData [_responseCode=" + _responseCode + ", _responseMessage=" + _responseMessage
				+ ", _responseObject=" + _responseObject + ", _responseObjectArray="
				+ Arrays.toString(_responseObjectArray) + ", _responseList=" + _responseList + "]";
	}

}
