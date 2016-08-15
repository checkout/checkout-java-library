package com.checkout.api.services.token;

import java.io.IOException;

import com.checkout.api.services.BaseService;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.checkout.api.services.token.request.PaymentTokenCreate;
import com.checkout.api.services.token.request.PaymentTokenUpdate;
import com.checkout.api.services.token.request.VisaCheckoutTokenCreate;
import com.checkout.api.services.token.response.PaymentToken;
import com.checkout.api.services.token.response.VisaCheckoutToken;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.google.gson.JsonSyntaxException;


public class TokenService extends BaseService {
	
	public Response<PaymentToken> createPaymentToken(PaymentTokenCreate payload) throws IOException,JsonSyntaxException {
		
		return httpClient.postRequest(ApiUrls.PAYMENT_TOKENS,AppSettings.secretKey,gson.toJson(payload),PaymentToken.class);	
	}
	
	public Response<VisaCheckoutToken> createVisaCheckoutToken(VisaCheckoutTokenCreate payload) throws IOException,JsonSyntaxException {
		return httpClient.postRequest(ApiUrls.VISACHECKOUT_TOKENS,AppSettings.publicKey,gson.toJson(payload),VisaCheckoutToken.class);	
	}
	
	public Response<OkResponse> updatePaymentToken(String paymentToken, PaymentTokenUpdate payload) throws IOException,JsonSyntaxException {
		
		String url=String.format(ApiUrls.PAYMENT_TOKEN, paymentToken);
		
		return httpClient.putRequest(url, AppSettings.secretKey,gson.toJson(payload), OkResponse.class );
	}
}
