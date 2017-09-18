package com.checkout.api.services.lookups;


import java.io.IOException;

import com.checkout.api.services.BaseService;
import com.checkout.api.services.lookups.response.BinLookup;
import com.checkout.api.services.lookups.response.BinLookupWithCardToken;
import com.checkout.api.services.shared.Response;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.google.gson.JsonSyntaxException;

public class LookupsService extends BaseService{
	
	public LookupsService(){
		super();
	}

	public Response<BinLookup> binLookup(String bin)  throws IOException,JsonSyntaxException {
		String apiUrl=String.format(ApiUrls.BINLOOKUP, bin);

		return httpClient.getRequest(apiUrl, AppSettings.secretKey, BinLookup.class);
	}

    public Response<BinLookupWithCardToken> binLookupWithCardToken(String token)  throws IOException,JsonSyntaxException {
        String apiUrl=String.format(ApiUrls.BINLOOKUP_CARDTOKEN, token);

        return httpClient.getRequest(apiUrl, AppSettings.secretKey, BinLookupWithCardToken.class);
    }

}
