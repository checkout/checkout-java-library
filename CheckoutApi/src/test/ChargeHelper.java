package test;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import apiServices.cards.response.Card;
import apiServices.charges.request.CardCharge;
import apiServices.charges.response.Charge;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;


public class ChargeHelper {

	static String customer_id = "";
	static CheckoutClient ckoClient;
	
	public static Response<Charge> chargeWithFullCardRequest() throws JsonSyntaxException, IOException{

		CardCharge chargesPayload = new CardCharge();
		Response<Customer> customerResponse = CustomerHelper.createCustomerRequestTest();
		ckoClient= new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
		
		customer_id = customerResponse.getType().id;
		chargesPayload.customerId = customer_id;
		chargesPayload.currency = "usd";
		chargesPayload.value = 100;
		Card cardContent = new Card();
		chargesPayload.card = cardContent;
		cardContent.name="zwp";
		cardContent.number="5313581000123430";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="257";

		return ckoClient.ChargeService.chargeWithCard(chargesPayload);

	}
}
