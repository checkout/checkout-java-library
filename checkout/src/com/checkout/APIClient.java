package com.checkout;

import java.io.IOException;

import com.checkout.api.services.card.CardService;
import com.checkout.api.services.charge.ChargeService;
import com.checkout.api.services.customer.CustomerService;
import com.checkout.api.services.reporting.ReportingService;
import com.checkout.api.services.token.TokenService;
import com.checkout.api.services.lookups.LookupsService;
import com.checkout.helpers.ApiHttpClient;
import com.checkout.helpers.AppSettings;
import com.checkout.helpers.Environment;

public class APIClient {
	
	public TokenService tokenService;
	public ChargeService chargeService;
	public CustomerService customerService;
	public CardService cardService;
	public ReportingService reportingService;
    public LookupsService lookupsService;
	
	public APIClient(String secretKey,Environment env, boolean debugMode,int connectTimeout,int readTimeout) throws IOException{
		this(secretKey,env,debugMode);
		
		AppSettings.connectTimeout=connectTimeout;
		AppSettings.readTimeout=readTimeout;
	}	
	
	public APIClient(String secretKey, String publicKey, Environment env, boolean debugMode,int connectTimeout,int readTimeout) throws IOException{
		this(secretKey,publicKey,env,debugMode);
		
		AppSettings.connectTimeout=connectTimeout;
		AppSettings.readTimeout=readTimeout;
	}	
	
	public APIClient(String secretKey,Environment env,boolean debugMode) throws IOException{
		this(secretKey,env);
		
		AppSettings.debugMode=debugMode;
	}
	
	public APIClient(String secretKey, String publicKey, Environment env,boolean debugMode) throws IOException{
		this(secretKey,publicKey,env);
		
		AppSettings.debugMode=debugMode;
	}
	
	public APIClient(String secretKey,Environment env)throws IOException{
		 AppSettings.secretKey=secretKey;
		 
		 AppSettings.SetEnvironment(env); 
		 ApiHttpClient.SetupLogger();
		 SetupServices();
	}
	
	public APIClient(String secretKey,String publicKey,Environment env)throws IOException{
		 AppSettings.secretKey=secretKey;
		 AppSettings.publicKey=publicKey;
		 
		 AppSettings.SetEnvironment(env); 
		 ApiHttpClient.SetupLogger();
		 SetupServices();
	}

	public APIClient(String secretKey,boolean debugMode) throws IOException{			
		this(secretKey,Environment.SANDBOX,true);
	}
	
	public APIClient(String secretKey,String publicKey,boolean debugMode) throws IOException{			
		this(secretKey,publicKey,Environment.SANDBOX,true);
	}
	
	public APIClient(String secretKey) throws IOException{			
		 AppSettings.secretKey=secretKey;
		 
		 AppSettings.SetEnvironment(Environment.SANDBOX);
		 ApiHttpClient.SetupLogger();	
		 SetupServices();
	}
	
	public APIClient(String secretKey, String publicKey) throws IOException{			
		 AppSettings.secretKey=secretKey;
		 AppSettings.publicKey=publicKey;
		 AppSettings.SetEnvironment(Environment.SANDBOX);
		 ApiHttpClient.SetupLogger();	
		 SetupServices();
	}
	
	private void SetupServices() {
		tokenService=new TokenService();
		chargeService=new ChargeService();
		customerService=new CustomerService();
		cardService=new CardService();
		reportingService=new ReportingService();
        lookupsService=new LookupsService();
	}
	
	public static void main(String[] args) 
	{
		
	}
}
