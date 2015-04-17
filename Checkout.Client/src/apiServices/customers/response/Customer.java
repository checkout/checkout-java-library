package apiServices.customers.response;

import java.util.Map;
import apiServices.sharedModels.Phone;


public class Customer {
	public String id;
	public String name;	
	public boolean liveMode;
	public String created;
	public String email;
	public Phone phone;
	public String description;
	public Map<String,String> metadata;	
	public CustomerCard cards;
	public String defaultCard;	
}
