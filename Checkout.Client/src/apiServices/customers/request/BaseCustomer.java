package apiServices.customers.request;

import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;
import apiServices.sharedModels.Phone;

import com.google.gson.Gson;

public class BaseCustomer{
	
	public String name;
	public String email;
	public Phone phone;
	public String description;
	public Map<String,String> metadata;
	public String customerId;
	
	public BaseCustomer getResponse(JSONObject customer) {

		Gson gson = new Gson();
		BaseCustomer c = gson.fromJson(customer.toString(),
				BaseCustomer.class);

		for (Iterator<?> iter = customer.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			System.out.println(key + ":" + customer.get(key).toString());

		}
		return c;
	}
}
