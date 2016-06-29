package com.checkout.api.services.card.response;

import com.checkout.api.services.card.request.BaseCard;

public class Card extends BaseCard {
	public String id;
	public String customerId;
	public String last4;
	public String paymentMethod;
	public String fingerprint;
	public String cvvCheck;
	public String avsCheck;
	public String responseCode;
}
