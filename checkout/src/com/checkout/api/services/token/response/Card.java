package com.checkout.api.services.token.response;

import com.checkout.api.services.card.request.BaseCard;

public class Card extends BaseCard {
	public String id;
	public String last4;
	public String paymentMethod;
}
