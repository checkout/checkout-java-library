package com.checkout.api.services;

import com.checkout.helpers.ApiHttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseService {
	
	protected ApiHttpClient httpClient;
	protected Gson gson;
	
	protected BaseService(){
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
		httpClient = new ApiHttpClient(gson);
	}
}
