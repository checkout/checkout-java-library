package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.cards.CardService;
import apiServices.cards.request.CardUpdate;
import apiServices.cards.response.Card;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class NotFoundError_CardAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void UpdateCardTest_NotFoundError() throws CKOException{
		Response<CardResponseModel> cardResponse = null;
		CardUpdate cardsPayload =new CardUpdate();
		
		cardsPayload.customerId="cust_0fc809f9-b412-4180-a00a-6ec0d8422e0c";
		cardsPayload.cardId="card_ec96fc5c-486a-4516-b9bc-e9ad48f7eeb3";
		
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="zwpss2";
		
		CardService api=new CardService();
		cardResponse=api.updateCard(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true, cardResponse.error.message.contains("Customer not found"));		
		
	}
	
	

}
