package test;

import apiServices.cards.CardService;
import apiServices.cards.request.CardCreate;
import apiServices.cards.response.Card;
import apiServices.customers.CustomerService;
import apiServices.customers.response.CustomerList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class CardHelper {
	public static String cust_id;
	
	
	public static Response<CardResponseModel> CreateCard() throws CKOException{
		Response<CustomerList> customerResponse = null;
		CustomerService api = new CustomerService();
		customerResponse = api.getCustomerList();
		
		cust_id=customerResponse.Model().data.get(0).id;		
		
		CardCreate cardsPayload=new CardCreate();
		Card cardContent=new Card();
		cardsPayload.card=cardContent;
		cardContent.name="zwp";
		cardContent.number="4543474002249996";
		cardContent.expiryMonth="06";
		cardContent.expiryYear="2017";
		cardContent.cvv="956";
		cardsPayload.customerId=cust_id;
		
		CardService api2=new CardService();
		return api2.createCard(cardsPayload);
	}
}
