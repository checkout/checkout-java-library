package test.cardservice;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import test.TestHelper;

import com.checkout.APIClient;
import com.checkout.api.services.card.request.CardCreate;
import com.checkout.api.services.card.response.Card;
import com.checkout.api.services.card.response.CardList;
import com.checkout.api.services.customer.request.CustomerCreate;
import com.checkout.api.services.customer.response.Customer;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.google.gson.JsonSyntaxException;

public class CardServiceTests {
	
	APIClient ckoClient;
	
	String customerId;
	
	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient(TestHelper.secretKey,true);
		
		//Create customer without card
		CustomerCreate customerPayload = TestHelper.getCustomerCreateModel();
		customerPayload.card=null;
		Response<Customer> customerResponse =  ckoClient.customerService.createCustomer(customerPayload);
		customerId = customerResponse.model.id;
	}

	@Test
	public void createCardTest() throws JsonSyntaxException, IOException{
		
		CardCreate payload= TestHelper.getCardCreateModel();
		
		Response<Card> cardResponse = ckoClient.cardService.createCard(customerId, payload);
		
		assertEquals(false, cardResponse.hasError);
		assertEquals(200, cardResponse.httpStatus);
		assertNotNull(cardResponse.model.responseCode);
		ValidateCard(payload, cardResponse.model);
	}
	
	@Test
	public void updateCardTest() throws JsonSyntaxException, IOException {

		Response<Card> cardCreateResponse = ckoClient.cardService.createCard(customerId, TestHelper.getCardCreateModel());
					
		Response<OkResponse> cardResponse = ckoClient.cardService.updateCard(customerId, cardCreateResponse.model.id, TestHelper.getCardUpdateModel());
		
		assertEquals(false, cardResponse.hasError);
		assertEquals(200, cardResponse.httpStatus);
		assertEquals(cardResponse.model.message,"ok");
	}
		
	@Test
	public void getCardTest() throws JsonSyntaxException, IOException{
		CardCreate cardCreatePayload= TestHelper.getCardCreateModel();
		Response<Card> cardCreateResponse = ckoClient.cardService.createCard(customerId, cardCreatePayload);
		
		Response<Card> cardResponse =ckoClient.cardService.getCard(customerId, cardCreateResponse.model.id);
		
		assertEquals(false, cardResponse.hasError);
		assertEquals(200, cardResponse.httpStatus);
		ValidateCard(cardCreatePayload, cardResponse.model);
	}

	
	@Test
	public void getCardListTest() throws JsonSyntaxException, IOException{
		CardCreate cardCreatePayload= TestHelper.getCardCreateModel();
		Response<Card> cardCreateResponse = ckoClient.cardService.createCard(customerId, cardCreatePayload);
		
		Response<CardList> cardListResponse = ckoClient.cardService.getCardList(customerId);
		Card cardResponse =  cardListResponse.model.data.get(0);
				
		assertEquals(false, cardListResponse.hasError);
		assertEquals(200, cardListResponse.httpStatus);
		assertEquals(cardListResponse.model.count,1);
		assertEquals(cardCreateResponse.model.id, cardResponse.id);
		ValidateCard(cardCreatePayload, cardResponse);	
	}
	
	@Test
	public void deleteCardTest() throws JsonSyntaxException, IOException{
		
		CardCreate cardCreatePayload= TestHelper.getCardCreateModel();
		Response<Card> cardCreateResponse = ckoClient.cardService.createCard(customerId, cardCreatePayload);
		
		Response<OkResponse> cardResponse =ckoClient.cardService.deleteCard(customerId, cardCreateResponse.model.id);
		
		assertEquals(false, cardResponse.hasError);
		assertEquals(200, cardResponse.httpStatus);
		assertEquals(cardResponse.model.message,"ok");
	}

	
	private void ValidateCard(CardCreate payload, Card cardResponse) {
		assertNotNull(cardResponse.id);
		assertTrue(payload.number.endsWith(cardResponse.last4));
		assertEquals(customerId,cardResponse.customerId);
		assertEquals(payload.name,cardResponse.name);
		assertEquals(payload.expiryMonth,cardResponse.expiryMonth);
		assertEquals(payload.expiryYear,cardResponse.expiryYear);
		assertEquals(payload.billingDetails.addressLine1, cardResponse.billingDetails.addressLine1);
		assertEquals(payload.billingDetails.addressLine2, cardResponse.billingDetails.addressLine2);
		assertEquals(payload.billingDetails.city, cardResponse.billingDetails.city);
		assertEquals(payload.billingDetails.country, cardResponse.billingDetails.country);
		assertEquals(payload.billingDetails.phone.countryCode, cardResponse.billingDetails.phone.countryCode);
		assertEquals(payload.billingDetails.phone.number, cardResponse.billingDetails.phone.number);
		assertEquals(payload.billingDetails.postcode, cardResponse.billingDetails.postcode);
		assertEquals(payload.billingDetails.state, cardResponse.billingDetails.state);
		assertEquals(cardResponse.paymentMethod.toLowerCase(),"visa");
		assertNotNull(cardResponse.fingerprint);
	}
	
}
