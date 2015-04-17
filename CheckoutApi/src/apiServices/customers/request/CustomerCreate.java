package apiServices.customers.request;

import java.util.ArrayList;

import apiServices.cards.response.Card;

public class CustomerCreate extends BaseCustomer {
	public String token;
	public Card card;

	public ArrayList<String> validateProperty() {

		if (email == null || email == "") {
			errorList.add("email is required");

		}
		return errorList;
	}

}
