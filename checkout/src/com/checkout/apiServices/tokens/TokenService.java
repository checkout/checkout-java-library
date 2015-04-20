package com.checkout.apiServices.tokens;

import java.io.IOException;

import com.checkout.apiServices.sharedModels.Response;
import com.checkout.apiServices.tokens.request.PaymentTokenCreate;
import com.checkout.apiServices.tokens.response.PaymentToken;
import com.checkout.exception.CKOException;
import com.checkout.helpers.ApiHttpClient;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.checkout.utilities.HttpMethods;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class TokenService {
	
	public Response<PaymentToken> createPaymentToken(PaymentTokenCreate tokenPayload) throws CKOException{
		Response<PaymentToken> cardsResponse = null;
		Gson gson = new Gson();

		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(tokenPayload);
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
