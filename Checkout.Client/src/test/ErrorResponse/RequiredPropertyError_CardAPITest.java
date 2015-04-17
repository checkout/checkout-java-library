package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.cards.CardService;
import apiServices.cards.request.CardCreate;
import apiServices.cards.request.CardDelete;
import apiServices.cards.request.CardGet;
import apiServices.cards.request.CardListGet;
import apiServices.cards.request.CardUpdate;
import apiServices.cards.response.Card;
import exception.CKOException;
import exception.ValidationFailException;

public class RequiredPropertyError_CardAPITest {

	@Before
	public void setUp() throws Exception {
	}
	
	
	@Test
	public void CreateCardTest_RequiredPropertyError() throws CKOException{
		boolean hasPropertyError = false;
		CardCreate cardsPayload=new CardCreate();
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		CardService api=new CardService();
		
		try{
		api.createCard(cardsPayload);
		}
		catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true, cardsPayload.errorList.contains("name is required"));
		assertEquals(true, cardsPayload.errorList.contains("card number is required"));
		assertEquals(true, cardsPayload.errorList.contains("expiryMonth is required"));
		assertEquals(true, cardsPayload.errorList.contains("expiryYear is required"));
		assertEquals(true, cardsPayload.errorList.contains("cvv is required"));		
		assertEquals(true, cardsPayload.errorList.contains("customerId is required"));	
		
	}
	
	
	@Test
	public void UpdateCardTest_RequiredPropertyError()throws CKOException{
		boolean hasPropertyError = false;
		
		CardUpdate cardsPayload =new CardUpdate();
		
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		
		CardService api=new CardService();
		try{
		api.updateCard(cardsPayload);
		}
		catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true, cardsPayload.errorList.contains("customerId is required"));	
		assertEquals(true, cardsPayload.errorList.contains("cardId is required"));	
		
	}
	
	
	@Test
	public void GetCardTest_RequiredPropertyError()throws CKOException{
		boolean hasPropertyError = false;
		CardGet cardsPayload =new CardGet();
		
		CardService api=new CardService();
		
		try{
		api.getCard(cardsPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);	
		assertEquals(true, cardsPayload.errorList.contains("customerId is required"));	
		assertEquals(true, cardsPayload.errorList.contains("cardId is required"));	
	}
	
	@Test
	public void GetCardListTest_RequiredPropertyError()throws CKOException{
		boolean hasPropertyError = false;
		CardListGet cardsPayload =new CardListGet();
		
		CardService api=new CardService();
		try{
		api.getCardList(cardsPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);
		assertEquals(true, cardsPayload.errorList.contains("customerId is required"));	
		
	}
	
	@Test
	public void DeleteCardTest_RequiredPropertyError() throws CKOException{
		
		boolean hasPropertyError = false;
		CardDelete cardsPayload=new CardDelete();
		
		CardService api=new CardService();
		try{
		api.deleteCard(cardsPayload);
		}catch(ValidationFailException e){
			
			hasPropertyError = true;
		}
		assertEquals(true, hasPropertyError);	
		assertEquals(true, cardsPayload.errorList.contains("customerId is required"));	
		assertEquals(true, cardsPayload.errorList.contains("cardId is required"));
	}
}
