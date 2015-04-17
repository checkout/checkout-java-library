package test;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import apiServices.payment_provider.request.LocalPaymentProviderListModel;
import apiServices.payment_provider.response.LocalPaymentProviderList;
import apiServices.sharedModels.Response;
import apiServices.tokens.response.PaymentToken;


public class LocalPaymentHelper {
	static CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");;

	public static Response<LocalPaymentProviderList> getLocalPaymentProviderListRequest() throws JsonSyntaxException, IOException{

		Response<PaymentToken> sessionTokenResponse = TokenHelper.createSessionToken();

		LocalPaymentProviderListModel paymentProviderPayloads = new LocalPaymentProviderListModel();

		paymentProviderPayloads.sessionToken = sessionTokenResponse.getType().id;
		
		return ckoClient.PaymentProviderService.getLocaPaymentProviderList(paymentProviderPayloads);

	}
}
