package apiServices.charges;

import java.io.IOException;
import apiServices.charges.request.CardCharge;
import apiServices.charges.request.CardIdCharge;
import apiServices.charges.request.CardTokenCharge;
import apiServices.charges.request.ChargeCapture;
import apiServices.charges.request.ChargeDefaultCard;
import apiServices.charges.request.ChargeRefund;
import apiServices.charges.request.ChargeUpdate;
import apiServices.charges.request.LocalPaymentCharge;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import controllers.ApiHttpContext;
import controllers.ApiUrls;
import controllers.AppSettings;

public class ChargeService {

	public Response<Charge> chargeWithCard(CardCharge chargesPayload)throws IOException,JsonSyntaxException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();

		ApiHttpContext httpRequest = new ApiHttpContext();
			String payloadJson = gson.toJson(chargesPayload);
			
			httpRequest.createPostConnection(ApiUrls.CardChargesApiUri,
					AppSettings.secretKey, "POST", payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		
		return chargesResponse;
	}

	public Response<Charge> chargeWithCardId(CardIdCharge chargesPayload) throws IOException,JsonSyntaxException{
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();
		
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(ApiUrls.CardChargesApiUri,
					AppSettings.secretKey, "POST", payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		
		return chargesResponse;
	}

	public Response<Charge> chargeWithDefaultCustomerCard(
			ChargeDefaultCard chargesPayload)throws IOException,JsonSyntaxException{
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		ApiHttpContext httpRequest = new ApiHttpContext();
		
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(ApiUrls.DefaultCardChargesApiUri,AppSettings.secretKey, "POST", payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		
		return chargesResponse;
	}

	public Response<Charge> chargeWithCardToken(
			CardTokenCharge chargesPayload) throws IOException,JsonSyntaxException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();
		
			String payloadJson = gson.toJson(chargesPayload);
			httpRequest.createPostConnection(ApiUrls.CardTokenChargesApiUri,AppSettings.secretKey, "POST", payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		
		return chargesResponse;
	}

	public Response<Charge> createLocalPaymentCharge(LocalPaymentCharge chargesPayload)throws IOException,JsonSyntaxException{
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();

		ApiHttpContext httpRequest = new ApiHttpContext();
	
			String payloadJson = gson.toJson(chargesPayload);
			System.out.println(payloadJson);
			httpRequest.createPostConnection(ApiUrls.LocalPaymentChargesApiUri, AppSettings.publicKey,"POST", payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		
		return chargesResponse;
	}

	public Response<Charge> refundCardChargeRequest(
			ChargeRefund chargesPayload) throws IOException,JsonSyntaxException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();		
			String payloadJson = gson.toJson(chargesPayload);
			String Url=String.format(ApiUrls.RefundChargeApiUri, chargesPayload.chargeId);
			httpRequest.createPostConnection(Url, AppSettings.secretKey,"POST", payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		
		return chargesResponse;
	}

	public Response<Charge> captureCardCharge(
			ChargeCapture chargesPayload) throws IOException,JsonSyntaxException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
	
		ApiHttpContext httpRequest = new ApiHttpContext();
		
			String payloadJson = gson.toJson(chargesPayload);
			String Url=String.format(ApiUrls.CaptureChargesApiUri, chargesPayload.chargeId);
			httpRequest.createPostConnection(Url, AppSettings.secretKey,"POST", payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		
		return chargesResponse;
	}

	public Response<Charge> updateCardCharge(
			ChargeUpdate chargesPayload) throws IOException,JsonSyntaxException {
		Response<Charge> chargesResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();

			String payloadJson = gson.toJson(chargesPayload);
			String Url=String.format(ApiUrls.UpdateChargesApiUri, chargesPayload.chargeId);
			httpRequest.createPostConnection(Url, AppSettings.secretKey, "PUT",payloadJson);
			chargesResponse = httpRequest.getResponse(Charge.class);
		
		return chargesResponse;
	}
}
