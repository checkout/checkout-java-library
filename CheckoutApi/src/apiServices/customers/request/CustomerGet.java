package apiServices.customers.request;

import java.util.ArrayList;

public class CustomerGet extends BaseCustomer{
	
public ArrayList<String> validateProperty() {
		
		if(customerId==null||customerId==""){
			errorList.add("customer id is required");
		}
		return errorList;
	}

}