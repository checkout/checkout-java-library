package test;

import apiServices.payment_provider.PaymentProviderService;
import apiServices.payment_provider.request.LocalPaymentProviderListModel;
import apiServices.payment_provider.response.LocalPaymentProviderList;
import apiServices.sharedModels.Response;
import apiServices.tokens.response.PaymentToken;
import exception.CKOException;

public class LocalPaymentHelper {

	public static Response<LocalPaymentProviderList> GetLocalPaymentProviderListRequest()
			throws CKOException {

		Response<PaymentToken> sessionTokenResponse = TokenHelper
				.CreateSessionToken();

		LocalPaymentProviderListModel paymentProviderPayloads = new LocalPaymentProviderListModel();

		paymentProviderPayloads.sessionToken = sessionTokenResponse.Model().id;

		PaymentProviderService api = new PaymentProviderService();
		
		return api.getLocaPaymentProviderList(paymentProviderPayloads);

	}
}
