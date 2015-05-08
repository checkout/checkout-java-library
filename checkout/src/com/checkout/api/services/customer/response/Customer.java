package com.checkout.api.services.customer.response;

import com.checkout.api.services.customer.request.BaseCustomer;

public class Customer extends BaseCustomer {
	public String id;
	public boolean liveMode;
	public String created;
	public CustomerCard cards;
	public String defaultCard;	
}
