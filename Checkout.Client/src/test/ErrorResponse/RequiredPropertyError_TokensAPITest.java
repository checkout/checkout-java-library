package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.cards.response.Card;
import apiServices.tokens.TokenService;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.request.PaymentTokenCreate;
import exception.CKOException;
import exception.ValidationFailException;

public class RequiredPropertyError_TokensAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateCardTokenRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;
		CardTokenCreate cardsPayload = new CardTokenCreate();
		Card cardContent = new Card();
		cardsPayload.card = cardContent;
		TokenService api = new TokenService();
		
		try {
			api.createCardToken(cardsPayload);
		} catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		
		assertEquals(true, hasPropertyError);
		assertEquals(true, cardsPayload.errorList.contains("type is required"));		
		assertEquals(true, cardsPayload.errorList.contains("card number is required"));
		assertEquals(true, cardsPayload.errorList.contains("expiryMonth is required"));
		assertEquals(true, cardsPayload.errorList.contains("expiryYear is required"));
		assertEquals(true, cardsPayload.errorList.contains("cvv is required"));		
	}

	@Test
	public void testCreateSessionTokenRequest_RequiredPropertyError() throws CKOException {
		boolean hasPropertyError = false;
		PaymentTokenCreate cardsPayload=new PaymentTokenCreate();
		TokenService api = new TokenService();
		try {
			api.createPaymentToken(cardsPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true, cardsPayload.errorList.contains("type is required"));
		assertEquals(true, cardsPayload.errorList.contains("amount is required"));
		assertEquals(true, cardsPayload.errorList.contains("currency is required"));
		
		
		
	}

}
