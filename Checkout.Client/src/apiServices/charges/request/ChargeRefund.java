package apiServices.charges.request;

import java.util.ArrayList;

public class ChargeRefund extends BaseCharge{
	public String chargeId;


	public ArrayList<String> ValidateProperty() {	
		if(chargeId==null||chargeId==null){
			errorList.add("chargeId is required");	
		}
		return errorList;
		
	}
}
