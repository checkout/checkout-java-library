package controllers;

import java.io.IOException;

import apiServices.cards.CardService;
import apiServices.customers.CustomerService;
import apiServices.payment_provider.PaymentProviderService;
import apiServices.tokens.TokenService;
import apiServices.charges.ChargeService;

public class CheckoutClient {

	public  CardService CardService;
	public  CustomerService CustomerService;
	public  PaymentProviderService PaymentProviderService;
	public  TokenService TokenService;
	public  ChargeService ChargeService;
	
	
	
	public CheckoutClient(String _secretKey,String _publicKey,boolean _debugMode,int _connectTimeout,int _readTimeout) throws IOException{			
		 this.CardService=new CardService();
		 this.CustomerService=new CustomerService();
		 this.PaymentProviderService=new PaymentProviderService();
		 this.TokenService=new TokenService();
		 this.ChargeService=new ChargeService();
		AppSettings.secretKey=_secretKey;
		AppSettings.publicKey=_publicKey;
		AppSettings.debugMode=_debugMode;
		AppSettings.connectTimeout=_connectTimeout;
		AppSettings.readTimeout=_readTimeout;
		ApiHttpContext.logFile();		
	}	
	
	public CheckoutClient(String _secretKey,String _publicKey){			
		 this.CardService=new CardService();
		 this.CustomerService=new CustomerService();
		 this.PaymentProviderService=new PaymentProviderService();
		 this.TokenService=new TokenService();
		 this.ChargeService=new ChargeService();
		 AppSettings.secretKey=_secretKey;
		 AppSettings.publicKey=_publicKey;
		
	}
	
}
