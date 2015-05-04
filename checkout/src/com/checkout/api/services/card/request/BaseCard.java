package com.checkout.api.services.card.request;

import com.checkout.api.services.shared.Address;


public class BaseCard {
	public String name;
	public String expiryMonth;
	public String expiryYear;
	public Address billingDetails;
}
