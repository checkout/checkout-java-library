package apiServices.cards.request;

import java.util.ArrayList;

public class CardGet extends BaseCards{
	
	public ArrayList<String> validateProperty() {

		if (cardId == null || cardId == "") {
			errorList.add("cardId is required");			
		}
		if (customerId == null || customerId == "") {
			errorList.add("customerId is required");			
		}
		return errorList;
	}
}
