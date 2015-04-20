package com.checkout.apiServices.sharedModels;

import java.util.List;

public class ResponseError {

	public String errorCode;
	public String message;
	public String eventId;
	public List<String> errors;
}
