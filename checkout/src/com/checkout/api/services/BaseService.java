package com.checkout.api.services;

import com.checkout.helpers.ApiHttpClient;
import com.google.gson.Gson;

public class BaseService {
	
	protected ApiHttpClient httpClient;
	protected Gson gson;
	
	protected BaseService(){
		gson = new Gson();
		httpClient = new ApiHttpClient(gson);
	}
}
