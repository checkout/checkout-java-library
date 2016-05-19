package com.checkout.api.services.token.response;

import java.util.Date;

public class VisaCheckoutToken {
	public String id;
	public boolean liveMode;
	public Date created;
	public boolean used;
	public Card card;
	public BinData binData;
}
