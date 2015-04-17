package test.TokenService;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.sharedModels.Response;
import apiServices.tokens.TokenService;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.PaymentToken;
import test.TestHelper;
import exception.CKOException;

public class TokenServiceTests {
	
	@Before
	public void setUp() throws Exception {
	}

	/*
	@Test
	public void CreateCardTokenTest() throws CKOException {
		Response<CardToken> tokenResponse = null;
		CardTokenCreate tokenPayload = new CardTokenCreate();
	
		Card cardContent = new Card();
		tokenPayload.card = cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";

		TokenService api = new TokenService();
		tokenResponse = api.createCardToken(tokenPayload);

		assertEquals(false, tokenResponse.getHasError());
		assertEquals(200, tokenResponse.getHttpStatus());
		assertEquals(tokenPayload.card.name,
				tokenResponse.getType().card.name);
		assertEquals(tokenPayload.card.expiryMonth,
				tokenResponse.getType().card.expiryMonth);
		assertEquals(tokenPayload.card.expiryYear,tokenResponse.getType().card.expiryYear);
		System.out.println(tokenResponse.getType().id);
	}
*/
	
	@Test
	public void CreatePaymentTokenTest() throws CKOException {
		
		TokenService tokenService = new TokenService();
		
		PaymentTokenCreate payload= TestHelper.getPaymentTokenCreateModel();
		
		payload.shippingDetails.phone.countryCode=null;
		
		Response<PaymentToken> tokenResponse = tokenService.createPaymentToken(payload);
		
		assertEquals(false, tokenResponse.hasError);
		assertEquals(200, tokenResponse.httpStatus);
		assertNotNull(tokenResponse.model.id);
		
	}

}
