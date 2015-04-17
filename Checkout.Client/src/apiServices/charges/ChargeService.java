package apiServices.charges;

import java.io.IOException;
import utilities.HttpMethods;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;
import com.google.gson.JsonSyntaxException;
import controllers.ApiHttpClient;
import controllers.ApiUrls;
import controllers.AppSettings;
import exception.CKOException;

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
