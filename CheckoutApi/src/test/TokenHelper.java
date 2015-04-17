package test;

import java.io.IOException;
import com.google.gson.JsonSyntaxException;
import controllers.CheckoutClient;
import apiServices.cards.response.Card;
import apiServices.sharedModels.Response;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;


public class TokenHelper {
	static CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");;

	public static Response<PaymentToken> createSessionToken() throws JsonSyntaxException, IOException{	
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		
		tokenPayload.value=100;
		tokenPayload.currency="GBP";
				
		return ckoClient.TokenService.createPaymentToken(tokenPayload);
	}
	
	
	public static Response<CardToken> createCardToken() throws JsonSyntaxException, IOException{
		CardTokenCreate tokenPayload = new CardTokenCreate();
		

		Card cardContent = new Card();
		tokenPayload.card = cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";

		return ckoClient.TokenService.createCardToken(tokenPayload);
	}
	
	
}
