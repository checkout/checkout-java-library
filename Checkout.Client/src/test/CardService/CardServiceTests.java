package test.CardService;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import apiServices.cards.CardService;
import apiServices.cards.request.CardCreate;
import apiServices.cards.request.CardDelete;
import apiServices.cards.request.CardGet;
import apiServices.cards.request.CardListGet;
import apiServices.cards.request.CardUpdate;
import apiServices.cards.response.Card;
import apiServices.cards.response.CardList;
import apiServices.customers.CustomerService;
import apiServices.customers.response.CustomerList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;
import exception.CKOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CardServiceTests {
	static String cust_id="";
	static String card_id="";
	
	
	@BeforeClass
	public static void setUp() throws Exception {		
		Response<CustomerList> customerResponse = null;
		CustomerService api = new CustomerService();
		customerResponse = api.getCustomerList();
		
		cust_id=customerResponse.Model().data.get(0).id;
		
	}

	@Test
	public void CreateCardTest() throws CKOException{
		
		Response<CardResponseModel> cardResponse = null;
		CardCreate cardsPayload=new CardCreate();
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";
		cardsPayload.customerId=cust_id;
		
		CardService api=new CardService();
		cardResponse=api.createCard(cardsPayload);
		card_id=cardResponse.Model().id;
		
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
		assertEquals(cardsPayload.card.name,cardResponse.Model().name);
		assertEquals(cardsPayload.card.expiryMonth,cardResponse.Model().expiryMonth);
		assertEquals(cardsPayload.card.expiryYear,cardResponse.Model().expiryYear);
		assertEquals("9996",cardResponse.Model().last4);		
	}
	
	
	@Test
	public void UpdateCardTest() throws CKOException{
		Response<CardResponseModel> cardResponse = null;
		CardUpdate cardsPayload =new CardUpdate();
		cardsPayload.cardId=card_id;
		cardsPayload.customerId=cust_id;
		
		
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="zwpsds2";
		
		CardService api=new CardService();
		cardResponse=api.updateCard(cardsPayload);
		
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
		assertEquals(cardContent.name,cardResponse.Model().name);
	}
	
	
	@Test
	public void GetCardTest()throws CKOException{
		Response<CardResponseModel> cardResponse = null;
		CardGet cardsPayload =new CardGet();
		cardsPayload.customerId=cust_id;
		cardsPayload.cardId=card_id;
		CardService api=new CardService();
		cardResponse=api.getCard(cardsPayload);
		
		card_id=cardResponse.Model().id;
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
		assertEquals("06",cardResponse.Model().expiryMonth);
	}
	
	@Test
	public void GetCardListTest()throws CKOException{
		Response<CardList> cardResponse = null;
		CardListGet cardsPayload =new CardListGet();
		cardsPayload.customerId=cust_id;
		CardService api=new CardService();
		cardResponse=api.getCardList(cardsPayload);
		
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
				
	}
	
	//@Test
	public void DeleteCardTest() throws CKOException{
		
		Response<DeleteResponse> cardResponse = null;	
		CardDelete cardsPayload=new CardDelete();
		cardsPayload.customerId=cust_id;
		cardsPayload.cardId=card_id;
		CardService api=new CardService();
		cardResponse=api.deleteCard(cardsPayload);
		
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
		assertEquals(true, cardResponse.Model().deleted);
		
	}
	
}
