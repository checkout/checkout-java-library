package com.checkout.api.services.payouts;


import java.io.IOException;

import com.checkout.api.services.BaseService;
import com.checkout.api.services.payouts.request.BasePayout;
import com.checkout.api.services.payouts.response.Payout;
import com.checkout.api.services.shared.Response;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.google.gson.JsonSyntaxException;

public class PayoutsService extends BaseService{
	
	public PayoutsService(){
		super();
	}

	public Response<Payout> createPayout(BasePayout payload)throws IOException,JsonSyntaxException {

		return httpClient.postRequest(ApiUrls.PAYOUTS,AppSettings.secretKey,gson.toJson(payload), Payout.class);
	}

}
