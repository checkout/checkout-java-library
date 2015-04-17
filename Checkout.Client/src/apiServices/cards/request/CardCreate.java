package apiServices.cards.request;

import java.util.ArrayList;

public class CardCreate extends BaseCards{
	
	public ArrayList<String> ValidateProperty() {

		if (card.name == null || card.name == "") {
			errorList.add("name is required");			
		}
		if (card.number == null || card.number == "") {
			errorList.add("card number is required");			
		}
		if (card.expiryMonth == null || card.expiryMonth == "") {
			errorList.add("expiryMonth is required");			
		}
		if (card.expiryYear == null || card.expiryYear == "") {
			errorList.add("expiryYear is required");			
		}
		if (card.cvv == null || card.cvv == "") {
			errorList.add("cvv is required");			
		}
		if (customerId == null || customerId == "") {
			errorList.add("customerId is required");			
		}
		return errorList;
	}
}
