package apiServices.payment_provider.request;

import java.util.ArrayList;

public class LocalPaymentProviderModel {
public String id;
public String sessionToken;
public ArrayList<String> errorList=new ArrayList<String>();

public ArrayList<String> validateProperty() {
	if (id==null||id==""){
		errorList.add("The payment provider id is required");
	}
	if(sessionToken==null||sessionToken==""){
		errorList.add("The session token is required");
		}
	return errorList;
	}
}
