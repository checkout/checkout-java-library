package apiServices.tokens.request;

import java.util.ArrayList;

import apiServices.cards.response.Card;

public class CardTokenCreate {
	
	public Card card;
	public ArrayList<String> errorList=new ArrayList<String>();
	
	public ArrayList<String> ValidateProperty() {		
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
		
		return errorList;
	}
}
