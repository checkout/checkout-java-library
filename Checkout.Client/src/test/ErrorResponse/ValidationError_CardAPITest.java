package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.cards.CardService;
import apiServices.cards.request.CardCreate;
import apiServices.cards.request.CardDelete;
import apiServices.cards.request.CardGet;
import apiServices.cards.request.CardListGet;
import apiServices.cards.response.Card;
import apiServices.cards.response.CardList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class ValidationError_CardAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void CreateCardTest_ValidationError() throws CKOException{	
		Response<CardResponseModel> cardResponse = null;
		CardCreate cardsPayload=new CardCreate();
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="testamde2";
		cardContent.number="4444555555554444";
		cardContent.expiryMonth="08";
		cardContent.expiryYear="2016";
		cardContent.cvv="123";
		cardsPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		
		CardService api=new CardService();
		cardResponse=api.createCard(cardsPayload);
				
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true, cardResponse.error.message.contains("Invalid Card Number"));	
		
	}
	
	
	@Test
	public void GetCardTest_InvalidCustomerError()throws CKOException{
		Response<CardResponseModel> cardResponse = null;
		CardGet cardsPayload =new CardGet();
		cardsPayload.customerId="cust_0c6021c5-f595-49a9-855d-7ddd1a42852";
		cardsPayload.cardId="card_6894fb41-5ec5-4644-9696-0aa9174d4e89";
		CardService api=new CardService();
		cardResponse=api.getCard(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(true,cardResponse.error.errors.contains("Invalid customer id"));
	}
	@Test
	public void GetCardTest_InvalidCardError()throws CKOException{
		Response<CardResponseModel> cardResponse = null;
		CardGet cardsPayload =new CardGet();
		cardsPayload.customerId="cust_4e6510b9-5c55-4581-8d23-d0468e899e28";
		cardsPayload.cardId="card_b1303dffb-2a28-465b-ba8b-a6e82bd17341";
		CardService api=new CardService();
		cardResponse=api.getCard(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true,cardResponse.error.errors.contains("Invalid card id"));
	}
	
	@Test
	public void GetCardListTest_ValidationError()throws CKOException{
		Response<CardList> cardResponse = null;
		CardListGet cardsPayload =new CardListGet();
		cardsPayload.customerId="cust_0c6021c5-f595-49a9-855d-7ddd1a42852";
		CardService api=new CardService();
		cardResponse=api.getCardList(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true,cardResponse.error.errors.contains("Invalid customer id"));
		
	}
	
	@Test
	public void DeleteCardTest_ValidationError() throws CKOException{
		
		Response<DeleteResponse> cardResponse = null;	
		CardDelete cardsPayload=new CardDelete();
		cardsPayload.customerId="cust_4dacf511e-c3d0-41ea-a2b4-113a13b88972";
		cardsPayload.cardId="card_a724de76-a853-4e6c-aa9b-c527002b78f8";
		CardService api=new CardService();
		cardResponse=api.deleteCard(cardsPayload);
		
		assertEquals(true, cardResponse.getHasError());
		assertEquals(false,cardResponse.getHttpStatus()==200);
		assertEquals(true,cardResponse.error.errors.contains("Invalid customer id"));
		
	}

}
