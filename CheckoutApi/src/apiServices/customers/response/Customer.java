package apiServices.customers.response;

import java.util.Map;


public class Customer {
	public String object;
	public String id;
	public String name;	
	public boolean liveMode;
	public String created;
	public String email;
	public String phoneNumber;
	public String description;
	public String itv;
	public Map<String,String> metadata;	
	public CustomerCard cards;
	public String defaultCard;
	public String responseCode;	
	
}
