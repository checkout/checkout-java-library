package test.ErrorResponse;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import apiServices.cards.request.CardUpdate;
import apiServices.cards.response.Card;
import apiServices.charges.request.CardCharge;
import apiServices.charges.response.Charge;
import apiServices.customers.request.CustomerDelete;
import apiServices.customers.request.CustomerUpdate;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;


public class NotFoundError {
	static CheckoutClient ckoClient;
	
	@BeforeClass
	public static void setUp() throws Exception {
		ckoClient= new CheckoutClient("sk_test_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_test_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
	}

	@Test
	public void updateCardTest_NotFoundError() throws JsonSyntaxException, IOException {
		Response<CardResponseModel> cardResponse = null;
		CardUpdate cardsPayload =new CardUpdate();
		
		cardsPayload.customerId="cust_0fc809f9-b412-4180-a00a-6ec0d8422e0c";
		cardsPayload.cardId="card_ec96fc5c-486a-4516-b9bc-e9ad48f7eeb3";
		
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="zwpss2";
		
		
		cardResponse=ckoClient.CardService.updateCard(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true, cardResponse.error.message.contains("Customer not found"));		
		
	}
	
	@Test
	public void testChargeWithFullCardRequest() throws JsonSyntaxException, IOException {		
		Response<Charge> chargesResponse=null;
		CardCharge chargesPayload =new CardCharge();
		
		chargesPayload.customerId="cust_fb3ab486-49c5-4e0c-bcc1-8299c2309cf3";
		chargesPayload.currency="usd";
		chargesPayload.value=100;
		Card cardContent=new Card();
		chargesPayload.card=cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";

		
		chargesResponse=ckoClient.ChargeService.chargeWithCard(chargesPayload);
		
		assertEquals(true, chargesResponse.getHasError());
		assertEquals(false,chargesResponse.getHttpStatus()==200);
		assertEquals(true, chargesResponse.error.message.contains("Customer not found"));
		
	}
	
	@Test
	public void updateCustomerRequestTest_notFoundError() throws JsonSyntaxException, IOException  {
		CustomerUpdate customerPayload = new CustomerUpdate();
		customerPayload.customerId="cust_e09aada7-76f9-419a-884a-2ad7e5265271";

		Response<Customer> customerResponse = ckoClient.CustomerService.updateCustomer(customerPayload);

		assertEquals(true, customerResponse.getHasError());
		assertEquals(true, customerResponse.error.message.contains("Customer not found"));
	}
	
	@Test
	public void deleteCustomerRequestTest_notFoundError() throws JsonSyntaxException, IOException  {
		CustomerDelete customerPayload = new CustomerDelete();
		customerPayload.customerId="cust_e09aada7-76f9-419a-884a-2ad7e5265271";

		Response<DeleteResponse> customerResponse = null;

		customerResponse = ckoClient.CustomerService.deleteCustomer(customerPayload);

		assertEquals(true, customerResponse.getHasError());
		assertEquals(true, customerResponse.error.message.contains("Customer not found"));

	}

}
