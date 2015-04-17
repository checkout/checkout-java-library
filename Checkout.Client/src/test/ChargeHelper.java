package test;

import apiServices.cards.response.Card;
import apiServices.charges.ChargeService;
import apiServices.charges.request.CardCharge;
import apiServices.charges.response.Charge;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class ChargeHelper {

	static String customer_id = "";

	public static Response<Charge> ChargeWithFullCardRequest()
			throws CKOException {

		CardCharge chargesPayload = new CardCharge();
		Response<Customer> customerResponse = CustomerHelper
				.CreateCustomerRequestTest();

		customer_id = customerResponse.Model().id;
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

		ChargeService api = new ChargeService();
		return api.chargeWithCard(chargesPayload);

	}
}
