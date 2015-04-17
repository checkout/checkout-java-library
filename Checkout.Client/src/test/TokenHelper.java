package test;

import apiServices.cards.response.Card;
import apiServices.sharedModels.Response;
import apiServices.tokens.TokenService;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;
import exception.CKOException;

public class TokenHelper {

	public static Response<PaymentToken> CreateSessionToken() throws CKOException{	
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		
		tokenPayload.value=100;
		tokenPayload.currency="GBP";
		
		TokenService api = new TokenService();
				
		return api.createPaymentToken(tokenPayload);
	}
	
	
	public static Response<CardToken> CreateCardToken() throws CKOException{
		CardTokenCreate tokenPayload = new CardTokenCreate();
		

		Card cardContent = new Card();
		tokenPayload.card = cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";

		TokenService api = new TokenService();
		return api.createCardToken(tokenPayload);
	}
	
	
}
