package apiServices.payment_provider.request;

import java.util.ArrayList;

public class LocalPaymentProviderListModel {
	public String sessionToken;
	public String countryCodes;
	public String ip;
	public String limit;
	public String region;
	public String name;
	
	public ArrayList<String> errorList=new ArrayList<String>();

	public ArrayList<String> validateProperty() {
		if(sessionToken==null||sessionToken==""){
			errorList.add("The session token is required");
			}
		return errorList;
		}
}
