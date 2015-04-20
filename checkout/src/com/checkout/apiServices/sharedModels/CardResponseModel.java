package com.checkout.apiServices.sharedModels;

public class CardResponseModel {

	public String id;
	public String last4;
	public String paymentMethod;
	public String fingerprint;
	public String customerId;
	public String name;
	public String expiryMonth;
	public String expiryYear;
	public Address billingDetails;
	public String cvcCheck;
	public String avsCheck;
	public String responseCode;
}
