package com.checkout.api.services.charge.request;

import com.checkout.api.services.card.request.CardCreate;

public class CardCharge extends BaseCharge {
	public CardCharge(){
		super();
	}
	
	public String transactionIndicator;
	public CardCreate card;
}
