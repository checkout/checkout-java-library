package apiServices.tokens.request;

import java.util.ArrayList;

public class PaymentTokenCreate {
	
	public int value = 0;
	public String currency;
	public ArrayList<String> errorList = new ArrayList<String>();

	public ArrayList<String> validateProperty() {
		
		if (value == 0) {
			errorList.add("value is required");
		}
		if (currency == null || currency == "") {
			errorList.add("currency is required");
		}

		return errorList;
	}
}
