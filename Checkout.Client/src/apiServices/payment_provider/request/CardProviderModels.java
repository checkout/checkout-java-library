package apiServices.payment_provider.request;

import java.util.ArrayList;

public class CardProviderModels {
	public String id;
	public ArrayList<String> errorList = new ArrayList<String>();

	public ArrayList<String> ValidateProperty() {
		if (id == null || id == "") {
			errorList.add("The payment provider id is required");
		}
		return errorList;
	}
}
