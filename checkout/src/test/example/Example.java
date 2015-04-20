package test.example;

import checkout.Checkout;
import checkout.exception.CKOException;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;

public class Example {

	public static void main(String[] args){

		Checkout ckoClient= new Checkout("sk_CC937715-4F68-4306-BCBE-640B249A4D50");
		
		String paymentToken ="pay_tok_7a66140a-ffc9-48a7-80c3-6e1b70e8076d";	// enter the payment token for the charge to be verified.
		
		Response<Charge> chargeResponse=null;
		
		 try {
			 
			 chargeResponse = ckoClient.chargeService.verifyCharge(paymentToken);
			 
		 } catch (CKOException e) {
	            e.printStackTrace();
		 }
		 
		if(!chargeResponse.hasError){
			String paymentTokenId = chargeResponse.model.id; //payment token id retrieved from the response model
			//...
		}else{
			// Api has returned an error. You can access the error details with the error property on the response object.
			// chargeResponse.error
		}
		
	}
}
