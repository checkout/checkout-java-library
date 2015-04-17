package test.TokenService;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import apiServices.cards.response.Card;
import apiServices.sharedModels.Response;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;


public class TokenServiceTests {
	static CheckoutClient ckoClient;
	@BeforeClass
	public static void setUp() throws Exception {
		ckoClient= new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
	}

	@Test
	public void createCardTokenTest() throws JsonSyntaxException, IOException {
		
		CardTokenCreate tokenPayload = new CardTokenCreate();
	
		Card cardContent = new Card();
		tokenPayload.card = cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";
		
		Response<CardToken> tokenResponse = ckoClient.TokenService.createCardToken(tokenPayload);

		assertEquals(false, tokenResponse.getHasError());
		assertEquals(200, tokenResponse.getHttpStatus());
		assertEquals(tokenPayload.card.name,
				tokenResponse.getType().card.name);
		assertEquals(tokenPayload.card.expiryMonth,
				tokenResponse.getType().card.expiryMonth);
		assertEquals(tokenPayload.card.expiryYear,tokenResponse.getType().card.expiryYear);
		
	}

	@Test
	public void createSessionTokenTest() throws JsonSyntaxException, IOException {
		Response<PaymentToken> tokenResponse= null;
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		
		tokenPayload.value=100;
		tokenPayload.currency="GBP";
				
		tokenResponse = ckoClient.TokenService.createPaymentToken(tokenPayload);
		assertEquals(false, tokenResponse.getHasError());
		assertEquals(200, tokenResponse.getHttpStatus());
		assertEquals(tokenPayload.value,tokenResponse.getType().value);
		assertEquals(tokenPayload.currency,tokenResponse.getType().currency);
		
		
	}

}
