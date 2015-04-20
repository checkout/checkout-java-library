package com.checkout.apiServices.charges;

import java.io.IOException;

import com.checkout.apiServices.charges.response.Charge;
import com.checkout.apiServices.sharedModels.Response;
import com.checkout.exception.CKOException;
import com.checkout.helpers.ApiHttpClient;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.checkout.utilities.HttpMethods;
import com.google.gson.JsonSyntaxException;

public class ChargeService {

	public Response<Charge> verifyCharge(String paymentToken) throws CKOException {
		Response<Charge> chargesResponse = null;
		
		ApiHttpClient httpRequest = new ApiHttpClient();
		
		String apiUri = ApiUrls.ChargesApiUri +"/"+ paymentToken;
		 
		try {
			httpRequest.createConnection(apiUri,AppSettings.secretKey, HttpMethods.Get, null);
			chargesResponse = httpRequest.getResponse(Charge.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}

}
