package apiServices.customers.request;

import java.util.ArrayList;

public class CustomerGet extends BaseCustomer{
	
public ArrayList<String> ValidateProperty() {
		
		if(customerId==null||customerId==""){
			errorList.add("customer id is required");
		}
		return errorList;
	}

}
