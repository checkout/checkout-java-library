package apiServices.payment_provider;

import java.io.IOException;

import apiServices.payment_provider.request.CardProviderModels;
import apiServices.payment_provider.request.LocalPaymentProviderListModel;
import apiServices.payment_provider.request.LocalPaymentProviderModel;
import apiServices.payment_provider.response.CardProvider;
import apiServices.payment_provider.response.CardProviderList;
import apiServices.payment_provider.response.LocalPaymentProvider;
import apiServices.payment_provider.response.LocalPaymentProviderList;
import apiServices.sharedModels.Response;

import com.google.gson.JsonSyntaxException;

import controllers.ApiHttpClient;
import controllers.ApiUrls;
import controllers.AppSettings;
import exception.CKOException;

public class PaymentProviderService extends ApiUrls {

	public Response<LocalPaymentProvider> getLocaPaymentProvider(
			LocalPaymentProviderModel paymentProviderPayloads)
			throws CKOException {
		Response<LocalPaymentProvider> paymentProviderResponse = null;
		CheckErrors(paymentProviderPayloads.ValidateProperty());

		ApiHttpClient httpRequest = new ApiHttpClient();
		try {
			httpRequest.createGetConnection(LocalPaymentProvidersUri(paymentProviderPayloads.id,paymentProviderPayloads.sessionToken),
					AppSettings.publicKey, "GET");
			
			paymentProviderResponse = httpRequest
					.getResponse(LocalPaymentProvider.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return paymentProviderResponse;
	}	
		

	public Response<LocalPaymentProviderList> getLocaPaymentProviderList(
			LocalPaymentProviderListModel paymentProviderPayloads)
			throws CKOException {
		Response<LocalPaymentProviderList> paymentProviderResponse = null;
		CheckErrors(paymentProviderPayloads.ValidateProperty());

		ApiHttpClient httpRequest = new ApiHttpClient();
		try {			
				httpRequest.createGetConnection(buildApiUrl(TokenUrl(paymentProviderPayloads)), AppSettings.publicKey, "GET");
			
			paymentProviderResponse = httpRequest
					.getResponse(LocalPaymentProviderList.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return paymentProviderResponse;
	}
	
	public static String TokenUrl(LocalPaymentProviderListModel paymentProviderPayloads){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("providers/localpayments?PaymentToken="+paymentProviderPayloads.sessionToken);
		
		if(paymentProviderPayloads.countryCodes!=null){
			stringBuilder.append("&countryCode="+paymentProviderPayloads.countryCodes);			
		}		
		if(paymentProviderPayloads.limit!=null){
			stringBuilder.append("&limit="+paymentProviderPayloads.limit);
		}
		if(paymentProviderPayloads.name!=null){
			stringBuilder.append("&name="+paymentProviderPayloads.name);
		}
		//System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}
		
		
	public Response<CardProvider> getCardProvider(
			CardProviderModels paymentProviderPayloads)
			throws CKOException {
		Response<CardProvider> paymentProviderResponse = null;
		CheckErrors(paymentProviderPayloads.ValidateProperty());

		ApiHttpClient httpRequest = new ApiHttpClient();
		try {
			httpRequest.createGetConnection(CardProvidersUri(paymentProviderPayloads.id),
					AppSettings.publicKey, "GET");

			paymentProviderResponse = httpRequest
					.getResponse(CardProvider.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return paymentProviderResponse;
	}
	
	public Response<CardProviderList> getCardProviderList()throws CKOException {
		Response<CardProviderList> paymentProviderResponse = null;

		ApiHttpClient httpRequest = new ApiHttpClient();
		try {
			httpRequest.createGetConnection(CardProvidersListUri,
					AppSettings.publicKey, "GET");

			paymentProviderResponse = httpRequest
					.getResponse(CardProviderList.class);
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return paymentProviderResponse;
	}
}
