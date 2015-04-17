package apiServices.charges.request;

import java.util.ArrayList;

public class CardTokenCharge extends BaseCharge{
public String cardToken;


public ArrayList<String> ValidateProperty() {	
	if(email==null&&customerId==null){
		errorList.add("email/customerId is required");	
	}
	if (value == 0) {
		errorList.add("amount is required");			
	}
	if (currency == null || currency == "") {
		errorList.add("currency is required");			
	}
	if (cardToken == null || cardToken == "") {
		errorList.add("cardToken is required");			
	}
	
	return errorList;
	
}
}
