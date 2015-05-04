package com.checkout.api.services.card;

import java.io.IOException;

import com.checkout.api.services.BaseService;
import com.checkout.api.services.card.request.CardCreate;
import com.checkout.api.services.card.request.CardUpdate;
import com.checkout.api.services.card.response.Card;
import com.checkout.api.services.card.response.CardList;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.checkout.utilities.HttpMethods;
import com.google.gson.JsonSyntaxException;

public class CardService extends BaseService{
	
	public CardService(){
		super();
	}

	public Response<Card> createCard(String customerId, CardCreate payload)throws IOException,JsonSyntaxException  {

			String url=String.format(ApiUrls.CARDS, customerId);
			
			return httpClient.postRequest(url, AppSettings.secretKey,gson.toJson(payload), Card.class );

	}

	public Response<OkResponse> updateCard(String customerId,String cardId, CardUpdate payload) throws IOException,JsonSyntaxException{
				
			String url=String.format(ApiUrls.CARD, customerId, cardId);
			
			return httpClient.putRequest(url,AppSettings.secretKey, gson.toJson(payload), OkResponse.class);
	}
	
	public Response<Card> getCard(String customerId,String cardId)throws IOException,JsonSyntaxException{
		
		String url=String.format(ApiUrls.CARD, customerId, cardId);
		
		return httpClient.getRequest(url,AppSettings.secretKey, Card.class);
	}
	
	
	public Response<CardList> getCardList(String customerId)throws IOException,JsonSyntaxException{
		
		String url=String.format(ApiUrls.CARDS, customerId);
		
		return httpClient.getRequest(url,AppSettings.secretKey, CardList.class);
	}

	
	public Response<OkResponse> deleteCard(String customerId,String cardId)throws IOException,JsonSyntaxException{
		
		String url=String.format(ApiUrls.CARD, customerId,cardId);
		return httpClient.deleteRequest(url,AppSettings.secretKey,OkResponse.class);
	}
}
