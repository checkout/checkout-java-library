package apiServices.tokens;

import java.io.IOException;

import utilities.HttpMethods;
import apiServices.sharedModels.Response;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import controllers.ApiHttpClient;
import controllers.ApiUrls;
import controllers.AppSettings;
import exception.CKOException;

public class TokenService {

	/*public Response<CardToken> createCardToken(
			CardTokenCreate cardsPayload) throws CKOException {
		Response<CardToken> cardsResponse = null;
		Gson gson = new Gson();
		CheckErrors(cardsPayload.ValidateProperty()); // throw validation
														// exception
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(cardsPayload);
			httpRequest.createPostConnection(CardTokensApiUri,
					AppSettings.publicKey, "POST", payloadJson);

			cardsResponse = httpRequest
					.getResponse(CardToken.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return cardsResponse;

	}*/
	
	public Response<PaymentToken> createPaymentToken(PaymentTokenCreate cardsPayload) throws CKOException{
		Response<PaymentToken> cardsResponse = null;
		Gson gson = new Gson();

		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(cardsPayload);
			httpRequest.createConnection( ApiUrls.PaymentTokensApiUri,AppSettings.secretKey, HttpMethods.Post, payloadJson);

			cardsResponse = httpRequest.getResponse(PaymentToken.class);
			
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return cardsResponse;
		
	}
}
