package controllers;

import apiServices.customers.CustomerService;
import apiServices.payment_provider.PaymentProviderService;
import apiServices.tokens.TokenService;
import apiServices.cards.CardService;
import apiServices.charges.ChargeService;

public class CheckoutClient {

	public CardService CardService;
	public CustomerService CustomerService;
	public PaymentProviderService PaymentProviderService;
	public TokenService TokenService;
	public ChargeService ChargeService;
	
}
