package apiServices.cards;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import controllers.ApiHttpContext;
import controllers.ApiUrls;
import controllers.AppSettings;
import apiServices.cards.request.CardCreate;
import apiServices.cards.request.CardDelete;
import apiServices.cards.request.CardGet;
import apiServices.cards.request.CardListGet;
import apiServices.cards.request.CardUpdate;
import apiServices.cards.response.CardList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;


public class CardService {

	public Response<CardResponseModel> createCard(CardCreate cardsPayload)throws IOException,JsonSyntaxException  {

		Response<CardResponseModel> cardsResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();
	
			String payloadJson = gson.toJson(cardsPayload);		
			String Url=String.format(ApiUrls.CardsApiUri, cardsPayload.customerId);
			httpRequest.createPostConnection(Url, AppSettings.secretKey,"POST", payloadJson);

			cardsResponse = httpRequest.getResponse(CardResponseModel.class);
		
		return cardsResponse;
	}

	public Response<CardResponseModel> updateCard(
			CardUpdate cardsPayload) throws IOException,JsonSyntaxException{
		Response<CardResponseModel> cardsResponse = null;
		Gson gson = new Gson();

		ApiHttpContext httpRequest = new ApiHttpContext();
		
			String payloadJson = gson.toJson(cardsPayload);
			String Url=String.format(ApiUrls.CardApiUri, cardsPayload.customerId,cardsPayload.cardId);
			httpRequest.createPostConnection(Url,AppSettings.secretKey, "PUT", payloadJson);
			cardsResponse = httpRequest.getResponse(CardResponseModel.class);
		
		return cardsResponse;

	}

	public Response<CardResponseModel> getCard(CardGet cardsPayload)throws IOException,JsonSyntaxException{
		Response<CardResponseModel> cardsResponse = null;
	
		ApiHttpContext httpRequest = new ApiHttpContext();
		String Url=String.format(ApiUrls.CardApiUri, cardsPayload.customerId,cardsPayload.cardId);
			httpRequest.createGetConnection(
					Url,
					AppSettings.secretKey, "GET");

			cardsResponse = httpRequest.getResponse(CardResponseModel.class);
		
		return cardsResponse;
	}

	public Response<CardList> getCardList(CardListGet cardsPayload)throws IOException,JsonSyntaxException{
		Response<CardList> cardsResponse = null;
	
		ApiHttpContext httpRequest = new ApiHttpContext();
		String Url=String.format(ApiUrls.CardsApiUri, cardsPayload.customerId);
			httpRequest.createGetConnection(Url, AppSettings.secretKey,"GET");

			cardsResponse = httpRequest.getResponse(CardList.class);

		return cardsResponse;
	}

	
	public Response<DeleteResponse> deleteCard(CardDelete cardsPayload)throws IOException,JsonSyntaxException{
		Response<DeleteResponse> cardsResponse = null;

		ApiHttpContext httpRequest = new ApiHttpContext();
		String Url=String.format(ApiUrls.CardApiUri, cardsPayload.customerId,cardsPayload.cardId);
			httpRequest.createGetConnection(Url,AppSettings.secretKey, "DELETE");

			cardsResponse = httpRequest.getResponse(DeleteResponse.class);

		return cardsResponse;
	}

}
