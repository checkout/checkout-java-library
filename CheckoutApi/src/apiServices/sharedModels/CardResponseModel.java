package apiServices.sharedModels;

import apiServices.cards.response.BillingDetails;

public class CardResponseModel {
	public String object;
	public String id;
	public String last4;
	public String paymentMethod;
	public String fingerprint;
	public String customerId;
	public String name;
	public String expiryMonth;
	public String expiryYear;
	public BillingDetails billingDetails;
	public String cvcCheck;
	public String avsCheck;
	public String responseCode;
	public String authCode;
	public boolean defaultCard;
}
