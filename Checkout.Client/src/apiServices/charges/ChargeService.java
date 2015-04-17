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
	
	/*	public Response<Charge> chargeWithCard(CardCharge chargesPayload) throws CKOException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(ApiUrls.CardChargesApiUri,AppSettings.secretKey, HttpMethods.Post, payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}
	

	public Response<Charge> chargeWithDefaultCustomerCard(ChargeDefaultCard chargesPayload)throws CKOException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
	
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(ApiUrls.DefaultCardChargesApiUri,AppSettings.secretKey, HttpMethods.Post, payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
			
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}
	
	public Response<Charge> chargeWithCardId(
			CardIdCharge chargesPayload) throws CKOException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		CheckErrors(chargesPayload.ValidateProperty());
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(CardChargesApiUri,
					AppSettings.secretKey, "POST", payloadJson);
			chargesResponse = httpRequest
					.getResponse(Charge.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}



	public Response<Charge> chargeWithCardToken(
			CardTokenCharge chargesPayload) throws CKOException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		CheckErrors(chargesPayload.ValidateProperty());
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(CardTokenChargesApiUri,
					AppSettings.secretKey, "POST", payloadJson);
			chargesResponse = httpRequest
					.getResponse(Charge.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}

	public Response<Charge> createLocalPaymentCharge(
			LocalPaymentCharge chargesPayload)
			throws CKOException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();

		CheckErrors(chargesPayload.ValidateProperty());
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(chargesPayload);
			System.out.println(payloadJson);
			httpRequest.createPostConnection(LocalPaymentChargesApiUri, AppSettings.publicKey,
					"POST", payloadJson);
			chargesResponse = httpRequest
					.getResponse(Charge.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}

	public Response<Charge> RefundCardChargeRequest(
			ChargeRefund chargesPayload) throws CKOException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		CheckErrors(chargesPayload.ValidateProperty());
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(RefundChargeApiUri(chargesPayload.chargeId), AppSettings.secretKey,
					"POST", payloadJson);
			chargesResponse = httpRequest
					.getResponse(Charge.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}

	public Response<Charge> captureCardCharge(
			ChargeCapture chargesPayload) throws CKOException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		CheckErrors(chargesPayload.ValidateProperty());
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(CaptureChargesApiUri(chargesPayload.chargeId), AppSettings.secretKey,
					"POST", payloadJson);
			chargesResponse = httpRequest
					.getResponse(Charge.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}

	public Response<Charge> updateCardCharge(
			ChargeUpdate chargesPayload) throws CKOException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		CheckErrors(chargesPayload.ValidateProperty());
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(UpdateChargesApiUri(chargesPayload.chargeId), AppSettings.secretKey, "PUT",
					payloadJson);
			chargesResponse = httpRequest
					.getResponse(Charge.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return chargesResponse;
	}
*/
}
