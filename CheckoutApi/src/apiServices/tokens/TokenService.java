package apiServices.tokens;

import java.io.IOException;

import apiServices.sharedModels.Response;
import apiServices.tokens.request.CardTokenCreate;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.CardToken;
import apiServices.tokens.response.PaymentToken;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import controllers.ApiHttpContext;
import controllers.ApiUrls;
import controllers.AppSettings;


public class TokenService {

	public Response<CardToken> createCardToken(
			CardTokenCreate cardsPayload) throws IOException,JsonSyntaxException {
		Response<CardToken> cardsResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();

			String payloadJson = gson.toJson(cardsPayload);
			httpRequest.createPostConnection(ApiUrls.CardTokensApiUri,AppSettings.publicKey, "POST", payloadJson);
			
			cardsResponse = httpRequest.getResponse(CardToken.class);
		
		return cardsResponse;

	}
	
	public Response<PaymentToken> createPaymentToken(PaymentTokenCreate cardsPayload) throws IOException,JsonSyntaxException{
		Response<PaymentToken> cardsResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();

			String payloadJson = gson.toJson(cardsPayload);
			httpRequest.createPostConnection(ApiUrls.PaymentTokensApiUri,AppSettings.secretKey, "POST", payloadJson);

			cardsResponse = httpRequest.getResponse(PaymentToken.class);
		
		return cardsResponse;
		
	}
}
