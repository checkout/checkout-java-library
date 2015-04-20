package apiServices.charges;

import java.io.IOException;

import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;
import checkout.exception.CKOException;
import checkout.helpers.ApiHttpClient;
import checkout.helpers.ApiUrls;
import checkout.helpers.AppSettings;
import checkout.utilities.HttpMethods;

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
