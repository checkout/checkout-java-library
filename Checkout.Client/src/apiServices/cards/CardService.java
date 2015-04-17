package apiServices.cards;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import apiServices.cards.request.CardCreate;
import apiServices.cards.request.CardDelete;
import apiServices.cards.request.CardGet;
import apiServices.cards.request.CardListGet;
import apiServices.cards.request.CardUpdate;
import apiServices.cards.response.CardList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;
import controllers.ApiHttpClient;
import controllers.ApiUrls;
import controllers.AppSettings;
import exception.CKOException;

public class CardService extends ApiUrls {

	public Response<CardResponseModel> createCard(
			CardCreate cardsPayload) throws CKOException {

		Response<CardResponseModel> cardsResponse = null;
		Gson gson = new Gson();
		CheckErrors(cardsPayload.ValidateProperty()); // throw validation
														// exception
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {

			String payloadJson = gson.toJson(cardsPayload);			
			httpRequest.createPostConnection(
					CardsApiUri(cardsPayload.customerId), AppSettings.secretKey,
					"POST", payloadJson);

			cardsResponse = httpRequest.getResponse(CardResponseModel.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return cardsResponse;
	}

	public Response<CardResponseModel> updateCard(
			CardUpdate cardsPayload) throws CKOException {
		Response<CardResponseModel> cardsResponse = null;
		Gson gson = new Gson();
		CheckErrors(cardsPayload.ValidateProperty());

		ApiHttpClient httpRequest = new ApiHttpClient();
		try {
			String payloadJson = gson.toJson(cardsPayload);
			httpRequest.createPostConnection(
					CardApiUri(cardsPayload.customerId, cardsPayload.cardId),
					AppSettings.secretKey, "PUT", payloadJson);
			cardsResponse = httpRequest.getResponse(CardResponseModel.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return cardsResponse;

	}

	public Response<CardResponseModel> getCard(CardGet cardsPayload)
			throws CKOException {
		Response<CardResponseModel> cardsResponse = null;
		CheckErrors(cardsPayload.ValidateProperty());

		ApiHttpClient httpRequest = new ApiHttpClient();
		try {
			httpRequest.createGetConnection(
					CardApiUri(cardsPayload.customerId, cardsPayload.cardId),
					AppSettings.secretKey, "GET");

			cardsResponse = httpRequest.getResponse(CardResponseModel.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return cardsResponse;
	}

	public Response<CardList> getCardList(CardListGet cardsPayload)
			throws CKOException {
		Response<CardList> cardsResponse = null;
		CheckErrors(cardsPayload.ValidateProperty());

		ApiHttpClient httpRequest = new ApiHttpClient();
		try {
			httpRequest.createGetConnection(CardsApiUri(cardsPayload.customerId), AppSettings.secretKey,
					"GET");

			cardsResponse = httpRequest.getResponse(CardList.class);

		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return cardsResponse;
	}

	
	public Response<DeleteResponse> deleteCard(CardDelete cardsPayload)
			throws CKOException {
		Response<DeleteResponse> cardsResponse = null;
		CheckErrors(cardsPayload.ValidateProperty());

		ApiHttpClient httpRequest = new ApiHttpClient();
		try {
			httpRequest.createGetConnection(CardApiUri(cardsPayload.customerId, cardsPayload.cardId),
					AppSettings.secretKey, "DELETE");

			cardsResponse = httpRequest.getResponse(DeleteResponse.class);

		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return cardsResponse;
	}

}
