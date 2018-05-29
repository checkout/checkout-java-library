package com.checkout.api.services.card.request;

public class CardCreate extends CardUpdate{
	public String cvv; 
	public String number;
	
	// token field is needed ckoAPIClient.cardService.createCard(String customerId, CardCreate payload)
	public String token;
}
