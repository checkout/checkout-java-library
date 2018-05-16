package com.checkout.api.services.shared;

import java.util.List;

public class ResponseError {

	public String errorCode;
	public String message;
	public String eventId;
	public List<String> errors;
	public List<String> errorMessageCodes;
}
