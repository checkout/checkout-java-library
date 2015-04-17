package apiServices.charges.request;

import java.util.ArrayList;

public class LocalPaymentCharge extends BaseCharge{
public String sessionToken;
public LocalPaymentRequestModel localPayment;
public boolean capture;

public ArrayList<String> ValidateProperty() {
	
	if(email==null||email==null){
		errorList.add("email is required");	
	}
	if (sessionToken == null || sessionToken == "") {
		errorList.add("sessionToken is required");			
	}
	if (localPayment.lppId == null || localPayment.lppId == "") {
		errorList.add("localPayment IppId is required");			
	}
	return errorList;
	
}
}
