package com.checkout.api.services.payouts.response;


import java.util.List;

public class Payout {
	public String id;
	public String destination;
	public String customerId;
	public String currency;
	public int value;
	public String responseCode;
	public String responseSummary;
	public String responseDetails;
	public String authCode;
	public String status;


	public String eventId;
    public String errorCode;
    public String message;
    public List<String> errorMessageCodes;
    public List<String> errors;


}
