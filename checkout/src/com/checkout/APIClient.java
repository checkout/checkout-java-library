package com.checkout;

import java.io.IOException;

import com.checkout.api.services.card.CardService;
import com.checkout.api.services.charge.ChargeService;
import com.checkout.api.services.customer.CustomerService;
import com.checkout.api.services.token.TokenService;
import com.checkout.helpers.ApiHttpClient;
import com.checkout.helpers.AppSettings;

public class APIClient {
	
	public TokenService tokenService;
	public ChargeService chargeService;
	public CustomerService customerService;
	public CardService cardService;
	
	public APIClient(String secretKey, boolean debugMode,int connectTimeout,int readTimeout) throws IOException{
		this(secretKey,debugMode);
		
		AppSettings.connectTimeout=connectTimeout;
		AppSettings.readTimeout=readTimeout;
	}	
	
	public APIClient(String secretKey,boolean debugMode) throws IOException{
		this(secretKey);
		
		AppSettings.debugMode=debugMode;
	}
	
	public APIClient(String secretKey) throws IOException{			
		 
		 AppSettings.secretKey=secretKey;
		 ApiHttpClient.SetupLogger();	
		 SetupServices();
	}

	private void SetupServices() {
		tokenService=new TokenService();
		chargeService=new ChargeService();
		customerService=new CustomerService();
		cardService=new CardService();
	}
	
	public static void main(String[] args) 
	{
		
	}
}
