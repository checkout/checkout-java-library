package com.checkout.api.services.charge.request;

public class CardIdCharge extends BaseCharge {
	public CardIdCharge(){
		super();
		
	}
	
	public String transactionIndicator;
	public String cardId;
	public String cvv;
}
