package com.keycloak.model.ELKStack;

import java.util.List;


public class ELKResponseData {

	//===================================
	//		Data Members
	//===================================

	private int _responseCode;
	private String _responseMessage;
	private String _responseData;
	private List<IndexData> _responseList;
	
	
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
	public String get_responseData() {
		return _responseData;
	}

	public void set_responseData(String _responseData) {
		this._responseData = _responseData;
	}


	public List<IndexData> get_responseList() {
		return _responseList;
	}

	public void set_responseList(List<IndexData> _responseList) {
		this._responseList = _responseList;
	}
	//===================================
	//		Default Constructor
	//===================================	
	
	public ELKResponseData() {}

	@Override
	public String toString() {
		return "ResponseData [_responseCode=" + _responseCode + ", _responseMessage=" + _responseMessage
				+ ", _responseData=" + _responseData + ", _responseList=" + _responseList + "]";
	}

}
