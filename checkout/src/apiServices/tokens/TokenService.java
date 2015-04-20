package apiServices.tokens;

import java.io.IOException;

import apiServices.sharedModels.Response;
import apiServices.tokens.request.PaymentTokenCreate;
import apiServices.tokens.response.PaymentToken;
import checkout.exception.CKOException;
import checkout.helpers.ApiHttpClient;
import checkout.helpers.ApiUrls;
import checkout.helpers.AppSettings;
import checkout.utilities.HttpMethods;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class TokenService {
	
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
