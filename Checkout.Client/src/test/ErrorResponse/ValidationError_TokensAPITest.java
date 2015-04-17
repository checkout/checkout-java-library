package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.cards.response.Card;
import apiServices.sharedModels.Response;
import apiServices.tokens.TokenService;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;
import exception.CKOException;

public class ValidationError_TokensAPITest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testCreateCardTokenRequest_InvalidCardNumber() throws CKOException {
		Response<CardToken> tokenResponse = null;
		CardTokenCreate tokenPayload = new CardTokenCreate();		

		Card cardContent = new Card();
		tokenPayload.card = cardContent;
		cardContent.name="testamde2";
		cardContent.number="3232555555554444";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2018";
		cardContent.cvv="1000";

		TokenService api = new TokenService();
		tokenResponse = api.createCardToken(tokenPayload);

		assertEquals(true, tokenResponse.getHasError());
		assertEquals(true,tokenResponse.error.message.contains("Invalid Card Number"));
		
	}
	

	@Test
	public void testCreateSessionTokenRequest_InvalidCurrency() throws CKOException {
		Response<PaymentToken> tokenResponse= null;
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		
		tokenPayload.value=6;
		tokenPayload.currency="kde";
		
		TokenService api = new TokenService();
		tokenResponse = api.createPaymentToken(tokenPayload);
		assertEquals(true, tokenResponse.getHasError());
		assertEquals(true,tokenResponse.error.message.contains("Invalid currency"));
	}
	

}
