package apiServices.cards.request;

import java.util.ArrayList;

public class CardListGet extends BaseCards {
	
	public ArrayList<String> validateProperty() {
		if (customerId == null || customerId == "") {
			errorList.add("customerId is required");			
		}
		return errorList;
	}
}
