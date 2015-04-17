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

import controllers.ApiHttpContext;
import controllers.ApiUrls;
import controllers.AppSettings;

public class PaymentProviderService{

	public Response<LocalPaymentProvider> getLocaPaymentProvider(
			LocalPaymentProviderModel paymentProviderPayloads)
					throws IOException,JsonSyntaxException {
		Response<LocalPaymentProvider> paymentProviderResponse = null;
		
		ApiHttpContext httpRequest = new ApiHttpContext();
		String Url=String.format(ApiUrls.LocalPaymentProvidersUri, paymentProviderPayloads.id,paymentProviderPayloads.sessionToken);
			httpRequest.createGetConnection(Url,AppSettings.publicKey, "GET");
			
			paymentProviderResponse = httpRequest
					.getResponse(LocalPaymentProvider.class);
		
		return paymentProviderResponse;
	}	
		

	public Response<LocalPaymentProviderList> getLocaPaymentProviderList(
			LocalPaymentProviderListModel paymentProviderPayloads)
					throws IOException,JsonSyntaxException {
		Response<LocalPaymentProviderList> paymentProviderResponse = null;

		ApiHttpContext httpRequest = new ApiHttpContext();
		String Url=String.format(ApiUrls.LocalPaymentProvidersListUri,paymentProviderPayloads.sessionToken);
			httpRequest.createGetConnection(Url, AppSettings.publicKey, "GET");
			
			paymentProviderResponse = httpRequest
					.getResponse(LocalPaymentProviderList.class);
		
		return paymentProviderResponse;
	}
	
	public static String tokenUrl(LocalPaymentProviderListModel paymentProviderPayloads){
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
		
		
	public Response<CardProvider> getCardProvider(CardProviderModels paymentProviderPayloads)throws IOException,JsonSyntaxException{
		Response<CardProvider> paymentProviderResponse = null;

		ApiHttpContext httpRequest = new ApiHttpContext();
		String Url=String.format(ApiUrls.CardProvidersUri,paymentProviderPayloads.id);
			httpRequest.createGetConnection(Url,AppSettings.publicKey, "GET");

			paymentProviderResponse = httpRequest.getResponse(CardProvider.class);
		
		return paymentProviderResponse;
	}
	
	public Response<CardProviderList> getCardProviderList()throws IOException,JsonSyntaxException {
		Response<CardProviderList> paymentProviderResponse = null;

		ApiHttpContext httpRequest = new ApiHttpContext();
		
			httpRequest.createGetConnection(ApiUrls.CardProvidersListUri,AppSettings.publicKey, "GET");

			paymentProviderResponse = httpRequest.getResponse(CardProviderList.class);
		
		return paymentProviderResponse;
	}
}
