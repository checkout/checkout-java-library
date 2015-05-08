package com.checkout.api.services.customer.request;

import java.util.Map;

import com.checkout.api.services.shared.Phone;

public class BaseCustomer{
	
	public String name;
	public String email;
	public Phone phone;
	public String description;
	public Map<String,String> metadata;
}
