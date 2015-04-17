package apiServices.sharedModels;

import com.google.gson.annotations.SerializedName;

public class CardResponseModel {

	@SerializedName("Id")
	public String id;
	
	@SerializedName("Last4")
	public String last4;
	
	@SerializedName("PaymentMethod")
	public String paymentMethod;
	
	public String fingerprint;
	public String customerId;
	
	@SerializedName("Name")
	public String name;
	
	public String expiryMonth;
	public String expiryYear;
	
	@SerializedName("BillingDetails")
	public Address billingDetails;
	
	@SerializedName("CvvCheck")
	public String cvcCheck;
	
	@SerializedName("AvsCheck")
	public String avsCheck;
	
	@SerializedName("ResponseCode")
	public String responseCode;
}
