package apiServices.charges.request;

import java.util.ArrayList;

public class CardIdCharge extends BaseCharge {
	public String cardId;

	public ArrayList<String> ValidateProperty() {
		if (email == null && customerId == null) {
			errorList.add("email/customerId is required");
		}
		if (value == 0) {
			errorList.add("amount is required");
		}
		if (currency == null || currency == "") {
			errorList.add("currency is required");
		}
		if (cardId == null || cardId == "") {
			errorList.add("cardId is required");
		}

		return errorList;

	}
}
