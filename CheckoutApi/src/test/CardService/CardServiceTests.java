package test.CardService;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.google.gson.JsonSyntaxException;
import controllers.CheckoutClient;
import apiServices.cards.request.CardCreate;
import apiServices.cards.request.CardDelete;
import apiServices.cards.request.CardGet;
import apiServices.cards.request.CardListGet;
import apiServices.cards.request.CardUpdate;
import apiServices.cards.response.Card;
import apiServices.cards.response.CardList;
import apiServices.customers.response.CustomerList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CardServiceTests {
	static String cust_id="";
	static String card_id="";
	static CheckoutClient ckoClient;
	
	@BeforeClass
	public static void setUp() throws Exception {		
		
		Response<CustomerList> customerResponse = null;		
		ckoClient= new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
		customerResponse =ckoClient.CustomerService.getCustomerList();		
		cust_id=customerResponse.getType().data.get(0).id;
		
	}

	@Test
	public void createCardTest() throws JsonSyntaxException, IOException{
		
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
			
		cardResponse=ckoClient.CardService.createCard(cardsPayload);
		card_id=cardResponse.getType().id;
		
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
		assertEquals(cardsPayload.customerId,cardResponse.getType().customerId);
		assertEquals(cardsPayload.card.expiryMonth,cardResponse.getType().expiryMonth);
		assertEquals(cardsPayload.card.expiryYear,cardResponse.getType().expiryYear);
		assertEquals("9996",cardResponse.getType().last4);		
	}
	
	
	@Test
	public void updateCardTest() throws JsonSyntaxException, IOException {
		Response<CardResponseModel> cardResponse = null;
		CardUpdate cardsPayload =new CardUpdate();
		cardsPayload.cardId=card_id;
		cardsPayload.customerId=cust_id;				
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="zwpsds2";
				
		cardResponse=ckoClient.CardService.updateCard(cardsPayload);
		
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
		assertEquals(cardContent.name,cardResponse.getType().name);
	}
	
	
	@Test
	public void getCardTest() throws JsonSyntaxException, IOException{
		Response<CardResponseModel> cardResponse = null;
		CardGet cardsPayload =new CardGet();
		cardsPayload.customerId=cust_id;
		cardsPayload.cardId=card_id;
		
		cardResponse=ckoClient.CardService.getCard(cardsPayload);
		
		card_id=cardResponse.getType().id;
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
		assertEquals("06",cardResponse.getType().expiryMonth);
	}
	
	@Test
	public void getCardListTest() throws JsonSyntaxException, IOException{
		Response<CardList> cardResponse = null;
		CardListGet cardsPayload =new CardListGet();
		cardsPayload.customerId=cust_id;
		
		cardResponse=ckoClient.CardService.getCardList(cardsPayload);
		
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
				
	}
	
	//@Test
	public void deleteCardTest() throws JsonSyntaxException, IOException{
		
		Response<DeleteResponse> cardResponse = null;	
		CardDelete cardsPayload=new CardDelete();
		cardsPayload.customerId=cust_id;
		cardsPayload.cardId=card_id;
		
		cardResponse=ckoClient.CardService.deleteCard(cardsPayload);
		
		assertEquals(false, cardResponse.getHasError());
		assertEquals(200, cardResponse.getHttpStatus());
		assertEquals(true, cardResponse.getType().deleted);
		
	}
	
}
