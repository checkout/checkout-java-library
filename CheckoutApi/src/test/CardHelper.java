package test;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import apiServices.cards.request.CardCreate;
import apiServices.cards.response.Card;
import apiServices.customers.response.CustomerList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.Response;

public class CardHelper {
	public static String cust_id;
	static CheckoutClient ckoClient;
	
	public static Response<CardResponseModel> createCard() throws JsonSyntaxException, IOException{
		Response<CustomerList> customerResponse = null;
		ckoClient= new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
		customerResponse = ckoClient.CustomerService.getCustomerList();
		
		cust_id=customerResponse.getType().data.get(0).id;		
		
		CardCreate cardsPayload=new CardCreate();
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";
		cardsPayload.customerId=cust_id;
		
		
		return ckoClient.CardService.createCard(cardsPayload);
	}
}
